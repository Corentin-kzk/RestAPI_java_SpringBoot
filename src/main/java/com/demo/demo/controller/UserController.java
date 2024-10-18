package com.demo.demo.controller;

import com.demo.demo.services.UserService;
import com.demo.demo.model.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/new")
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @PatchMapping("/delete/{id}")
    public User delete(@PathVariable Long id) {
       userService.delete(id);
       return null;
    }

    @PutMapping("/update/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        return userService.update(id, user);
    }



}
