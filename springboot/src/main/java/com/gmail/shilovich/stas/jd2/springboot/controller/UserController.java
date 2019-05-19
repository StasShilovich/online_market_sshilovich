package com.gmail.shilovich.stas.jd2.springboot.controller;

import com.gmail.shilovich.stas.jd2.servicemodule.UserService;
import com.gmail.shilovich.stas.jd2.servicemodule.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/private")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getUser(Model model) {
        List<UserDTO> users = userService.getUsers();
        model.addAttribute("users", users);
        return "/users";
    }

    @PostMapping("/users/update/{id}")
    public String changeRole(
            @RequestParam("role") String role,
            @PathVariable("id") Long id
    ) {
        userService.changeRole(id, role);
        return "redirect:/private/users";
    }
}
