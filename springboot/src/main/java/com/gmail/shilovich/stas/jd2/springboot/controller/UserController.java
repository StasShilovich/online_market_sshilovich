package com.gmail.shilovich.stas.jd2.springboot.controller;

import com.gmail.shilovich.stas.jd2.servicemodule.RoleService;
import com.gmail.shilovich.stas.jd2.servicemodule.validator.UserDTOValidator;
import com.gmail.shilovich.stas.jd2.servicemodule.UserService;
import com.gmail.shilovich.stas.jd2.servicemodule.model.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.gmail.shilovich.stas.jd2.servicemodule.constant.ServiceModuleConstant.OBJECTS_ON_PAGE;

@Controller
@RequestMapping("/private")
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);


    private final UserService userService;
    private final RoleService roleService;
    private final UserDTOValidator userDTOValidator;

    @Autowired
    public UserController(UserService userService, RoleService roleService, UserDTOValidator userDTOValidator) {
        this.userService = userService;
        this.roleService = roleService;
        this.userDTOValidator = userDTOValidator;
    }

    @GetMapping("/users")
    public String getUser(
            Model model,
            @RequestParam(name = "page", defaultValue = "1") int page
    ) {
        List<UserDTO> users = userService.getUsers();
        model.addAttribute("users", users);
        model.addAttribute("page", page);
        List<String> roles = roleService.getRoleNameList();
        model.addAttribute("roles", roles);
        model.addAttribute("scale", OBJECTS_ON_PAGE);
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

    @GetMapping("/user/add")
    public String addUser() {
        return "/user";
    }

    @PostMapping("/users/add")
    public String addUser(
            @ModelAttribute("user") UserDTO userDTO,
            BindingResult bindingResult,
            Model model
    ) {
        userDTOValidator.validate(userDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            logger.info("Adding user is not complete!");
            return "/users";
        }
        userService.addUser(userDTO);
        model.addAttribute("user", userDTO);
        return "redirect:/private/users";
    }
}
