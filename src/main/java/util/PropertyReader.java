package util;

import java.io.IOException;
import java.util.Properties;

public class PropertyReader {


    public static String getProperty(String name)  {
        Properties property = new Properties();
        try {
            property.load(PropertyReader.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
        }
        return property.getProperty(name);
    }

}