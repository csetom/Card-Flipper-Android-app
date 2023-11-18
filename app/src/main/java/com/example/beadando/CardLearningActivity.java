package com.example.beadando;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.beadando.Cards.CardSide;
import com.example.beadando.Cards.LearningCard;

import java.util.Vector;

public class CardLearningActivity extends AppCompatActivity {
    private Vector<LearningCard> cards= new Vector<LearningCard>();
    private  TextView cardText,goodCardNumber,badCardNumber;
    private  LearningCard shownCard;
    private CardSide side;
    private GestureDetector gestureDetector;
    private Integer Good=0,Bad=0;


    @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
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



        gestureDetector = new GestureDetector(this, new GestureListener());

        side=CardSide.A;
        cardText = (TextView) findViewById(R.id.cardText);
        goodCardNumber = (TextView) findViewById(R.id.goodCardNumber);
        badCardNumber = (TextView) findViewById(R.id.badCardNumber);
        goodCardNumber.setText(Good+"");
        badCardNumber.setText(Bad+"");
       // cardChooser = new CardChooser(cards);
        shownCard= cards.firstElement();
        try {
            cardText.setText(shownCard.getSide(side));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        cardText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });

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
        setNextCard();

    }

    private void GoodCard() throws Exception {
        Good++;
        cards.remove(shownCard);
        setNextCard();
    }

    private void BadCard() throws Exception {
        Bad++;
        cards.remove(shownCard);
        setNextCard();
    }

    @SuppressLint("SetTextI18n")
    private  void setNextCard() throws Exception {
        //Ending
        goodCardNumber.setText(Good+"");
        badCardNumber.setText(Bad+"");
        if (cards.size()<=0) {
            //GameEnded.
            cardText.setVisibility(View.GONE);
        } else {
            shownCard = cards.firstElement();
            side = CardSide.A; // TODO: megcsinalni, hogy legyen default side.
            cardText.setText(shownCard.getSide(side));
        }
    }
    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onSingleTapUp(@NonNull MotionEvent e) {
            try {
                flipTheCard();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            return super.onSingleTapUp(e);
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float distanceX = e2.getX() - e1.getX();
            float distanceY = e2.getY() - e1.getY();

            if (Math.abs(distanceX) > Math.abs(distanceY)
                    && Math.abs(distanceX) > SWIPE_THRESHOLD
                    && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                // Right swipe
                if (distanceX > 0) {
                    try {
                        GoodCard();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                // Left swipe
                else {
                    try {
                        BadCard();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }

                return true;
            } else if (Math.abs(distanceY) > Math.abs(distanceX)
                    && Math.abs(distanceY) > SWIPE_THRESHOLD
                    && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    try {
                        skipTheCard();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
            }

            return false;
        }
    }
}

