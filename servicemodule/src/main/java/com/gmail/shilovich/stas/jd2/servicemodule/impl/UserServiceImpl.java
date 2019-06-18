package com.gmail.shilovich.stas.jd2.servicemodule.impl;

import com.gmail.shilovich.stas.jd2.repositorymodule.RoleRepository;
import com.gmail.shilovich.stas.jd2.repositorymodule.UserRepository;
import com.gmail.shilovich.stas.jd2.repositorymodule.model.Role;
import com.gmail.shilovich.stas.jd2.repositorymodule.model.User;
import com.gmail.shilovich.stas.jd2.servicemodule.UserService;
import com.gmail.shilovich.stas.jd2.servicemodule.converter.UserConverter;
import com.gmail.shilovich.stas.jd2.servicemodule.model.AppUserPrincipal;
import com.gmail.shilovich.stas.jd2.servicemodule.model.LoginDTO;
import com.gmail.shilovich.stas.jd2.servicemodule.model.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private static final String ERROR_MESSAGE = "User Service module operation failed";

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            UserConverter userConverter,
            RoleRepository roleRepository
    ) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public List<UserDTO> getUsers() {
        List<User> users = userRepository.getUsers();
        List<UserDTO> userDTOS = users.stream()
                .map(userConverter::toDTO)
                .collect(Collectors.toList());
        for (UserDTO userDTO : userDTOS) {
            logger.info(userDTO.toString());
        }
        return userDTOS;
    }

    @Override
    @Transactional
    public void changeRole(Long id, String name) {
        Role role = roleRepository.findRoleByName(name);
        userRepository.changeRole(id, role);
    }

    @Override
    @Transactional
    public void addUser(UserDTO userDTO) {
        User user = userConverter.fromDTO(userDTO);
        userRepository.persist(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.loadUserByEmail(email);
        LoginDTO loginDTO = userConverter.toLoginDTO(user);
        return new AppUserPrincipal(loginDTO);
    }
}
