package com.test.utils;

import com.test.config.ConfigProperties;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

@Log4j2
public class HibernateUtil {
    @Getter
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        log.debug("Building Hibernate SessionFactory");
        try {
            Properties properties = new Properties();
            properties.setProperty("hibernate.connection.url", ConfigProperties.getProperty("DB_URL"));
            properties.setProperty("hibernate.connection.username", ConfigProperties.getProperty("DB_USER"));
            properties.setProperty("hibernate.connection.password", ConfigProperties.getProperty("DB_PASS"));
            properties.setProperty("hibernate.default_schema", ConfigProperties.getProperty("DB_SCHEMA"));

            return new Configuration()
                    .addProperties(properties)
                    .configure()
                    .buildSessionFactory();
        } catch (Throwable ex) {
            log.error("Error building Hibernate SessionFactory", ex);
            throw new ExceptionInInitializerError("Initial SessionFactory creation failed: " + ex);
        }
    }
}
