package com.example.expconv_server.web.security;

import com.example.expconv_server.domain.user.Role;
import com.example.expconv_server.domain.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JwtEntityFactory {
    public static JwtEntity createJwtEntity(User user) {
        JwtEntity entity = new JwtEntity(
                user.getId(),
                user.getName(),
                user.getPassword(),
                user.getUsername(),
                user.getEmail(),
                mapToGrantedAuthorization(new ArrayList<>(user.getRoles()))
        );
        return entity;
    }

    private static List<GrantedAuthority> mapToGrantedAuthorization(List<Role> roles) {
        return roles.stream().map(name -> name.name() ).map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
    }
}
