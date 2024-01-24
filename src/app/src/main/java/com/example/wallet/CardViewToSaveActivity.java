package com.example.wallet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CardViewToSaveActivity extends AppCompatActivity {
    User currentUser;

    boolean editExt_editable = false;

    boolean showSaveButton = true;

    Button b_saveButton;

    List<Card> cards;
    RecyclerView recyclerViewCards;
    CustomViewAdapterCards customViewAdapterCards;

    EditText et_firstname;
    EditText et_lastname;
    EditText et_iban;

    String cardName;
    String firstname;
    String lastname;
    String iban;
    double balance;

    BankCard bankCard;
    DriversLicense driversLicense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view_to_save);

        b_saveButton = findViewById(R.id.cardSaveButton);

        Intent intent = getIntent();
        if (intent != null) {
            User user = intent.getParcelableExtra("user");
            if (user != null) {
                currentUser = new User(user.getUsername(), user.getPassword(), user.getEmail());
                currentUser.setUserID(user.getUserID());
            }
            bankCard = intent.getParcelableExtra("card");
            if (bankCard != null) {
                setData();
            }
            driversLicense = intent.getParcelableExtra("d_card");
            if (driversLicense != null){
                setData();
            }
            else {
                System.out.println("kein intent gefunden");
            }

            String editable = intent.getStringExtra("editable");
            if(editable != null){
                if(editable.equals("true")){
                    this.editExt_editable = true;
                }
            }

            String saveButton = intent.getStringExtra("saveButton");
            if(saveButton != null){
                if(saveButton.equals("false")){
                    this.showSaveButton = false;
                    b_saveButton.setVisibility(View.INVISIBLE);
                }
            }

        }else {
            System.out.println("kein user gefunden");
        }

        et_firstname = findViewById(R.id.saveCard_firstName);
        et_lastname = findViewById(R.id.saveCard_lastName);
        et_iban = findViewById(R.id.saveCard_iban);

        recyclerViewCards = findViewById(R.id.recyclerView_cardSave_preview);
        recyclerViewCards.setLayoutManager(new LinearLayoutManager(this));

        setData();
        createCardAndFillTextViews();

        setEditableEditText(this.editExt_editable);

        addTextChangedListener();
    }

    private  void addTextChangedListener(){
        et_firstname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                updateCard();
            }
        });

        et_lastname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                updateCard();
            }
        });

        et_iban.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                updateCard();
            }
        });

    }

    private void setData(){

        if(bankCard != null){
            cardName = bankCard.getCardName();
            iban = bankCard.getIban();
            balance = 0.00;
            firstname = bankCard.getFirstName();
            lastname = bankCard.getLastName();
        }
        else if(driversLicense != null)
        {
            cardName = driversLicense.getCardName();
            firstname = driversLicense.getFirstName();
            lastname = driversLicense.getLastName();
        }
        else{
            cardName = "TestBank";
            iban = "8428112882246";
            balance = 0.00;
            firstname = "Max";
            lastname = "Mustermann";
        }

    }

    private void setEditableEditText(boolean editable){
        et_firstname.setEnabled(editable);
        et_lastname.setEnabled(editable);
        et_iban.setEnabled(editable);
    }

    public void buttonPressedSaveData(View v){
        if(saveData()) {
            finish();   // end Activity
            Intent intent = new Intent(CardViewToSaveActivity.this, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("user",currentUser);
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(), "Karte bereits in DB", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean saveData(){
        BankCard newBankCard = new BankCard(currentUser.getUserID(),
                cardName,
                iban,
                balance,
                firstname,
                lastname);
        DataBaseHelper db = new DataBaseHelper(CardViewToSaveActivity.this);
        if(!db.checkIfCardExists(currentUser.getUserID(),"BankCards",newBankCard)) {
            db.addBankCard(newBankCard);
            return true;
        }
        return false;
    }

    private void createCardAndFillTextViews(){
        createCard();
        fillTextViews();
    }

    private void createCard(){
        cards = new ArrayList<>();

        int userId = -1;
        if(currentUser != null){
            userId = currentUser.getUserID();
        }

        cards.add(new BankCard(userId,
                cardName,
                iban,
                balance,
                firstname,
                lastname));


        customViewAdapterCards = new CustomViewAdapterCards();
        customViewAdapterCards.add(cards);

        recyclerViewCards.setAdapter(customViewAdapterCards);
    }

    private void fillTextViews(){
        et_firstname.setText(firstname);
        et_lastname.setText(lastname);
        et_iban.setText(iban);
    }

    private void updateCard(){
        getTextFromViews();
        createCard();
    }

    private void getTextFromViews(){
        firstname = et_firstname.getText().toString();
        lastname = et_lastname.getText().toString();
        iban = et_iban.getText().toString();
    }

}
