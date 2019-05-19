package com.gmail.shilovich.stas.jd2.servicemodule.impl;

import com.gmail.shilovich.stas.jd2.repositorymodule.UserRepository;
import com.gmail.shilovich.stas.jd2.repositorymodule.model.User;
import com.gmail.shilovich.stas.jd2.servicemodule.UserService;
import com.gmail.shilovich.stas.jd2.servicemodule.converter.UserConverter;
import com.gmail.shilovich.stas.jd2.servicemodule.exception.ServiceException;
import com.gmail.shilovich.stas.jd2.servicemodule.model.AppUserPrincipal;
import com.gmail.shilovich.stas.jd2.servicemodule.model.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private static final String ERROR_MESSAGE = "User Service module operation failed";

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @Override
    public List<UserDTO> getUsers() {
        List<User> users;
        try (Connection connection = userRepository.getConnection()) {
            return getUsers(connection);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new ServiceException(ERROR_MESSAGE, e);
        }
    }

    @Override
    public void changeRole(Long id, String role) {
        try (Connection connection = userRepository.getConnection()) {
            changeRole(id, role, connection);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new ServiceException(ERROR_MESSAGE, e);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try (Connection connection = userRepository.getConnection()) {
            return loadUserDetails(email, connection);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new ServiceException(ERROR_MESSAGE, e);
        }
    }

    private UserDetails loadUserDetails(String email, Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        try {
            User user = userRepository.loadUserByEmail(email, connection);
            UserDTO userDTO = userConverter.toDTO(user);
            connection.commit();
            return new AppUserPrincipal(userDTO);
        } catch (SQLException e) {
            connection.rollback();
            logger.error(e.getMessage(), e);
            throw new ServiceException(ERROR_MESSAGE, e);
        }
    }

    private void changeRole(Long id, String role, Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        try {
            userRepository.changeRole(id, role, connection);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            logger.error(e.getMessage(), e);
            throw new ServiceException(ERROR_MESSAGE, e);
        }
    }

    private List<UserDTO> getUsers(Connection connection) throws SQLException {
        List<User> users;
        connection.setAutoCommit(false);
        try {
            users = userRepository.getUsers(connection);
            List<UserDTO> list = users.stream()
                    .map(userConverter::toDTO)
                    .collect(Collectors.toList());
            connection.commit();
            return list;
        } catch (SQLException e) {
            connection.rollback();
            logger.error(e.getMessage(), e);
            throw new ServiceException(ERROR_MESSAGE, e);
        }
    }
}
