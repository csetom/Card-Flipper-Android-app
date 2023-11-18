package com.example.beadando;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button StartLearningBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StartLearningBtn = (Button) findViewById(R.id.LearningBtn);
        StartLearningBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartLearningActivity();
            }
        });
    }
    public void StartLearningActivity (){
        Intent intent = new Intent(this, CardLearningActivity.class);
        Log.d("info","New activity");
        startActivity(intent);
    }
}