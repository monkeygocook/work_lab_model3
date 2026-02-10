package com.app.webdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebdemoController {

    // Serve the index.html page
    @GetMapping("/")
    public String home() {
        return "index"; // returns Thymeleafe template index.html
    }

   // Display the registration form
    @GetMapping("/registration")
    public String showRegistrationForm() {
        return "registration"; // returns Thymeleafe template registration.html
    }
}
