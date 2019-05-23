package com.gmail.shilovich.stas.jd2.repositorymodule;

import com.gmail.shilovich.stas.jd2.repositorymodule.model.User;

import java.sql.Connection;
import java.util.List;

public interface UserRepository extends GenericRepository<Long,User> {

    List<User> getUsers(Connection connection);

    void changeRole(Long id, String role, Connection connection);

    User loadUserByEmail(String email, Connection connection);
}
