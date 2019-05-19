package com.gmail.shilovich.stas.jd2.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/*")
    public String getUser() {
        return "/home";
    }
}
