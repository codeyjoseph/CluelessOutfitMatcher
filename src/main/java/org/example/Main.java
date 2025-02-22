package org.example;

import org.example.gui.Program;
import org.example.program.CluelessOutfitMatcher;

import java.io.FileInputStream;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties topProperties = readProperties("src/main/resources/topcat.properties");
        Properties bottomProperties = readProperties("src/main/resources/bottomcat.properties");
        if (topProperties == null || bottomProperties == null) {
            return ;
        }
        CluelessOutfitMatcher matcher = new CluelessOutfitMatcher(topProperties, bottomProperties);
        System.out.println(matcher);
        boolean verdict = matcher.dressMe(0, 0);
        if (verdict)
            System.out.println("It's a match");
        else
            System.out.println("X NO MATCH X");
        Program prog = new Program(matcher);
        prog.run();
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