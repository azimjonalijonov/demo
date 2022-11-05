package com.example.demo.service;

import com.example.demo.Entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }
    public User save(User user){
       user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    public boolean existByLogin(String login){
        return userRepository.existsByLogin(login);
    }


}
