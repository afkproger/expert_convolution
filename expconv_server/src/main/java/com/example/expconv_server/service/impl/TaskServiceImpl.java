package com.example.expconv_server.service.impl;

import com.example.expconv_server.domain.exception.ResourceNotFoundException;
import com.example.expconv_server.domain.task.Status;
import com.example.expconv_server.domain.task.Task;
import com.example.expconv_server.domain.user.User;
import com.example.expconv_server.repository.TaskRepository;
import com.example.expconv_server.repository.UserRepository;
import com.example.expconv_server.service.TaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Task getById(Long id) {
        return taskRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TaskNotFound"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getAllByUserId(Long userId) {
        return taskRepository.findByUserId(userId);
    }

    @Override
    @Transactional
    public Task update(Task task) {
        if (task.getStatus() == null) {
            task.setStatus(Status.TODO);
        }
        taskRepository.save(task);
        return task;
    }

    @Override
    @Transactional
    public void delete(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    @Transactional
    public Task create(Task task, Long userId) {
        task.setStatus(Status.TODO);
        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("Пользователь с таким id не найден , не возможно создать для него задачу"));
        task.setExpirationDate(LocalDateTime.now());
        task.setUser(user);
        taskRepository.save(task);
        return task;
    }
}
