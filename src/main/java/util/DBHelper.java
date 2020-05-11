package util;

import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }
        return sessionFactory;
    }


    private static Configuration getMySqlConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);

        configuration.setProperty("hibernate.dialect", PropertyReader.getProperty("h.dialect"));
        configuration.setProperty("hibernate.connection.driver_class", PropertyReader.getProperty("db.driver"));
        StringBuilder urlH = new StringBuilder();
        urlH.append(PropertyReader.getProperty("db.type")).
                append(PropertyReader.getProperty("db.host")).append(":").
                append(PropertyReader.getProperty("db.port")).append("/").
                append(PropertyReader.getProperty("db.name")).append("?").
                append("serverTimezone=").append(PropertyReader.getProperty("db.serverTimezone"));
        String hURL = urlH.toString();
        configuration.setProperty("hibernate.connection.url", hURL);
        configuration.setProperty("hibernate.connection.username", PropertyReader.getProperty("db.username"));
        configuration.setProperty("hibernate.connection.password", PropertyReader.getProperty("db.password"));
        configuration.setProperty("hibernate.show_sql", PropertyReader.getProperty("h.show_sql"));
        configuration.setProperty("hibernate.hbm2ddl.auto", PropertyReader.getProperty("h.hbm2ddl.auto"));
        configuration.setProperty("hibernate.current_session_context_class", "thread");
        return configuration;
    }



    private static SessionFactory createSessionFactory() {
        Configuration configuration = getMySqlConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }


    private static Connection mysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName(PropertyReader.getProperty("db.driver")).newInstance());
            StringBuilder urlS = new StringBuilder();
            urlS.
                    append(PropertyReader.getProperty("db.type")).
                    append(PropertyReader.getProperty("db.host")).append(":").
                    append(PropertyReader.getProperty("db.port")).append("/").
                    append(PropertyReader.getProperty("db.name")).append("?").
                    append("user=").append(PropertyReader.getProperty("db.username")).append("&").          //login
                    append("password=").append(PropertyReader.getProperty("db.password")).append("&").
                    append("&serverTimezone=").append(PropertyReader.getProperty("db.serverTimezone"));
            String url = urlS.toString();
            Connection connection = DriverManager.getConnection(url);
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    public static Connection getMysqlConnection() {
        return mysqlConnection();
    }

}

