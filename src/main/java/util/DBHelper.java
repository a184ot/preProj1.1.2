package util;

import model.User;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {

    private static DBHelper dbHelper;

    public static DBHelper getInstance() {
        if (dbHelper == null) {
            dbHelper = new DBHelper();
        }
        return dbHelper;
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

    public  Configuration getConfiguration() {
        return getMySqlConfiguration();
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
                    append("serverTimezone=").append(PropertyReader.getProperty("db.serverTimezone"));
            String url = urlS.toString();
            Connection connection = DriverManager.getConnection(url);
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    public Connection getConnection() {
        return mysqlConnection();
    }

}

