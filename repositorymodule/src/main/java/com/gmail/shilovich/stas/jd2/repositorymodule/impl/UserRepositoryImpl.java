package com.gmail.shilovich.stas.jd2.repositorymodule.impl;

import com.gmail.shilovich.stas.jd2.repositorymodule.UserRepository;
import com.gmail.shilovich.stas.jd2.repositorymodule.exception.DatabaseException;
import com.gmail.shilovich.stas.jd2.repositorymodule.model.Role;
import com.gmail.shilovich.stas.jd2.repositorymodule.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl extends GenericRepositoryImpl implements UserRepository {
    private static final Logger logger = LogManager.getLogger(UserRepositoryImpl.class);
    private static final String ERROR_MESSAGE = "Data module operation failed";

    @Override
    public List<User> getUsers(Connection connection) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT U.F_ID,U.F_EMAIL,U.F_PASSWORD,U.F_NAME,U.F_SURNAME,U.F_PATRONYMIC,U.F_DELETED, R.F_NAME FROM T_USER U JOIN T_ROLE R on U.F_ROLE_ID = R.F_ID";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet set = statement.executeQuery()) {
                while (set.next()) {
                    users.add(getUser(set));
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DatabaseException(ERROR_MESSAGE, e);
        }
        return users;
    }

    @Override
    public void changeRole(Long id, String role, Connection connection) {
        String sql = "UPDATE T_USER U SET U.F_ROLE_ID=(SELECT R.F_ID FROM T_ROLE R WHERE R.F_NAME=?) WHERE U.F_ID=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, role);
            statement.setString(2, String.valueOf(id).toUpperCase());
            int row = statement.executeUpdate();
            logger.info("Role " + role + " update at id " + id + " row affected " + row);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DatabaseException(ERROR_MESSAGE, e);
        }
    }

    @Override
    public User loadUserByEmail(String email, Connection connection) {
        User user = new User();
        String sql = "SELECT U.F_ID,U.F_EMAIL,U.F_PASSWORD,U.F_NAME,U.F_SURNAME,U.F_PATRONYMIC,U.F_DELETED, R.F_NAME FROM T_USER U JOIN T_ROLE R on U.F_ROLE_ID = R.F_ID WHERE U.F_EMAIL=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email.trim());
            try (ResultSet set = statement.executeQuery()) {
                while (set.next()) {
                    return getUser(set);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DatabaseException(ERROR_MESSAGE, e);
        }
        return user;
    }

    private User getUser(ResultSet set) throws SQLException {
        User user = new User();
        user.setId(set.getLong("U.F_ID"));
        user.setEmail(set.getString("U.F_EMAIL"));
        user.setPassword(set.getString("U.F_PASSWORD"));
        user.setName(set.getString("U.F_NAME"));
        user.setSurname(set.getString("U.F_SURNAME"));
        user.setPatronymic(set.getString("U.F_PATRONYMIC"));
        user.setDeleted(set.getBoolean("U.F_DELETED"));
        Role role = new Role();
        role.setName(set.getString("R.F_NAME"));
        user.setRole(role);
        return user;
    }

}
