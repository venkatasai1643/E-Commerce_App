package com.e_commerce.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.e_commerce.application.entity.User;
import com.e_commerce.application.repository.UserRepository;

@Service
public class AuthServiceImpl {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User insertUserDetails(User user){

        String hashedPassword =
                passwordEncoder.encode(user.getPassword());

        user.setPassword(hashedPassword);

        return repository.save(user);
    }

    public String loginUser(User user){
        User existingUser = repository.findByEmail(user.getEmail()).orElse(null);
        
         boolean passwordMatches =
                passwordEncoder.matches(
                        user.getPassword(),
                        existingUser.getPassword()
                );
         if (passwordMatches) {
            return "Login successful";
        }

        return "Invalid email or password";
    }
}
