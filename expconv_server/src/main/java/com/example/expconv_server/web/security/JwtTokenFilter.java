package com.example.expconv_server.web.security;

import com.example.expconv_server.domain.exception.ResourceNotFoundException;
import com.example.expconv_server.web.security.providers.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.Arrays;

public class JwtTokenFilter extends GenericFilterBean {
    private JwtTokenProvider tokenProvider;

    public JwtTokenFilter(JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("accessToken".equals(cookie.getName())) {
                    token = cookie.getValue();
                    logger.info("Токен - " + token);
                    break;
                }
            }
        }

        if (token != null) {
            try {
                boolean isValid = tokenProvider.validateToken(token);
                logger.info("Токен валиден ? - " + isValid);
                if (isValid) {
                    Authentication auth = tokenProvider.getAuthentication(token);
                    if (auth != null) {
                        SecurityContextHolder.getContext().setAuthentication(auth);
                    } else {
                        logger.error("Ошибка: Authentication объект null");
                    }
                } else {
                    throw new RuntimeException("Ошибка авторизации в JwtTokenFilter ");
                }
            } catch (Exception e) {
                logger.error("Ошибка при обработке токена: ", e);
                throw new ResourceNotFoundException("Ошибка с куки: " + e.getMessage());
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
