package com.app.webdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.webdemo.model.RegistrationForm;
import jakarta.validation.Valid;

@Controller
public class WebdemoController {

    // หน้าแรก: index.html
    @GetMapping("/")
    public String home() {
        return "index";
    }

    // แสดงฟอร์มสมัครสมาชิก: registration.html
    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        // ถ้ายังไม่มี attribute ใน model ให้สร้างใหม่ (กัน null)
        if (!model.containsAttribute("registrationForm")) {
            model.addAttribute("registrationForm", new RegistrationForm());
        }
        return "registration";
    }

    // เพิ่มหน้า Contact Us: contactus.html  (เพื่อให้ Integration test เขียว)
    @GetMapping("/contactus")
    public String contactus() {
        return "contactus";
    }

    // รับ submit ฟอร์ม
    @PostMapping("/register")
    public String handleRegistration(
            @Valid RegistrationForm registrationForm,
            BindingResult bindingResult,
            Model model) {

        // ถ้ามี error validation → กลับไปหน้า registration พร้อมข้อความ error
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        // เก็บข้อมูลไว้แสดงผลในหน้า success
        model.addAttribute("firstName", registrationForm.getFirstName());
        model.addAttribute("lastName", registrationForm.getLastName());
        model.addAttribute("country", registrationForm.getCountry());
        model.addAttribute("dob", registrationForm.getDob());
        model.addAttribute("email", registrationForm.getEmail());

        return "success"; // success.html
    }

    // (ตัวเลือก) อยากเปิด success ตรง ๆ ได้ก็ปลดคอมเมนต์
    // @GetMapping("/success")
    // public String success() {
    //     return "success";
    // }
}