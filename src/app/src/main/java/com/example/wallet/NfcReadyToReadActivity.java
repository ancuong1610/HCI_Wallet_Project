package com.example.wallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NfcReadyToReadActivity extends AppCompatActivity {

    User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc_ready_to_read);

        Intent intent = getIntent();
        if (intent != null) {
            User user = intent.getParcelableExtra("user");
            if (user != null) {
                currentUser = new User(user.getUsername(), user.getPassword(), user.getEmail());
                currentUser.setUserID(user.getUserID());
            }
        }
    }

    public void launchNfcReading(View v){
        Intent i = new Intent(this, NfcReadingActivity.class);
        i.putExtra("user",currentUser);
        startActivity(i);
    }
}