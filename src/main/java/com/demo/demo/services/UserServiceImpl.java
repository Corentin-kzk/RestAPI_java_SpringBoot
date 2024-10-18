package com.demo.demo.services;
import com.demo.demo.model.User;
import com.demo.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private String encryptPassword(String password) {
        System.out.println(password);
        return passwordEncoder.encode(password);
    }

   private Boolean decryptPassword(String password, String encode) {
        return passwordEncoder.matches(password, encode);
    }


    @Override
    public User create(User user) {
        if (!user.getPassword().isEmpty()) {
            user.setPassword(this.encryptPassword(user.getPassword()));
            return userRepository.save(user);
        } else {
            throw new RuntimeException("Passwords is empty");
        }

    }

    @Override
    public User update(Long id, User user) {
        return userRepository.findById(id).map(u -> {
            boolean isSamePassword = this.decryptPassword(user.getPassword(), u.getPassword());
            if (!isSamePassword) {
                u.setPassword(this.encryptPassword(user.getPassword()));
                u.setFirstname(user.getFirstname());
                u.setLastname(user.getLastname());
                u.setUpdatedAt(new Date());
                return userRepository.save(u);
            }
            else {
                throw new RuntimeException("Passwords can not the same");
            }

        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public void delete(Long id) {
        userRepository.findById(id).map(u -> {
            u.setDeleted_at(new Date());
            return userRepository.save(u);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
