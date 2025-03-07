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
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Duration;
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
        Claims claims = Jwts.claims();
        claims.put("userId", userId);
        Date now = new Date();
        Date validity = new Date(now.getTime() + jwtProperties.getRefresh());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key)
                .compact();
    }

    public JwtResponse refreshUserToken(String refreshToken, HttpServletResponse response) {
        JwtResponse jwtResponse = new JwtResponse();
        if (validateToken(refreshToken)) {
            Long userId = getId(refreshToken);
            User user = userService.getById(userId);
            jwtResponse.setUserId(userId);
            jwtResponse.setUsername(user.getUsername());

            String updateAccessToken = createAccessToken(userId, user.getUsername(), user.getRoles());
            String updateRefreshToken = createRefreshToken(user.getId(), user.getUsername());

            deleteCookie("refreshToken", response);
            deleteCookie("accessToken", response);
            addCookie("accessToken", updateAccessToken, Duration.ofMinutes(15), response);
            addCookie("refreshToken", updateRefreshToken, Duration.ofDays(7), response);

            return jwtResponse;
        } else {
            throw new AccessDeniedException();
        }
    }

    public boolean validateToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
            return !claims.getExpiration().before(new Date());

        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public boolean deleteTokens(HttpServletResponse response) {
        try {
            deleteCookie("refreshToken", response);
            deleteCookie("accessToken", response);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Long getId(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return claims.get("userId", Long.class);
    }

    private void deleteCookie(String name, HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie.from(name, "")
                .httpOnly(true)
                .sameSite("Lax")
                .path("/")
                .maxAge(0)
                .build();
        response.addHeader("Set-Cookie", cookie.toString());
    }

    public void addCookie(String name, String value, Duration maxAge, HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie.from(name, value)
                .httpOnly(true)
                .sameSite("Lax")
                .path("/")
                .maxAge(maxAge)
                .build();
        response.addHeader("Set-Cookie", cookie.toString());
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
