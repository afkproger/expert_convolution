package com.example.expconv_server.web.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class JwtEntity implements UserDetails {

    @Override
    public String toString() {
        return "JwtEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                '}';
    }

    @Setter
    @Getter
    private Long id;
    @Setter
    @Getter
    private String name;
    private String email;
    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;


    public JwtEntity(Long id, String name, String password, String username, String email, List<GrantedAuthority> authorities) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.username = username;
        this.email = email;
        this.authorities = authorities;
    }


    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
