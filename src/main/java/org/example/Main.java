package org.example;

import org.example.program.CluelessOutfitMatcher;

import java.io.FileInputStream;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties topProperties = readProperties("src/main/resources/tops.properties");
        Properties bottomProperties = readProperties("src/main/resources/bottoms.properties");
        if (topProperties == null || bottomProperties == null) {
            return ;
        }
        CluelessOutfitMatcher matcher = new CluelessOutfitMatcher(topProperties, bottomProperties);
        System.out.println(matcher);
    }

    public static Properties readProperties(String filepath) {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(filepath)) {
            properties.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return properties;
    }
}