package com.example.expconv_server.web.dto.auth;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Response login")
public class JwtResponse {
    private Long user_id;

    @Schema(description = "User username" , example = "afkproger")
    private String username;

    public Long getId() {
        return user_id;
    }

    public void setId(Long id) {
        this.user_id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
