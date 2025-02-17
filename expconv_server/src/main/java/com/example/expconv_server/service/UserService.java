package com.example.expconv_server.service;

import com.example.expconv_server.domain.user.User;

public interface UserService {

    User getById(Long id);

    User getByUsername(String username);

    User update(User user);

    User create(User user);

    void delete(Long id);

    boolean isTaskOwner(Long userId, Long taskId);
}
