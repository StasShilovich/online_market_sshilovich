package com.gmail.shilovich.stas.jd2.repositorymodule.impl;

import com.gmail.shilovich.stas.jd2.repositorymodule.UserRepository;
import com.gmail.shilovich.stas.jd2.repositorymodule.exception.DatabaseException;
import com.gmail.shilovich.stas.jd2.repositorymodule.model.Role;
import com.gmail.shilovich.stas.jd2.repositorymodule.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class UserRepositoryImpl extends GenericRepositoryImpl<Long, User> implements UserRepository {
    private static final Logger logger = LogManager.getLogger(UserRepositoryImpl.class);
    private static final String ERROR_MESSAGE = "Data module operation failed";

    @Override
    public List<User> getUsers() {
        List<User> all = super.findAll(0, 100);
        return all;
    }

    @Override
    public void changeRole(Long id, Role role) {
        String hql = "UPDATE User U SET role=:role WHERE U.id=:id";
        Query query = entityManager.createQuery(hql);
        query.setParameter("id", id);
        query.setParameter("role", role);
        int row = query.executeUpdate();
        if (row == 0) {
            throw new DatabaseException("Row affected " + row + " when " + role + " update.");
        }

    }

    @Override
    public User loadUserByEmail(String email) {
        String hql = "FROM User AS U WHERE U.email=:email";
        Query query = entityManager.createQuery(hql);
        query.setParameter("email", email);
        return (User) query.getSingleResult();
    }

}
