package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.boot.SchemaAutoTooling;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.dialect.H2Dialect;
import org.hibernate.service.ServiceRegistry;

import java.sql.*;
import java.util.Properties;

public class Util {
    private static final String URL = "jdbc:mysql://localhost";
    private static final String USER = "root";
    private static final String PASSWORD = "123456789";
    private static Connection connection = null;
    private static SessionFactory sessionFactory = null;

    public static SessionFactory getSessionFactory() throws SQLException {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();

            Properties settings = new Properties();

            settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            settings.put(Environment.URL, URL);
            settings.put(Environment.USER, USER);
            settings.put(Environment.PASS, PASSWORD);
            settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
            settings.put(Environment.SHOW_SQL, "true");
            settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            settings.put(Environment.HBM2DDL_AUTO, "create-drop");

            configuration.setProperties(settings);
            configuration.addAnnotatedClass(User.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }

    public static void close() {
        try {
            if (connection != null) {
                connection.close();
            }
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        } catch (SQLException e) {
            System.err.println("Ошибка закрытия соединения: " + e);
        }
    }


}
