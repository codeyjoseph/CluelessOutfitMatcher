package org.example.program;

import org.example.model.ImageData;
import org.example.service.OutfitLogic;

import java.util.*;

public class CluelessOutfitMatcher {
    private final ImageData[] tops;
    private final ImageData[] bottoms;
    private final OutfitLogic outfitLogic;

    public CluelessOutfitMatcher(Properties topProperties, Properties bottomProperties) {
        tops = getImageData(topProperties);
        bottoms = getImageData(bottomProperties);
        String criteriaString = topProperties.getProperty("criteria");
        Map<String, ArrayList<String>> criteria = parseCriteria(criteriaString);
        outfitLogic = new OutfitLogic(criteria);
    }
    private ImageData[] getImageData(Properties properties) {
        String[] filepaths = properties.getProperty("images").split(";");
        String[] genres = properties.getProperty("genres").split(";");
        String[] colours = properties.getProperty("colours").split(";");
        String[] names = properties.getProperty("names").split(";");

        ImageData[] imageData = new ImageData[filepaths.length];
        for (int i = 0; i < imageData.length; i++) {
            imageData[i] = new ImageData(filepaths[i], genres[i], colours[i], names[i]);
        }
        return imageData;
    }

    private Map<String, ArrayList<String>> parseCriteria(String criteria) {
        Map<String, ArrayList<String>> criteriaMap = new HashMap<>();
        String[] criteriaPairs = criteria.split(";");
        for (String pair : criteriaPairs) {
            String[] keyValue = pair.split(":");
            String key = keyValue[0];
            ArrayList<String> values = new ArrayList<>(Arrays.asList(keyValue[1].split(",")));
            criteriaMap.put(key, values);
        }
        return criteriaMap;
    }

    public Boolean dressMe(int top, int bottom) {
        return outfitLogic.testOutfit(tops[top], bottoms[bottom]);
    }

    @Override
    public String toString() {
        String message = "Clueless Outfit Matcher, made by @codeyjoseph.\n";
        message += "The current criteria is as follows:\n" + outfitLogic.toString() + "\n\n";
        message += "Current fits:\n";
        for (int i = 0; i < tops.length; i++) {
            message += tops[i].toString() + "\n";
        }
        for (int i = 0; i < bottoms.length; i++) {
            message += bottoms[i].toString() + "\n";
        }
        return message;
    }
}
