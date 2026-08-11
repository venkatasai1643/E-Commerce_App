package com.e_commerce.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_commerce.application.entity.User;
import com.e_commerce.application.repository.UserRepository;

@Service
public class AuthServiceImpl {

    @Autowired
    private UserRepository repository;

    public void insertUserDetails(User user){
        repository.save(user);
    }

    public String loginUser(User user){
        User existingUser = repository.findByEmail(user.getEmail()).orElse(null);
        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            return "Login successful";
        } else {
            return "Invalid email or password";
        }
    }
}
