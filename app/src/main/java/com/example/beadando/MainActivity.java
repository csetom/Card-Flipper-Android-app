package com.example.beadando;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    Button StartLearningBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StartLearningBtn = (Button) findViewById(R.id.LearningBtn);
        Button NewCardBtn = findViewById(R.id.NewCardBtn);

        StartLearningBtn.setOnClickListener(v -> StartLearningActivity());
        NewCardBtn.setOnClickListener(v -> StartNewCard());
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