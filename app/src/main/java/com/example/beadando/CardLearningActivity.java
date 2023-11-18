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
    private  Integer index=0;
    private CardSide side;
    private Button flipBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_learning);
        Log.d("info","Create Activity");
        cards.add(new LearningCard("Alma","Barack",0));
        cards.add(new LearningCard("Akorte","Bkorte",0));
        side=CardSide.A;

        cardText = (TextView) findViewById(R.id.cardText);
        LearningCard card = cards.get(0);
        cardText.setText(card.getASide());
        flipBtn= (Button)findViewById(R.id.Flipbtn);
        flipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipTheCard();
            }

        });



        // Log.d("Activity on Create","LearningCard");
    }

    public void flipTheCard(){
        LearningCard card = cards.get(index);
        switch (side){
            case A:
                side=CardSide.B;
                cardText.setText(card.getBSide());
                Log.d("info","Side A");
                return;
            case B:
                side=CardSide.A;
                cardText.setText(card.getASide());
                Log.d("info","Side B");
                
            break;
        }
    }
}