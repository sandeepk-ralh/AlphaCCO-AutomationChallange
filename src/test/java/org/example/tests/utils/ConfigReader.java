package org.example.tests.utils;

import java.io.*;
import java.util.Properties;

public class ConfigReader {

    protected static Properties properties;

    public ConfigReader() {
        BufferedReader reader;
        String propertyFilePath = "src/test/java/configuration.properties";
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                System.out.println("Loading config file failed." + e.getMessage());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("configuration.properties file not found at " + propertyFilePath);
        }
    }

    public static String getBaseUrl() {
        return properties.getProperty("baseUrl");
    }

    public static String getEmailAlphaCCO() {
        return properties.getProperty("emailAlphaCCO");
    }

    public static String getPassword() {
        return properties.getProperty("password");
    }

    public static String getInvalidUsername() {
        return properties.getProperty("invalidEmail");
    }

    public static String getInvalidPassword() {
        return properties.getProperty("invalidPassword");
    }

    public static String getNewPassword() {
        return properties.getProperty("password");
    }

    public static String getOutlookPassword() {
        return properties.getProperty("outlookPassword");
    }

    public static String getOutlookUrl() {
        return properties.getProperty("outlookUrl");
    }
}
