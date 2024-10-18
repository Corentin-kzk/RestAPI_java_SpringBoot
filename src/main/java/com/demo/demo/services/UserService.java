package com.demo.demo.services;
import com.demo.demo.model.User;

public interface UserService {
    User create(User user);
    User update(Long id, User user);
    void delete(Long id);
    User getUserById(Long id);
}
