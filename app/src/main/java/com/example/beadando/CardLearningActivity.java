package com.example.beadando;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.beadando.Cards.CardSide;
import com.example.beadando.Cards.LearningCard;

import java.util.List;
import java.util.Vector;

public class CardLearningActivity extends AppCompatActivity {
    private  List<LearningCard> cards= new Vector<LearningCard>();
    private  TextView cardText;
    private  LearningCard shownCard;
    private  Integer index=0;
    private CardSide side;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_learning);
        Log.d("info","Create Activity");
        cards.add(new LearningCard("Alma","Barack",0));
        cards.add(new LearningCard("Akorte","Bkorte",0));
        side=CardSide.A;

        cardText = (TextView) findViewById(R.id.cardText);
        shownCard= cards.get(0);
        try {
            cardText.setText(shownCard.getSide(side));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Button flipBtn= (Button)findViewById(R.id.Flipbtn);
        Button skipBtn= (Button)findViewById(R.id.SkipBtn);

        flipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    flipTheCard();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index<cards.size()-1){
                    index++;
                } else {
                    index = 0;
                }
                side=CardSide.A; // TODO: megcsinalni, hogy legyen default side.
                shownCard=cards.get(index);
                try {
                    cardText.setText(shownCard.getSide(side));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });



        // Log.d("Activity on Create","LearningCard");
    }

    public void flipTheCard() throws Exception {
        switch (side){
            case A:
                side=CardSide.B;
            break;
            case B:
                side=CardSide.A;
        }
        cardText.setText(shownCard.getSide(side));
    }
}