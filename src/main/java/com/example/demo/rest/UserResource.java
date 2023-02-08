package com.example.demo.rest;

import com.example.demo.Entity.User;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserResource {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserResource(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }
    @PostMapping("/register")
    public ResponseEntity save(@RequestBody User user){
        if (userService.existByLogin(user.getLogin())){
            return new ResponseEntity("bu user mavjud", HttpStatus.BAD_REQUEST);

        }
        if (lengthCheck(user.getPassword())) {
            return new ResponseEntity("length is less", HttpStatus.BAD_REQUEST);
        }
        User result =userService.save(user);
        return ResponseEntity.ok(result);

    }
    public boolean lengthCheck(String pass){
        return (pass.length()<=4);
    }
}
