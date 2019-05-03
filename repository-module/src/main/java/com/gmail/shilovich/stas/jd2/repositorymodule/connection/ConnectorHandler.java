package com.gmail.shilovich.stas.jd2.repositorymodule.connection;

import com.gmail.shilovich.stas.jd2.repositorymodule.exception.DatabaseException;
import com.gmail.shilovich.stas.jd2.repositorymodule.properties.DatabaseProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


@Repository
public class ConnectorHandler {

    private static final Logger logger = LogManager.getLogger(ConnectorHandler.class);
    private static final String ERROR_MESSAGE = "Connection failed";
    private final DatabaseProperties databaseProperties;

    @Autowired
    public ConnectorHandler(DatabaseProperties databaseProperties) {
        try {
            Class.forName(databaseProperties.getDriver());
        } catch (ClassNotFoundException e) {
            logger.error(ERROR_MESSAGE, e);
            throw new DatabaseException(ERROR_MESSAGE, e);
        }
        this.databaseProperties = databaseProperties;
    }

    public Connection getConnection() {
        try {
            Properties properties = new Properties();
            properties.setProperty("user", databaseProperties.getUsername());
            properties.setProperty("password", databaseProperties.getPassword());
            properties.setProperty("useUnicode", "true");
            properties.setProperty("characterEncoding", "cp1251");
            return DriverManager.getConnection(databaseProperties.getUrl(), properties);
        } catch (SQLException e) {
            logger.error(ERROR_MESSAGE, e);
            throw new DatabaseException(ERROR_MESSAGE, e);
        }
    }
}