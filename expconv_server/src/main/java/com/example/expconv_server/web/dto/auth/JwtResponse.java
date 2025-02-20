package com.example.expconv_server.web.dto.auth;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Response login")
public class JwtResponse {
    private Long user_id;

    @Override
    public String toString() {
        return "JwtResponse{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                '}';
    }

    @Schema(description = "User username" , example = "afkproger")
    private String username;

    public Long getUserId() {
        return user_id;
    }

    public void setUserId(Long user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
