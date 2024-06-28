package com.capstone.librarymsapprest.controller;

// AuthController.java
import com.capstone.librarymsapprest.model.User;
import com.capstone.librarymsapprest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        user.setUsername(user.getUsername());
        user.setPassword(user.getPassword());
        user.setConfirmPassword(user.getConfirmPassword());
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }
        return userRepository.save(user);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody User user) {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null && (user.getPassword().equals(existingUser.getPassword()))) {
            return "Login successful!";
        } else {
            throw new IllegalArgumentException("Invalid username or password");
        }
    }
}

