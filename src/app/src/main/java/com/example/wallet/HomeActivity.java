package com.example.wallet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    List<Card> cards;
    List<Card> allCards;
    RecyclerView recyclerViewCards;
    CustomViewAdapterCards customViewAdapterCards;
    User currentUser;

    EditText searchFiled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        if (intent != null) {
            User user = intent.getParcelableExtra("user");
            if (user != null) {
                currentUser = new User(user.getUsername(), user.getPassword(), user.getEmail());
                currentUser.setUserID(user.getUserID());
            }
        }
        DataBaseHelper db = new DataBaseHelper(HomeActivity.this);
        cards = db.getAllCardsOfUserById(currentUser.getUserID());
        allCards = db.getAllCardsOfUserById(currentUser.getUserID());

        recyclerViewCards = findViewById(R.id.recyclerViewCardsHome);

        if (!cards.isEmpty()){
            customViewAdapterCards = new CustomViewAdapterCards();
            customViewAdapterCards.setUser(currentUser);
            customViewAdapterCards.add(cards);

            recyclerViewCards.setLayoutManager(new LinearLayoutManager(this));
            recyclerViewCards.setAdapter(customViewAdapterCards);
        }


        this.searchFiled = findViewById(R.id.editTextCardSearch);

        this.searchFiled.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });


        toggleHideFastFilterButton();
    }

    public void launchSettings(View v){
        Intent i = new Intent(this, SettingsActivity.class);
        startActivity(i);
    }

    public void launchAddCard(View v){
        Intent i = new Intent(this, AddCardSelectionActivity.class);
        i.putExtra("user",currentUser);
        startActivity(i);
    }
    public void launchCardViewToSave(View v){
        Intent i = new Intent(this, CardViewToSaveActivity.class);
        startActivity(i);
    }
    public void launchBank(View v){
        recyclerViewCards = findViewById(R.id.recyclerViewCardsHome);

        DataBaseHelper db = new DataBaseHelper(HomeActivity.this);
        cards.clear();
        cards.addAll(db.getBankCardsByUserID(currentUser.getUserID()));
        customViewAdapterCards = new CustomViewAdapterCards();
        customViewAdapterCards.add(cards);

        recyclerViewCards.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCards.setAdapter(customViewAdapterCards);


    }
    public void launchAuto(View v){
        recyclerViewCards = findViewById(R.id.recyclerViewCardsHome);

        DataBaseHelper db = new DataBaseHelper(HomeActivity.this);
        cards.clear();
        DriversLicense driver = db.getDriversLicenseByID(currentUser.getUserID());
        if (driver != null)
            cards.add(db.getDriversLicenseByID(currentUser.getUserID()));
        customViewAdapterCards = new CustomViewAdapterCards();
        customViewAdapterCards.add(cards);

        recyclerViewCards.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCards.setAdapter(customViewAdapterCards);


    }
    public void launchGesundheit(View v) {
        recyclerViewCards = findViewById(R.id.recyclerViewCardsHome);

        DataBaseHelper db = new DataBaseHelper(HomeActivity.this);
        cards.clear();
        if (db.getOrganDonorCardByID(currentUser.getUserID()) != null)
            cards.add(db.getOrganDonorCardByID(currentUser.getUserID()));
        customViewAdapterCards = new CustomViewAdapterCards();
        customViewAdapterCards.add(cards);

        recyclerViewCards.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCards.setAdapter(customViewAdapterCards);


    }
    public void launchFilter(View v) {
        recyclerViewCards = findViewById(R.id.recyclerViewCardsHome);

        DataBaseHelper db = new DataBaseHelper(HomeActivity.this);
        List<Card> newCards = db.getAllCardsOfUserById(currentUser.getUserID());

        if(newCards.size() != cards.size()) {
            cards.clear();
            cards = newCards;
            customViewAdapterCards = new CustomViewAdapterCards();
            customViewAdapterCards.add(cards);

            recyclerViewCards.setLayoutManager(new LinearLayoutManager(this));
            recyclerViewCards.setAdapter(customViewAdapterCards);
        }


        searchFiled.setText("");
        toggleHideFastFilterButton();
    }
    private void toggleHideFastFilterButton(){
        FrameLayout fl = findViewById(R.id.frameLayout_filterButtons);

        if(fl.getVisibility() == View.VISIBLE)
            fl.setVisibility(View.GONE);
        else
            fl.setVisibility(View.VISIBLE);
    }
    private void filter(String text){
        String tl = text.toLowerCase();

        ArrayList<Card> filteredList = new ArrayList<Card>();

        for(Card c : allCards){
            if(c instanceof BankCard){
                BankCard bc = (BankCard) c;
                if(bc.getIban().toLowerCase().contains(tl) ||
                        bc.getFirstName().toLowerCase().contains(tl)||
                        bc.getLastName().toLowerCase().contains(tl))
                    filteredList.add(c);

            }else if(c instanceof IdentityCard){
                IdentityCard ic = (IdentityCard) c;
                if(ic.getBirthday().toLowerCase().contains(tl) ||
                        ic.getBirthname().toLowerCase().contains(tl) ||
                        ic.getFirstName().toLowerCase().contains(tl) ||
                        ic.getLastName().toLowerCase().contains(tl) ||
                        ic.getId_Number().toLowerCase().contains(tl) ||
                        ic.getNationality().toLowerCase().contains(tl) ||
                        ic.getPlaceOfBirth().toLowerCase().contains(tl) )
                    filteredList.add(c);


            }else if(c instanceof DriversLicense){
                DriversLicense dl = (DriversLicense) c;
                if(dl.getBirthday().toLowerCase().contains(tl) ||
                        dl.getPlaceOfBirth().toLowerCase().contains(tl) ||
                        dl.getFirstName().toLowerCase().contains(tl) ||
                        dl.getLastName().toLowerCase().contains(tl) ||
                        dl.getDriversLicenseID().toLowerCase().contains(tl) ||
                        dl.getIssuedBy().toLowerCase().contains(tl) ||
                        dl.getValidFrom().toLowerCase().contains(tl) ||
                        dl.getValidUntil().toLowerCase().contains(tl))
                    filteredList.add(c);

            }else if(c instanceof OrganDonorCard){
                OrganDonorCard oc = (OrganDonorCard) c;
                if(oc.getBirthday().toLowerCase().contains(tl) ||
                oc.getFirstName().toLowerCase().contains(tl) ||
                oc.getLastName().toLowerCase().contains(tl) ||
                oc.getStreet_number().toLowerCase().contains(tl) ||
                oc.getPostalCode_city().toLowerCase().contains(tl))
                    filteredList.add(c);

            }
        }

        if(filteredList.isEmpty()){
            Toast.makeText(this,"Keine Karten mit diesem Inhalt gefunden.", Toast.LENGTH_LONG);
        }


        cards.clear();
        cards = filteredList;
        customViewAdapterCards = new CustomViewAdapterCards();
        customViewAdapterCards.add(cards);

        recyclerViewCards.setAdapter(customViewAdapterCards);


    }


}