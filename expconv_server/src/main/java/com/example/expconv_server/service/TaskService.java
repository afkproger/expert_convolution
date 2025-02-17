package com.example.expconv_server.service;

import com.example.expconv_server.domain.task.Task;

import java.util.List;

public interface TaskService {
    Task getById(Long id);

    List<Task> getAllByUserId(Long userId);

   Task update(Task task);

    void delete(Long id);

    Task create(Task task , Long userId);
}
