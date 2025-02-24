package com.example.expconv_server.web.dto.auth;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Request for login")
public class JwtRequest {

    @Schema(description = "username" ,example = "Nikolai")
    @NotNull(message="Username must be not null")
    private String username;

    @Schema(description = "password" , example = "$2a$10$ajE5ZyZ5Bo56hFj41mmC1OUsXNg8dkYH5WdnzETpRZdLolJHTsT5i")
    @NotNull(message = "Password must be not null")
    private String password;

    public @NotNull(message = "Username must be not null") String getUsername() {
        return username;
    }

    public void setUsername(@NotNull(message = "Username must be not null") String username) {
        this.username = username;
    }

    public @NotNull(message = "Password must be not null") String getPassword() {
        return password;
    }

    public void setPassword(@NotNull(message = "Password must be not null") String password) {
        this.password = password;
    }
}
