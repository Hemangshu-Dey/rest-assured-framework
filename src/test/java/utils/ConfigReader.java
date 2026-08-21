package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static final Properties prop;

    static {
        try {
            prop = new Properties();
            FileInputStream fis = new FileInputStream(
                    "src/test/resources/config/qa.properties"
            );
            prop.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getProperty(String key) {
        return prop.getProperty(key);
    }
}