package com.example.expconv_server.web.controller;


import com.example.expconv_server.domain.user.User;
import com.example.expconv_server.service.AuthService;
import com.example.expconv_server.service.UserService;
import com.example.expconv_server.web.dto.auth.JwtRequest;
import com.example.expconv_server.web.dto.auth.JwtResponse;
import com.example.expconv_server.web.dto.user.UserDto;
import com.example.expconv_server.web.dto.validation.OnCreate;
import com.example.expconv_server.web.mappers.UserMapper;
import com.example.expconv_server.web.security.providers.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@Validated
@Tag(name = "Auth Controller", description = "Auth API")
public class AuthController {
    private final AuthService authService;
    private final UserService userService;
    private final UserMapper userMapper;
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(AuthService authService, UserService userService, UserMapper userMapper, JwtTokenProvider jwtTokenProvider) {
        this.authService = authService;
        this.userService = userService;
        this.userMapper = userMapper;
        this.jwtTokenProvider = jwtTokenProvider;
    }


    @PostMapping("/login")
    @Operation(summary = "Get user tokens")
    public JwtResponse login(@RequestBody JwtRequest jwtRequest, HttpServletResponse response) {
        JwtResponse jwtResponse = authService.login(jwtRequest, response);
        logger.info(jwtResponse.toString());
        return jwtResponse;
    }

    @PostMapping("/register")
    @Operation(summary = "Register")
    public ResponseEntity<?> register(@Validated(OnCreate.class) @RequestBody UserDto userDto) {
        try {
            User user = userMapper.toEntity(userDto);
            User createdUser = userService.create(user);
            UserDto createdUserDto = userMapper.toDto(createdUser);
            logger.info(createdUserDto.toString());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUserDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }


    @PostMapping("/refresh")
    @Operation(summary = "Refresh user tokens")
    public JwtResponse refresh(@RequestBody String refreshToken) {
        return authService.refresh(refreshToken);
    }


    @GetMapping("/check")
    @Operation(summary = "Проверяем есть ли куки файлы на сервере")
    public ResponseEntity<?> check(@CookieValue(name = "accessToken", required = false) String accessToken , HttpServletRequest request) {
        if (accessToken == null || accessToken.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("authenticated", false));
        } else {
            if (jwtTokenProvider.validateToken(accessToken)) {
                String username = jwtTokenProvider.getUsername(accessToken);
                User user = userService.getByUsername(username);
                return ResponseEntity.status(HttpStatus.OK)
                        .body(Map.of("authenticated", true, "userId", user.getId()));
            }else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("authenticated", false));
            }
        }
    }
}
