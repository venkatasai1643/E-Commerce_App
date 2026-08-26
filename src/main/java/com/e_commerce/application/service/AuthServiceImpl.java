package com.e_commerce.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.e_commerce.application.entity.User;
import com.e_commerce.application.repository.UserRepository;
import com.e_commerce.application.security.JwtService;
import com.e_commerce.application.dto.LoginRequest;
import com.e_commerce.application.dto.LoginResponse;

@Service
public class AuthServiceImpl {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public User insertUserDetails(User user){

        String hashedPassword =
                passwordEncoder.encode(user.getPassword());

        user.setPassword(hashedPassword);

        return repository.save(user);
    }

    public LoginResponse loginUser(LoginRequest loginRequest){
        User existingUser = repository.findByEmail(loginRequest.getEmail()).orElse(null);
        
        if(existingUser == null)
            return null;

         boolean passwordMatches =
                passwordEncoder.matches(
                        loginRequest.getPassword(),
                        existingUser.getPassword()
                );
         if (!passwordMatches) {
            return null;
        }
        String token = jwtService.generateToken(existingUser.getEmail(), existingUser.getRole());

        return new LoginResponse(token,existingUser.getRole());
    }
}
