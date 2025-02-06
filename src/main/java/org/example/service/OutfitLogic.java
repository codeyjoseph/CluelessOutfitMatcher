package org.example.service;

import java.util.ArrayList;
import java.util.Map;

public class OutfitLogic {
    public Map criteriaMap;

    public OutfitLogic(Map criteriaMap) {
        this.criteriaMap = criteriaMap;
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
