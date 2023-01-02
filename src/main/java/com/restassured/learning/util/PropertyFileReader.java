package com.restassured.learning.util;

import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Log4j2
public class PropertyFileReader {

    public static final String FILE_SEPARATOR = File.separator;
    public static final String PROJECT_DIRECTORY = System.getProperty("user.dir");
    public static final String CONFIG_PROPERTIES_FILE_PATH = PROJECT_DIRECTORY + FILE_SEPARATOR
            + "src" + FILE_SEPARATOR + "test" + FILE_SEPARATOR + "resources" + FILE_SEPARATOR + "config.properties";

    private PropertyFileReader() {


    }

    public static String propertyReader(String key) {

        Properties properties = new Properties();
        String value="";

        try {
            FileReader reader = new FileReader(CONFIG_PROPERTIES_FILE_PATH);
            properties.load(reader);
            value=properties.getProperty(key);

        } catch (IOException e) {
            log.error(e);
        }
        return value;

    }

}
