package com.example.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

// ถ้าคลาสของคุณยังใช้ชื่อ demoController (ตัว d เล็ก) ให้ใส่ demoController.class
@WebMvcTest(demoController.class) // หรือ @WebMvcTest(DemoController.class) ถ้าเปลี่ยนชื่อคลาสแล้ว
class DemoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void index_returnsIndexView() throws Exception {
        mvc.perform(get("/"))
           .andExpect(status().isOk())
           .andExpect(view().name("index"));
    }

    @Test
    void registration_returnsRegistrationView() throws Exception {
        mvc.perform(get("/registration"))
           .andExpect(status().isOk())
           .andExpect(view().name("registration"));
    }

    @Test
    void contactus_returnsContactusView() throws Exception {
        mvc.perform(get("/contactus"))
           .andExpect(status().isOk())
           .andExpect(view().name("contactus"));
    }
}