package util;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    public static String getProperty(String name) throws IOException {
        FileInputStream fis;
        Properties property = new Properties();
        FileReader reader = new FileReader("config.properties");
        Properties properties = new Properties();
        properties.load(reader);

        System.out.println(properties.getProperty("user"));
        String host = property.getProperty("db.host");
        String login = property.getProperty("db.login");
        String password = property.getProperty("db.password");
        String propertyResponse = property.getProperty(name);
        return propertyResponse;
    }
}