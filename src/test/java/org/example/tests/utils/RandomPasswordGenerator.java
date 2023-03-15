package org.example.tests.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Properties;

public class RandomPasswordGenerator {

    public static String generateRandomPassword()
    {
        // ASCII range – alphanumeric (0-9, a-z, A-Z)
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        // each iteration of the loop randomly chooses a character from the given
        // ASCII range and appends it to the `StringBuilder` instance

        for (int i = 0; i < 11; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
        return sb.toString();
    }

    public static String getRandomPassword() {
        String newPassword = RandomPasswordGenerator.generateRandomPassword();
        Properties prop = new Properties();
        FileInputStream input = null;
        try {
            input = new FileInputStream("src/test/java/configuration.properties");
        } catch (FileNotFoundException e) {
            System.out.println("The config file was NOT found.");
            return null;
        }
        try {
            prop.load(input);
        } catch (IOException e) {
            System.out.println("NOT able to load the config file.");
            return null;
        }
        prop.setProperty("password", newPassword);
        FileOutputStream output = null;
        try {
            output = new FileOutputStream("src/test/java/configuration.properties");
        } catch (FileNotFoundException e) {
            System.out.println("The config file was NOT found.");
            return null;
        }
        try {
            prop.store(output, null);
        } catch (IOException e) {
            System.out.println("NOT able to update the config file.");
            return null;
        }
        return newPassword;
    }
}
