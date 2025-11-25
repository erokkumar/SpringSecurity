package com.comp.erok.learning.Controller;

import com.comp.erok.learning.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/send-email")
    public String sendEmail() {
        emailService.sendSimpleMail(
                "rokkumar77@gmail.com",
                "Test Email",
                "Hello! This is a test email."
        );

        return "Email sent!";
    }
}

