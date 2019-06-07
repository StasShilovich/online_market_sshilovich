package com.gmail.shilovich.stas.jd2.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/private")
public class LoginController {

    @GetMapping("/login")
    public String getUser() {
        return "/login";
    }
}
