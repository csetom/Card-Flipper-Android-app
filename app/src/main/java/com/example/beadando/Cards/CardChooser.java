package com.example.beadando.Cards;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.stream.Collectors;


public class CardChooser {

    private List<LearningCard> objectList;
    private Random random;

    public CardChooser(List<LearningCard> objectList) {
        this.objectList = objectList;
        this.random = new Random();
    }


    public LearningCard getRandomObject(){
        return getRandomObject(null);
    }
    public LearningCard getRandomObject(LearningCard actualCard) {
        double totalProbability = 0;
        List<LearningCard> filteredList = actualCard == null ? objectList : objectList.stream().filter(card -> !card.equals(actualCard)).collect(Collectors.toList());
        for (LearningCard obj : filteredList) {
            totalProbability += obj.getKnowledge();
        }

        // Generate a random number between 0 and the total probability
        double randomValue = random.nextDouble() * totalProbability;

        // Iterate through the objects and find the one corresponding to the random value
        double cumulativeProbability = 0;
        for (LearningCard obj : filteredList) {
            cumulativeProbability += obj.getKnowledge();
            if (randomValue <= cumulativeProbability) {
                return obj;
            }
        }

        // This should not happen, but if there's an issue, return null or handle it accordingly
        return actualCard;
    }
}

