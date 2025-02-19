package com.example.expconv_server.service.impl;

import com.example.expconv_server.domain.user.User;
import com.example.expconv_server.service.AuthService;
import com.example.expconv_server.service.UserService;
import com.example.expconv_server.web.dto.auth.JwtRequest;
import com.example.expconv_server.web.dto.auth.JwtResponse;
import com.example.expconv_server.web.security.providers.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
            System.out.println("Authentication failed: " + e.getMessage());
            throw new RuntimeException("Authentication failed", e);
        }
        User user = userService.getByUsername(loginRequest.getUsername());
        jwtResponse.setUsername(user.getUsername());
        jwtResponse.setId(user.getId());


        String accessToken = jwtTokenProvider.createRefreshToken(user.getId(), user.getUsername());
        String refreshToken = jwtTokenProvider.createAccessToken(user.getId(), user.getUsername(), user.getRoles());

        ResponseCookie accessTokenCookie = ResponseCookie.from("accessToken", accessToken)
                .httpOnly(true)
                .sameSite("Strict")
                .path("/")
                .maxAge(Duration.ofMinutes(15))
                .build();

        ResponseCookie refreshTokenCookies = ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true).sameSite("Strict").path("/").maxAge(Duration.ofDays(15)).build();


        response.addHeader("Set-Cookie" , refreshTokenCookies.toString());
        response.addHeader("Set-Cookie", accessTokenCookie.toString());
        jwtResponse.setRefreshToken(refreshToken);
        return jwtResponse;
    }

    @Override
    public JwtResponse refresh(String refreshToken) {
        return jwtTokenProvider.refreshUserToken(refreshToken);
    }
}
