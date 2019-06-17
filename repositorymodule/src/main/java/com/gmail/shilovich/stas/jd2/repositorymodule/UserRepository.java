package com.gmail.shilovich.stas.jd2.repositorymodule;

import com.gmail.shilovich.stas.jd2.repositorymodule.model.Role;
import com.gmail.shilovich.stas.jd2.repositorymodule.model.User;

import java.sql.Connection;
import java.util.List;

public interface UserRepository extends GenericRepository<Long, User> {

    List<User> getUsers();

    void changeRole(Long id, Role role);

    User loadUserByEmail(String email);
}
