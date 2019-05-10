package com.gmail.shilovich.stas.jd2.repositorymodule.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class LiquibaseConfig {

    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:migrations/db_user_1.xml");
        liquibase.setChangeLog("classpath:migrations/db_user_insert_1.xml");
        liquibase.setChangeLog("classpath:migrations/db_user_insert_2.xml");
        liquibase.setChangeLog("classpath:migrations/db_user_insert_3.xml");
        liquibase.setDataSource(dataSource);
        return liquibase;
    }
}
