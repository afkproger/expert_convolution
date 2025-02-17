package com.example.expconv_server.web.dto.auth;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Response login")
public class JwtResponse {
    private Long id;

    @Schema(description = "User username" , example = "afkproger")
    private String username;
    @Schema(description = "User accusesToken" , example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c")
    private String accusesToken;
    @Schema(description = "User refreshToken" , example = "eyJhbGciOiJIUzI1NiIsInR5cCI6Imh5aSJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.7_fbaJYGoYZPCA_shPouXn3hFgC12WQ4mJas3f55VeY")
    private String refreshToken;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccusesToken() {
        return accusesToken;
    }

    public void setAccusesToken(String accusesToken) {
        this.accusesToken = accusesToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
