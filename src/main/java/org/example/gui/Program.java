package org.example.gui;

import org.example.model.ImageData;
import org.example.program.CluelessOutfitMatcher;

import javax.swing.*;
import static javax.swing.JOptionPane.showMessageDialog;
import java.awt.*;
import java.io.FileInputStream;
import java.util.Properties;

public class Program {
    private final CluelessOutfitMatcher logic;
    private JFrame frame;
    private int topIndex;
    private int bottomIndex;
    private final int topConstraint;
    private final int bottomConstraint;

    public Program(CluelessOutfitMatcher logic) {
        this.logic = logic;
        topConstraint = this.logic.getTops().length - 1;
        bottomConstraint = this.logic.getTops().length - 1;
        topIndex = 0;
        bottomIndex = 0;
        createFrame();
    }

    public static void main(String[] args) {
        Program prog = new Program(new CluelessOutfitMatcher(readProperties("src/main/resources/topcat.properties"), readProperties("src/main/resources/bottomcat.properties")));
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

    public void run() {
        frame.setVisible(true);
    }

    private void createFrame() {
        // make frame
        // fill it with buttons, images and popups.
        // add function to the buttons
        // set what's visible and what isn't
        // customise appearance
        frame = new JFrame("Joe's Wardrobe");
        frame.setSize(1000, 1000);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding

        JButton dressMeButton = new JButton("Dress Me");
        JButton nextTopButton = new JButton(">>");
        JButton previousTopButton = new JButton("<<");
        JButton nextBottomButton = new JButton(">>");
        JButton previousBottomButton = new JButton("<<");

        String topFilepath = logic.getTops()[0].getFilepath();
        String bottomFilepath = logic.getBottoms()[0].getFilepath();
        ImageIcon topIcon = new ImageIcon(topFilepath);
        ImageIcon bottomIcon = new ImageIcon(bottomFilepath);

        Image top = resizeImage(topIcon);
        ImageIcon topImage = new ImageIcon(top);
        Image bottom = resizeImage(bottomIcon);
        ImageIcon bottomImage = new ImageIcon(bottom);

        JLabel topLabel = new JLabel(topImage);
        JLabel bottomLabel = new JLabel(bottomImage);

        dressMeButton.setPreferredSize(new Dimension(200, 50));
        nextTopButton.setPreferredSize(new Dimension(80, 50));
        previousTopButton.setPreferredSize(new Dimension(80, 50));
        nextBottomButton.setPreferredSize(new Dimension(80, 50));
        previousBottomButton.setPreferredSize(new Dimension(80, 50));

        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(previousTopButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        frame.add(topLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        frame.add(nextTopButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(previousBottomButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        frame.add(bottomLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        frame.add(nextBottomButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        frame.add(dressMeButton, gbc);

        dressMeButton.addActionListener(event -> {
            if (logic.dressMe(topIndex, bottomIndex))
                JOptionPane.showMessageDialog(frame, "IT'S A MATCH");
            else
                JOptionPane.showMessageDialog(frame, "NO MATCH");
        });

        nextTopButton.addActionListener(event -> {
            if (topIndex == topConstraint)
                topIndex = 0;
            else
                topIndex++;
            ImageIcon updatedImage = new ImageIcon(logic.getTops()[topIndex].getFilepath());
            Image resized = resizeImage(updatedImage);
            topLabel.setIcon(new ImageIcon(resized));
        });

        previousTopButton.addActionListener(event -> {
            if (topIndex == 0)
                topIndex = topConstraint;
            else
                topIndex--;
            ImageIcon updatedImage = new ImageIcon(logic.getTops()[topIndex].getFilepath());
            Image resized = resizeImage(updatedImage);
            topLabel.setIcon(new ImageIcon(resized));
        });

        nextBottomButton.addActionListener(event -> {
            if (bottomIndex == bottomConstraint)
                bottomIndex = 0;
            else
                bottomIndex++;
            ImageIcon updatedImage = new ImageIcon(logic.getBottoms()[bottomIndex].getFilepath());
            Image resized = resizeImage(updatedImage);
            bottomLabel.setIcon(new ImageIcon(resized));
        });

        previousBottomButton.addActionListener(event -> {
            if (bottomIndex == 0)
                bottomIndex = bottomConstraint;
            else
                bottomIndex--;
            ImageIcon updatedImage = new ImageIcon(logic.getBottoms()[bottomIndex].getFilepath());
            Image resized = resizeImage(updatedImage);
            bottomLabel.setIcon(new ImageIcon(resized));
        });
    }

    private Image resizeImage(ImageIcon original) {
        return original.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
    }

}
