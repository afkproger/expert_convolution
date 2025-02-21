package com.example.expconv_server.service;

import com.example.expconv_server.web.dto.auth.JwtRequest;
import com.example.expconv_server.web.dto.auth.JwtResponse;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
    JwtResponse login(JwtRequest loginRequest , HttpServletResponse response);
    JwtResponse refresh (String refreshToken ,  HttpServletResponse response);
    boolean logout(HttpServletResponse response);
}
