package com.example.beadando.Cards;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class LearningCard extends RealmObject {
    @PrimaryKey
    private int id;
    @Required
    private String ASide;
    @Required
    private String BSide;
    private double knowledge; /// 0 to 1, its a percent


    public LearningCard(int id, String ASide, String BSide, double knowledge) {
        this.id=id;
        this.ASide = ASide;
        this.BSide = BSide;
        this.knowledge = knowledge;

    }
    public LearningCard(String ASide, String BSide, double knowledge) {
        this.ASide = ASide;
        this.BSide = BSide;
        this.knowledge = knowledge;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public  LearningCard(){};
    public String getASide() {
        return ASide;
    }

    public String getBSide() {
        return BSide;
    }

    public void setASide(String ASide) {
        this.ASide = ASide;
    }

    public void setBSide(String BSide) {
        this.BSide = BSide;
    }

    public void setKnowledge(float knowledge) {
        this.knowledge = knowledge;
    }

    public double getKnowledge() {
        return knowledge;
    }

    public String getSide(CardSide side) throws Exception {
        switch (side){
            case A:
                return getASide();
            case B:
                return getBSide();
        };
        throw new Exception("There is no other sides");
    }
}
