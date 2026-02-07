package com.example.demo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class demoController {
    // Serve the index.html page
    @GetMapping("/index")
    public String home() {
        return "index"; // Refers to src/main/resources/templates/index.html
    }
    // Serve the registration.html page
    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }
    @GetMapping("/contactus")
    public String contactus() {
        return "contactus";
    }

}