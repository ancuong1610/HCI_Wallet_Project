package com.example.wallet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toolbar;

public class AddCardSelectionActivity extends AppCompatActivity {

    User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card_selection);

        Intent intent = getIntent();
        if (intent != null) {
            User user = intent.getParcelableExtra("user");
            if (user != null) {
                currentUser = new User(user.getUsername(), user.getPassword(), user.getEmail());
                currentUser.setUserID(user.getUserID());
            }
        }

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_add_card_selection);

    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.class, menu);
        return true;
    }
    //*/


    public void launchAddCardCameraFront(View v){
        Intent i = new Intent(this, AddCardCameraBackActivity.class);
        i.putExtra("user",currentUser);
        startActivity(i);
    }

    public void launchNfcReadyToRead(View v){
        Intent i = new Intent(this, NfcReadyToReadActivity.class);
        i.putExtra("user",currentUser);
        startActivity(i);
    }
}