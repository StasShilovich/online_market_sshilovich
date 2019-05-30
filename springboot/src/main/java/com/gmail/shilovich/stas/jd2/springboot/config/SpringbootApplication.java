package com.gmail.shilovich.stas.jd2.springboot.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(scanBasePackages = "com.gmail.shilovich.stas.jd2",
        exclude = UserDetailsServiceAutoConfiguration.class)
@EntityScan("com.gmail.shilovich.stas.jd2.repositorymodule.model")
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

}
