package com.e_commerce.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.e_commerce.application.entity.User;
import com.e_commerce.application.service.AuthServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private AuthServiceImpl authServiceImpl;

    
    @PostMapping("/api/save_details")
    public String userDetails(@RequestBody User user ){

        authServiceImpl.insertUserDetails(user);

        return "User registered successfully";
    }

}
