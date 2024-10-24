package com.demo.demo.controller;

import com.demo.demo.DTO.UserDTO;
import com.demo.demo.Mapper.UserMapper;
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
    private final UserMapper userMapper;

    @PostMapping("/")
    public UserDTO create(@RequestBody User user) {
        return userMapper.convertUserToUserDTO(userService.create(user));
    }

    @PatchMapping("/{id}")
    public User delete(@PathVariable Long id) {
       userService.delete(id);
       return null;
    }

    @PutMapping("/{id}")
    public UserDTO update(@PathVariable Long id, @RequestBody User user) {
        return  userMapper.convertUserToUserDTO(userService.update(id, user));
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return  userMapper.convertUserToUserDTO(userService.getUserById(id));

    }
}
