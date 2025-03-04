package com.example.expconv_server.web.mappers;

import com.example.expconv_server.domain.task.Task;
import com.example.expconv_server.web.dto.task.TaskDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    @Mapping(target = "scale", source = "scale")
    @Mapping(target = "indicators", source = "indicators")
    TaskDto toDto(Task task);
    List<TaskDto> toDto(List<Task> tasks);

    @Mapping(target = "scale", source = "scale")
    Task toEntity(TaskDto taskDto);
}
