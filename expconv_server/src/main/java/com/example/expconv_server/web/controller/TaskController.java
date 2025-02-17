package com.example.expconv_server.web.controller;


import com.example.expconv_server.domain.task.Task;
import com.example.expconv_server.service.TaskService;
import com.example.expconv_server.web.dto.task.TaskDto;
import com.example.expconv_server.web.dto.validation.OnUpdate;
import com.example.expconv_server.web.mappers.TaskMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tasks")
@Validated
@Tag(name = "Task Controller" , description = "Task API")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;


    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }


    @PutMapping
    @Operation(summary = "Update task")
    public TaskDto updateTask(@Validated(OnUpdate.class) @RequestBody TaskDto taskDto) {
        Task task = taskMapper.toEntity(taskDto);
        Task updatedTask = taskService.update(task);
        return taskMapper.toDto(updatedTask);

    }

    @GetMapping("/{id}")
    @Operation(summary = "Get task by Id")
    public TaskDto getTask(@PathVariable Long id) {
        Task task = taskService.getById(id);
        return taskMapper.toDto(task);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete task")
    public void deleteById(@PathVariable Long id) {
        taskService.delete(id);
    }


}
