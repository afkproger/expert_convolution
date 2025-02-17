package com.example.expconv_server.web.dto.user;

import com.example.expconv_server.domain.task.Task;
import com.example.expconv_server.domain.user.Role;
import com.example.expconv_server.web.dto.validation.OnCreate;
import com.example.expconv_server.web.dto.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;
import java.util.Set;

public class UserDto {
    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", passwordConfirmation='" + passwordConfirmation + '\'' +
                '}';
    }

    public UserDto(Long id, String name, String username, String password, String email, String passwordConfirmation) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.passwordConfirmation = passwordConfirmation;
    }

    public @NotNull(message = "Id must be not null.", groups = OnUpdate.class) Long getId() {
        return id;
    }

    public void setId(@NotNull(message = "Id must be not null.", groups = OnUpdate.class) Long id) {
        this.id = id;
    }

    public @NotNull(message = "Name must be not null.", groups = {OnUpdate.class, OnCreate.class}) @Length(max = 255, message = "Name length must be smaller than 255 symbols", groups = {OnUpdate.class, OnCreate.class}) String getName() {
        return name;
    }

    public void setName(@NotNull(message = "Name must be not null.", groups = {OnUpdate.class, OnCreate.class}) @Length(max = 255, message = "Name length must be smaller than 255 symbols", groups = {OnUpdate.class, OnCreate.class}) String name) {
        this.name = name;
    }

    public @NotNull(message = "Username must be not null.", groups = {OnUpdate.class, OnCreate.class}) @Length(max = 255, message = "Username length must be smaller than 255 symbols", groups = {OnUpdate.class, OnCreate.class}) String getUsername() {
        return username;
    }

    public void setUsername(@NotNull(message = "Username must be not null.", groups = {OnUpdate.class, OnCreate.class}) @Length(max = 255, message = "Username length must be smaller than 255 symbols", groups = {OnUpdate.class, OnCreate.class}) String username) {
        this.username = username;
    }

    public @NotNull(message = "Password must be not null", groups = {OnUpdate.class, OnCreate.class}) String getPassword() {
        return password;
    }

    public void setPassword(@NotNull(message = "Password must be not null", groups = {OnUpdate.class, OnCreate.class}) String password) {
        this.password = password;
    }

    public @NotNull(message = "Email must be not null", groups = {OnUpdate.class, OnCreate.class}) String getEmail() {
        return email;
    }

    public void setEmail(@NotNull(message = "Email must be not null", groups = {OnUpdate.class, OnCreate.class}) String email) {
        this.email = email;
    }

    public @NotNull(message = "Password confirmation must be not null", groups = {OnUpdate.class, OnCreate.class}) String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(@NotNull(message = "Password confirmation must be not null", groups = {OnUpdate.class, OnCreate.class}) String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    @NotNull(message = "Id must be not null.", groups = OnUpdate.class)
    private Long id;

    @NotNull(message = "Name must be not null.", groups = {OnUpdate.class, OnCreate.class})
    @Length(max=255 , message = "Name length must be smaller than 255 symbols" , groups = {OnUpdate.class, OnCreate.class})
    private String name;


    @NotNull(message = "Username must be not null.", groups = {OnUpdate.class, OnCreate.class})
    @Length(max=255 , message = "Username length must be smaller than 255 symbols" , groups = {OnUpdate.class, OnCreate.class})
    private String username;

    @NotNull(message = "Password must be not null" , groups = {OnUpdate.class, OnCreate.class})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotNull(message = "Email must be not null" , groups = {OnUpdate.class, OnCreate.class})
    private String email;

    @NotNull(message = "Password confirmation must be not null" , groups = {OnUpdate.class, OnCreate.class})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String passwordConfirmation;
}
