package com.servicebuilder.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
public class HibernateConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();

        driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        driverManagerDataSource.setUsername("root");
        driverManagerDataSource.setPassword("root"); //should be encrypted
        driverManagerDataSource.setUrl(
                "jdbc:mysql://localhost:3307/application?createDatabaseIfNotExist=false" +
                        "&useSSL=false&allowPublicKeyRetrieval=true"); //should be encrypted

        return driverManagerDataSource;
    }
}
