package com.example.beadando;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    Button StartLearningBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StartLearningBtn = (Button) findViewById(R.id.LearningBtn);
        Button NewCardBtn = findViewById(R.id.NewCardBtn);

        StartLearningBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartLearningActivity();
            }
        });
        NewCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartNewCard();
            }
        });
        Realm.init(getApplicationContext());
    }


    public  void StartNewCard () {
        Intent intent = new Intent(this, NewCardsActivity.class);
        startActivity(intent);
    }
    public void StartLearningActivity (){
        Intent intent = new Intent(this, CardLearningActivity.class);
        Log.d("info","New activity");
        startActivity(intent);
    }
}