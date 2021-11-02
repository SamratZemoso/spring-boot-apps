package com.security.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {

    @GetMapping("/admin")
    public String welcomeAdmin() {
        return "Welcome Admin !!";
    }

}
