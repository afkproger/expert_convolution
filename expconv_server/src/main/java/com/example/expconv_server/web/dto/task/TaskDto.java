package com.example.expconv_server.web.dto.task;

import com.example.expconv_server.domain.task.Status;
import com.example.expconv_server.web.dto.indicator.IndicatorDto;
import com.example.expconv_server.web.dto.scale.ScaleDto;
import com.example.expconv_server.web.dto.validation.OnCreate;
import com.example.expconv_server.web.dto.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;


@Schema(description = "Task Dto")
public class TaskDto {
    @Schema(description = "Task Id")
    @NotNull(message = "Id must be not null" , groups = OnUpdate.class)
    private Long id;

    @NotNull(message = "Title must be not null" , groups = {OnUpdate.class , OnCreate.class} )
    @Length(max=255 , message = "Title length must be smaller than symbols" , groups = {OnUpdate.class , OnCreate.class})
    @Schema(description = "Task title" , example = "Call Mike")
    private String title;

    @Length(max=255 , message = "Title length must be smaller than symbols" , groups = {OnUpdate.class , OnCreate.class})
    @Schema(description = "Title description" , example = "Ask about meeting ")
    private String description;

    private Status status;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime expirationDate;

    @Schema(description = "List of scales for the task")
    private List<ScaleDto> scale;

    @Schema(description = "List of indicators for the task")
    private List<IndicatorDto> indicators;

    public List<ScaleDto> getScale() {
        return scale;
    }

    public void setScale(List<ScaleDto> scale) {
        this.scale = scale;
    }

    public List<IndicatorDto> getIndicators() {
        return indicators;
    }

    public void setIndicators(List<IndicatorDto> indicators) {
        this.indicators = indicators;
    }

    public @NotNull(message = "Id must be not null", groups = OnUpdate.class) Long getId() {
        return id;
    }

    public void setId(@NotNull(message = "Id must be not null", groups = OnUpdate.class) Long id) {
        this.id = id;
    }

    public @NotNull(message = "Title must be not null", groups = {OnUpdate.class, OnCreate.class}) @Length(max = 255, message = "Title length must be smaller than symbols", groups = {OnUpdate.class, OnCreate.class}) String getTitle() {
        return title;
    }

    public void setTitle(@NotNull(message = "Title must be not null", groups = {OnUpdate.class, OnCreate.class}) @Length(max = 255, message = "Title length must be smaller than symbols", groups = {OnUpdate.class, OnCreate.class}) String title) {
        this.title = title;
    }

    public @Length(max = 255, message = "Title length must be smaller than symbols", groups = {OnUpdate.class, OnCreate.class}) String getDescription() {
        return description;
    }

    public void setDescription(@Length(max = 255, message = "Title length must be smaller than symbols", groups = {OnUpdate.class, OnCreate.class}) String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }
}

