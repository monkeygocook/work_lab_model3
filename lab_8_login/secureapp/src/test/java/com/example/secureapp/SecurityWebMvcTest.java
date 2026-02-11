package com.example.secureapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientAutoConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerAutoConfiguration;
import org.springframework.boot.autoconfigure.security.saml2.Saml2RelyingPartyAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

// ✅ ชี้ controller ให้ตรงกับโปรเจกต์ในรูป
import com.example.secureapp.controller.WebsiteController;

// ถ้าคุณมี SecurityConfig ที่ mapping ของคุณ “จำเป็นต้องใช้” ให้ปลดคอมเมนต์ 2 บรรทัดนี้
// import org.springframework.context.annotation.Import;
// @Import(SecurityConfig.class)

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Unit test (web slice) สำหรับ Spring Security flow หลัก:
 *  - ยังไม่ล็อกอิน: /home -> 302 ไป /login
 *  - ล็อกอินแล้ว: /home -> 200 OK
 *
 * ไม่แตะไฟล์ main, ไม่โหลดทั้งแอป — ใช้ @WebMvcTest เจาะเฉพาะชั้น MVC
 * และ exclude auto-config บางตัว (OAuth2/ResourceServer/SAML2) ที่มักลาก dependency จน context ล้ม
 */
@WebMvcTest(controllers = { WebsiteController.class })
@AutoConfigureMockMvc(addFilters = true)
@ImportAutoConfiguration(exclude = {
        OAuth2ClientAutoConfiguration.class,
        OAuth2ResourceServerAutoConfiguration.class,
        Saml2RelyingPartyAutoConfiguration.class
})
class SecurityWebMvcTest {

    @Autowired
    private MockMvc mvc;

    /**
     * เคส: เข้าหน้า protected (/home) โดย "ยังไม่ล็อกอิน"
     * คาดหวัง: ถูก redirect (302) ไปหน้า /login
     * ถ้า home ของคุณอยู่ path อื่น ให้เปลี่ยน "/home" ให้ตรงของจริง
     */
    @Test
    void home_should_redirect_to_login_when_unauthenticated() throws Exception {
        mvc.perform(get("/home"))
           .andExpect(status().is3xxRedirection())
           .andExpect(redirectedUrlPattern("**/login"));
    }

    /**
     * เคส: เข้าหน้า protected (/home) โดย "ล็อกอินแล้ว"
     * คาดหวัง: 200 OK
     * (ถ้าคืน view name/ข้อความเฉพาะ สามารถเพิ่ม assert view()/content() ได้)
     */
    @Test
    @WithMockUser(username = "user", roles = { "USER" })
    void home_should_return_200_when_authenticated() throws Exception {
        mvc.perform(get("/home").accept(MediaType.TEXT_HTML))
           .andExpect(status().isOk());
           // ตัวอย่างเสริม:
           // .andExpect(view().name("home"))
           // .andExpect(content().string(containsString("Welcome")))
    }

    /**
     * (เลือกใช้) ถ้าคุณมีหน้า login แบบ custom จริง ๆ (มี LoginController + login.html)
     * ให้ปลดคอมเมนต์ทดสอบนี้ได้; ถ้าใช้ default login ของ Spring Security ให้คอมเมนต์ทิ้งไว้
     */
    // @Test
    // void loginPage_should_be_public_200() throws Exception {
    //     mvc.perform(get("/login").accept(MediaType.TEXT_HTML))
    //        .andExpect(status().isOk())
    //        .andExpect(view().name("login"));
    // }
}