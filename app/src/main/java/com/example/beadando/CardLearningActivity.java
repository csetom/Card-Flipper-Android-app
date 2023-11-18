package com.example.beadando;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.beadando.Cards.CardChooser;
import com.example.beadando.Cards.CardSide;
import com.example.beadando.Cards.LearningCard;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Vector;

public class CardLearningActivity extends AppCompatActivity {
    private Vector<LearningCard> cards= new Vector<LearningCard>();
    private  TextView cardText;
    private  LearningCard shownCard;
    private CardSide side;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_learning);
        Log.d("info","Create Activity");

        cards.add(new LearningCard("0","A",0.1));
        cards.add(new LearningCard("1","B",0.2));
        cards.add(new LearningCard("2","C",0.3));
        cards.add(new LearningCard("3","D",0.4));
        cards.add(new LearningCard("4","E",0.5));
        cards.add(new LearningCard("5","F",0.6));

        side=CardSide.A;
        cardText = (TextView) findViewById(R.id.cardText);
       // cardChooser = new CardChooser(cards);
        shownCard= cards.firstElement();
        try {
            cardText.setText(shownCard.getSide(side));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Button skipBtn= (Button)findViewById(R.id.SkipBtn);

        cardText.setOnClickListener(new View.OnClickListener() {
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
                try {
                    skipTheCard();
                 } catch (Exception e) {
                    throw new RuntimeException(e);
                 }
            }
        });

        // Log.d("Activity on Create","LearningCard");
    }

    private void flipTheCard() throws Exception {
        switch (side){
            case A:
                side=CardSide.B;
            break;
            case B:
                side=CardSide.A;
        }
        cardText.setText(shownCard.getSide(side));
    }

    private void skipTheCard() throws Exception {
        cards.remove(shownCard);
        cards.add(shownCard);
        shownCard=cards.firstElement();
        side=CardSide.A; // TODO: megcsinalni, hogy legyen default side.
        cardText.setText(shownCard.getSide(side));
    }
}