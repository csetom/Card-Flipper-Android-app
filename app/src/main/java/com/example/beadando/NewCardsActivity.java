package com.example.beadando;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import io.realm.RealmConfiguration;

public class NewCardsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_cards);
        Button save= findViewById(R.id.SaveBtn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RealmConfiguration config = new RealmConfiguration.Builder().name(getResources().getString(R.string.realmName)).build();

            }
        });
    }
}