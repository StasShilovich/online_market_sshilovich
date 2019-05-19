package com.gmail.shilovich.stas.jd2.servicemodule.converter.impl;

import com.gmail.shilovich.stas.jd2.repositorymodule.model.Role;
import com.gmail.shilovich.stas.jd2.repositorymodule.model.User;
import com.gmail.shilovich.stas.jd2.servicemodule.converter.UserConverter;
import com.gmail.shilovich.stas.jd2.servicemodule.model.UserDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserConverterImpl implements UserConverter {
    @Override
    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setPatronymic(user.getPatronymic());
        userDTO.setRole(user.getRole().getName());
        userDTO.setDeleted(user.isDeleted());
        return userDTO;
    }

    @Override
    public User fromDTO(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setPatronymic(userDTO.getPatronymic());
        Role role = new Role();
        role.setName(userDTO.getRole());
        user.setRole(role);
        user.setDeleted(userDTO.isDeleted());
        return user;
    }
}
