package org.example.service;

import org.example.model.ImageData;

import java.util.ArrayList;
import java.util.Map;

public class OutfitLogic {
    public Map<String, ArrayList<String>> criteriaMap;

    public OutfitLogic(Map<String, ArrayList<String>> criteriaMap) {
        this.criteriaMap = criteriaMap;
    }

    public boolean testOutfit(ImageData top, ImageData bottom) {
        return criteriaMap.get(top.getGenre()).contains(bottom.getGenre());
    }

    @Override
    public String toString() {
        StringBuilder message = new StringBuilder("Criteria:\n");
        criteriaMap.forEach((k, v) -> {
            message.append(k).append(": ").append(v).append("\n");
        });
        return message.toString();
    }
}
