package org.example.model;

public class ImageData {
    private final String filepath;
    private final String genre;
    private final String colour;
    private final String name;

    public ImageData(String filepath, String genre, String colour, String name) {
        this.filepath = filepath;
        this.genre = genre;
        this.colour = colour;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("filepath=%s\ngenre=%s\ncolour=%s\nname=%s", filepath, genre, colour, name);
    }

    public String getFilepath() {
        return filepath;
    }

    public String getGenre() {
        return genre;
    }

    public String getColour() {
        return colour;
    }

    public String getName() {
        return name;
    }
}
