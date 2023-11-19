package com.example.beadando;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.beadando.Cards.LearningCard;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class NewCardsActivity extends AppCompatActivity {

    Realm realm;
    int lastId=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_cards);
        Button save= findViewById(R.id.SaveBtn);
        RealmConfiguration config = new RealmConfiguration.Builder().allowWritesOnUiThread(true).name(getResources().getString(R.string.realmName)).build();
        realm = Realm.getInstance(config);
        Number max=realm.where(LearningCard.class).max("id");
        if (max==null)
            max=0;
        lastId=max.intValue();
        save.setOnClickListener(v -> {
            EditText SideA=(EditText) findViewById(R.id.editTextSideA);
            EditText SideB=(EditText) findViewById(R.id.editTextSideB);
            String sideAText=SideA.getText().toString();
            String sideBText=SideB.getText().toString();
            lastId++;
            LearningCard card = new LearningCard(lastId,sideAText,sideBText,0);
            realm.executeTransaction (transactionRealm -> transactionRealm.insert(card));
            CharSequence text=getResources().getText(R.string.saveDone);
            Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
            SideB.setText("");
            SideA.setText("");
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // the ui thread realm uses asynchronous transactions, so we can only safely close the realm
        // when the activity ends and we can safely assume that those transactions have completed
        realm.close();
    }
}