package com.e_commerce.application.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
    
    @GetMapping("/api/hello")
    public String helloWorld(){
        return "Hello World";
    }

}
