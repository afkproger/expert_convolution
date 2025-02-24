package com.example.expconv_server.service.impl;

import com.example.expconv_server.domain.user.User;
import com.example.expconv_server.service.AuthService;
import com.example.expconv_server.service.UserService;
import com.example.expconv_server.web.dto.auth.JwtRequest;
import com.example.expconv_server.web.dto.auth.JwtResponse;
import com.example.expconv_server.web.security.providers.JwtTokenProvider;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.Duration;


@Service
public class AuthServiceImpl implements AuthService {
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
    public AuthServiceImpl(JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager, UserService userService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @Override
    public JwtResponse login(JwtRequest loginRequest, HttpServletResponse response) {
        JwtResponse jwtResponse = new JwtResponse();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        } catch (Exception e) {
            throw new RuntimeException("Authentication failed", e);
        }
        User user = userService.getByUsername(loginRequest.getUsername());
        jwtResponse.setUsername(user.getUsername());
        jwtResponse.setUserId(user.getId());


        String accessToken = jwtTokenProvider.createAccessToken(user.getId(), user.getUsername(), user.getRoles());
        String refreshToken = jwtTokenProvider.createRefreshToken(user.getId(), user.getUsername());


        jwtTokenProvider.addCookie("accessToken" , accessToken , Duration.ofMinutes(15) , response);
        jwtTokenProvider.addCookie("refreshToken", refreshToken , Duration.ofDays(7) , response);
        return jwtResponse;
    }

    @Override
    public JwtResponse refresh(String refreshToken ,  HttpServletResponse response) {
        return jwtTokenProvider.refreshUserToken(refreshToken , response);
    }

    @Override
    public boolean logout(HttpServletResponse response) {
        return jwtTokenProvider.deleteTokens(response);
    }
}
