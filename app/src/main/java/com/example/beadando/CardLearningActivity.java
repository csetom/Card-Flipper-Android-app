package com.example.beadando;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.beadando.Cards.CardSide;
import com.example.beadando.Cards.LearningCard;

import java.util.Vector;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class CardLearningActivity extends AppCompatActivity {
    private Vector<LearningCard> cards;
    private  TextView cardText,goodCardNumber,badCardNumber;
    private  LearningCard shownCard;
    private CardSide side;
    private GestureDetector gestureDetector;
    private Integer Good=0,Bad=0;
    Realm backgroundThreadRealm;
    Animation flipForward ,flipBackward;



    @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_learning);
        Log.v("info","Create Activity");

        String realmName = getResources().getString(R.string.realmName);
        RealmConfiguration config = new RealmConfiguration.Builder().name(realmName).build();
        backgroundThreadRealm = Realm.getInstance(config);


        cards = new Vector<>(backgroundThreadRealm.where(LearningCard.class).findAll());

        gestureDetector = new GestureDetector(this, new GestureListener());

        flipForward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.flip_forward);
        flipBackward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.flip_backward);
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
        cardText.setOnTouchListener((v, event) -> gestureDetector.onTouchEvent(event));

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // the ui thread realm uses asynchronous transactions, so we can only safely close the realm
        // when the activity ends and we can safely assume that those transactions have completed
        backgroundThreadRealm.close();
    }
    private void flipTheCard() {
        // Load forward and backward animations from XML


        // Start the forward animation
        // Set up an AnimatorListener to start the backward animation after the forward animation completes
        flipForward.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                switch (side){
                    case A:
                        side=CardSide.B;
                        break;
                    case B:
                        side=CardSide.A;
                }
                updateTextView();
                cardText.startAnimation(flipBackward);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        cardText.startAnimation(flipForward);

    }

    private void updateTextView() {
        // Set the text based on the currently visible side of the card
        try {
            cardText.setText(shownCard.getSide(side));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        // Reset the alpha to fully opaque
        cardText.setAlpha(1f);

        // Reset the scale to the original size
        cardText.setScaleX(1f);
        cardText.setScaleY(1f);
    }

    private void skipTheCard() {
        Toast.makeText(getApplicationContext(),getResources().getText(R.string.Skip), Toast.LENGTH_SHORT).show();
        cards.remove(shownCard);
        cards.add(shownCard);
        setNextCard();

    }

    private void GoodCard() {
        Toast.makeText(getApplicationContext(),getResources().getText(R.string.Good), Toast.LENGTH_SHORT).show();
        Good++;
        cards.remove(shownCard);
        setNextCard();
    }

    private void BadCard() {
        Toast.makeText(getApplicationContext(),getResources().getText(R.string.Bad), Toast.LENGTH_SHORT).show();
        Bad++;
        cards.remove(shownCard);
        setNextCard();

    }

    @SuppressLint("SetTextI18n")
    private  void setNextCard() {
        //Ending
        goodCardNumber.setText(Good+"");
        badCardNumber.setText(Bad+"");
        if (cards.size() == 0) {
            //GameEnded.
            cardText.setVisibility(View.GONE);
            cardText.setText("");
        } else {
            shownCard = cards.firstElement();
            side = CardSide.A;
            updateTextView();
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
        public boolean onDown(@NonNull MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            assert e1 != null;
            float distanceX = e2.getX() - e1.getX();
            float distanceY = e2.getY() - e1.getY();
            Animation cardAnimation=cardText.getAnimation();
            if (cardAnimation!=null && !cardText.getAnimation().hasEnded()) return false;
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

