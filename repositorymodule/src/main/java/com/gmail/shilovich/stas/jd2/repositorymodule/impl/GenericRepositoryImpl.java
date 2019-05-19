package com.gmail.shilovich.stas.jd2.repositorymodule.impl;

import com.gmail.shilovich.stas.jd2.repositorymodule.GenericRepository;
import com.gmail.shilovich.stas.jd2.repositorymodule.exception.DatabaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class GenericRepositoryImpl implements GenericRepository {

    private static Logger logger = LoggerFactory.getLogger(GenericRepositoryImpl.class);

    @Autowired
    private DataSource dataSource;

    @Override
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DatabaseException("Some troubles with getting connection", e);
        }
    }
}
