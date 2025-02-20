package com.example.expconv_server.web.security.providers;


import com.example.expconv_server.domain.exception.AccessDeniedException;
import com.example.expconv_server.domain.user.Role;
import com.example.expconv_server.domain.user.User;
import com.example.expconv_server.service.UserService;
import com.example.expconv_server.web.dto.auth.JwtResponse;
import com.example.expconv_server.web.security.JwtProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class JwtTokenProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
    private final JwtProperties jwtProperties;
    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private Key key;

    public JwtTokenProvider(JwtProperties jwtProperties, UserDetailsService userDetailsService, UserService userService) {
        this.jwtProperties = jwtProperties;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
    }

    private List<String> resolveString(Set<Role> roles) {
        return roles.stream().map(Enum::name).toList();
    }

    public String createAccessToken(Long userId, String username, Set<Role> roles) {
        Claims claims = Jwts.claims();
        claims.put("userId", userId);
        claims.put("roles", resolveString(roles));
        Date now = new Date();
        Date validity = new Date(now.getTime() + jwtProperties.getAccess());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key)
                .compact();
    }


    public String createRefreshToken(Long userId, String username) {
        Claims claims = Jwts.claims(); // создаем ClaimsBuilder
        claims.put("userId", userId);  // добавляем данные в ClaimsBuilder
        Date now = new Date();
        Date validity = new Date(now.getTime() + jwtProperties.getRefresh());

        return Jwts.builder()
                .setClaims(claims)  // передаем уже готовый объект Claims
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key)
                .compact();
    }

    public JwtResponse refreshUserToken(String refreshToken) {
        JwtResponse jwtResponse = new JwtResponse();
        if (validateToken(refreshToken)) {
            Long userId = Long.valueOf(getId(refreshToken));
            User user = userService.getById(userId);
            jwtResponse.setUserId(userId);
            jwtResponse.setUsername(user.getUsername());

            return jwtResponse;
        } else {
            throw new AccessDeniedException();
        }
    }

    public boolean validateToken(String token) {
        logger.info("Мы зашли в validateToken");
        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
            return !claims.getExpiration().before(new Date());  // Проверка срока действия

        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private String getId(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return claims.getId();
    }

    public Authentication getAuthentication(String token) {
        String userName = getUsername(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
