package com.gmail.shilovich.stas.jd2.springbootmodule.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.gmail.shilovich.stas.jd2")
public class SpringBootModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootModuleApplication.class, args);
    }
}
