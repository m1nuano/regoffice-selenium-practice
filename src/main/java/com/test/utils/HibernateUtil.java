package com.test.utils;

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
            properties.setProperty("hibernate.connection.url", System.getenv("DB_URL"));
            properties.setProperty("hibernate.connection.username", System.getenv("DB_USER"));
            properties.setProperty("hibernate.connection.password", System.getenv("DB_PASS"));
            properties.setProperty("hibernate.default_schema", "reg_office");

            return new Configuration()
                    .addProperties(properties)
                    .configure()
                    .buildSessionFactory();
        } catch (Throwable ex) {
            log.error("Error building Hibernate SessionFactory", ex);
            throw new ExceptionInInitializerError("Initial SessionFactory creation failed: " + ex);
        }

    }

    public static void shutdown() {
        log.debug("Shutting down Hibernate SessionFactory");
        getSessionFactory().close();
    }
}
