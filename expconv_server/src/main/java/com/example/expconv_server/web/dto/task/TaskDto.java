package com.example.expconv_server.web.dto.task;

import com.example.expconv_server.domain.task.Status;
import com.example.expconv_server.web.dto.validation.OnCreate;
import com.example.expconv_server.web.dto.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@Data
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
}

