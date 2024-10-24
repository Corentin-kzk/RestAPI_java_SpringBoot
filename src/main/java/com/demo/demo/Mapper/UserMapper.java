package com.demo.demo.Mapper;

import com.demo.demo.DTO.UserDTO;
import com.demo.demo.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO convertUserToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstname(user.getFirstname());
        userDTO.setLastname(user.getLastname());
        userDTO.setEmail(user.getEmail());
        userDTO.setId(user.getId());
        return userDTO;
    }
}
