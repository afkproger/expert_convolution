package com.example.expconv_server.web.controller;


import com.example.expconv_server.domain.task.Task;
import com.example.expconv_server.domain.user.User;
import com.example.expconv_server.service.TaskService;
import com.example.expconv_server.service.UserService;
import com.example.expconv_server.service.impl.AuthServiceImpl;
import com.example.expconv_server.web.dto.task.TaskDto;
import com.example.expconv_server.web.dto.user.UserDto;
import com.example.expconv_server.web.dto.validation.OnCreate;
import com.example.expconv_server.web.dto.validation.OnUpdate;
import com.example.expconv_server.web.mappers.TaskMapper;
import com.example.expconv_server.web.mappers.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Validated
@Tag(name = "User Controller", description = "User API")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final TaskService taskService;
    private final TaskMapper taskMapper;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService, UserMapper userMapper, TaskService taskService, TaskMapper taskMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by Id")
    public UserDto getUserById(@PathVariable Long id) {
        User user = userService.getById(id);
        return userMapper.toDto(user);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user")
    public void deleteUserById(@PathVariable Long id) {
        userService.delete(id);
    }

    @PutMapping
    @Operation(summary = "Update user")
    public UserDto updateUser(@Validated({OnUpdate.class}) @RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        User updatedUser = userService.update(user);
        return userMapper.toDto(updatedUser);
    }

    @GetMapping("/{id}/tasks")
    @Operation(summary = "Get all user tasks")
    public List<TaskDto> getUserTasks(@PathVariable Long id) {
        List<Task> userTasksById = taskService.getAllByUserId(id);
        logger.info("User tasks " + userTasksById.toString());
        List<TaskDto> taskDto = taskMapper.toDto(userTasksById);
        logger.info("Tasks dto" + taskDto.toString());
        return taskDto;
    }

    @PostMapping("/{id}/tasks")
    @Operation(summary = "Add task to user")
    public TaskDto createUserTask(@PathVariable Long id,
                                  @Validated(OnCreate.class) @RequestBody TaskDto taskDto) {

        Task task = taskMapper.toEntity(taskDto);
        Task createdTask = taskService.create(task, id);
        return taskMapper.toDto(createdTask);

    }

}
