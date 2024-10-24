package com.demo.demo.controller;

import com.demo.demo.model.User;
import com.demo.demo.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    @Autowired
    private final UserService userService;

    @PostMapping("/")
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @PatchMapping("/{id}")
    public User delete(@PathVariable Long id) {
       userService.delete(id);
       return null;
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        return userService.update(id, user);
    }



}
