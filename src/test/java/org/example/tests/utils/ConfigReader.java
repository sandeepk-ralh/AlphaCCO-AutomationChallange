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
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("configuration.properties not found at " + propertyFilePath);
        }
    }

    public static String getBaseUrl() {
        String baseUrl = properties.getProperty("baseUrl");
        if(baseUrl != null) {
            return baseUrl;
        } else {
            System.out.println("baseUrl not specified in the configuration.properties file.");
            return null;
        }
    }

    public static String getEmailAlphaCCO() {
        String emailAlphaCCO = properties.getProperty("emailAlphaCCO");
        if(emailAlphaCCO != null) {
            return emailAlphaCCO;
        } else {
            System.out.println("emailAlphaCCO not specified in the configuration.properties file.");
            return null;
        }
    }

    public static String getPassword() {
        String password = properties.getProperty("password");
        if(password != null) {
            return password;
        } else {
            System.out.println("password not specified in the configuration.properties file.");
            return null;
        }
    }

    public static String getInvalidUsername() {
        String invalidEmail = properties.getProperty("invalidEmail");
        if(invalidEmail != null) {
            return invalidEmail;
        } else {
            System.out.println("invalidEmail not specified in the configuration.properties file.");
            return null;
        }
    }

    public static String getInvalidPassword() {
        String invalidPassword = properties.getProperty("invalidPassword");
        if(invalidPassword != null) {
            return invalidPassword;
        } else {
            System.out.println("invalidPassword not specified in the configuration.properties file.");
            return null;
        }
    }

    public static String getNewPassword() {
        String newPassword = properties.getProperty("password");
        if(newPassword != null) {
            return newPassword;
        } else {
            System.out.println("newPassword not specified in the configuration.properties file.");
            return null;
        }
    }

    public static String getOutlookPassword() {
        String outlookPassword = properties.getProperty("outlookPassword");
        if(outlookPassword != null) {
            return outlookPassword;
        } else {
            System.out.println("outlookPassword not specified in the configuration.properties file.");
            return null;
        }
    }

    public static String getOutlookUrl() {
        String outlookUrl = properties.getProperty("outlookUrl");
        if(outlookUrl != null) {
            return outlookUrl;
        } else {
            System.out.println("outlookUrl not specified in the configuration.properties file.");
            return null;
        }
    }
}
