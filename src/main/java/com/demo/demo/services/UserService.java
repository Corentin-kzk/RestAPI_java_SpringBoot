package com.demo.demo.services;

import com.demo.demo.Exception.ForbiddenException;
import com.demo.demo.Exception.NotFoundException;
import com.demo.demo.model.User;
import com.demo.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // QUID de l'utilité si la méthode est un passe plat
    protected String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }

    // QUID de l'utilité si la méthode est un passe plat
   protected Boolean validPassword(String password, String encode) {
        return passwordEncoder.matches(password, encode);
    }

    public User create(User user) {
        if (!user.getPassword().isEmpty()) {
            user.setPassword(this.encryptPassword(user.getPassword()));
            return userRepository.save(user);
        } else {
            throw new NotFoundException("Passwords is empty");
        }

    }

    public User update(Long id, User user) {
        return userRepository.findByIdAndIsDeletedFalse(id).map(u -> {
            boolean isSamePassword = this.validPassword(user.getPassword(), u.getPassword());
            if (!isSamePassword) {
                u.setPassword(this.encryptPassword(user.getPassword()));
                u.setFirstname(user.getFirstname());
                u.setLastname(user.getLastname());
                u.setUpdatedAt(LocalDateTime.now());
                return userRepository.save(u);
            }
            else {
                throw new ForbiddenException("Passwords can not the same");
            }

        }).orElseThrow(() -> new NotFoundException("User not found"));
    }


    public void delete(Long id) {
        userRepository.findByIdAndIsDeletedFalse(id).map(u -> {
            u.setDeleted_at(LocalDateTime.now());
            u.setDeleted(true);
            return userRepository.save(u);
        }).orElseThrow(() -> new NotFoundException("User not found"));
    }


    public User getUserById(Long id) {
        return userRepository.findByIdAndIsDeletedFalse(id).orElseThrow(() -> new NotFoundException("User not found"));
    }
}
