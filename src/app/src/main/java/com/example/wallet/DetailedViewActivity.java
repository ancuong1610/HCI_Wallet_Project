package com.example.wallet;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DetailedViewActivity extends AppCompatActivity {
    User currentUser;
    Button b_nfcButton;
    List<Card> cards;
    RecyclerView recyclerViewCards;
    CustomViewAdapterCards customViewAdapterCards;
    String cardName;
    String firstname;
    String lastname;
    String iban;
    double balance;

    BankCard bankCard;
    DriversLicense driversLicense;
    OrganDonorCard organDonorCard;
    IdentityCard identityCard;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_view);
        linearLayout = findViewById(R.id.linear_text_fields);
        b_nfcButton = findViewById(R.id.nfcButton);

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
            organDonorCard = intent.getParcelableExtra("o_card");
            if (organDonorCard != null)
                setData();
            identityCard = intent.getParcelableExtra("i_card");
            if (identityCard != null)
                setData();
            else {
                System.out.println("kein intent gefunden");
            }

        }else {
            System.out.println("kein user gefunden");
        }

        recyclerViewCards = findViewById(R.id.recyclerView_cardSave_preview);
        recyclerViewCards.setLayoutManager(new LinearLayoutManager(this));

        setData();
        createCard();
    }

    private void setData(){

        if(bankCard != null){
            fillBankLinear();
        }
        else if(driversLicense != null)
        {
            fillDriversLicenceLinear();
        }
        else if(organDonorCard != null)
        {
            fillOrganDonorLinear();
        }
        else if (identityCard != null) {
            fillIDLinear();

        } else{
            cardName = "TestBank";
            iban = "8428112882246";
            balance = 0.00;
            firstname = "Max";
            lastname = "Mustermann";
        }

    }

    private TextView getNewTextView(){
        TextView newTextView = new TextView(this);
        newTextView.setTextColor(Color.GRAY);
        return newTextView;
    }

    private EditText getNewEditText(){
        EditText newEditText = new EditText(this);
        newEditText.setEnabled(false);
        newEditText.setHintTextColor(Color.WHITE);
        return newEditText;
    }

    private void fillBankLinear(){
        linearLayout.removeAllViews();
        int childCount = linearLayout.getChildCount();
        for (int i = childCount - 1; i >= 0; i--) {
            View childView = linearLayout.getChildAt(i);
            if (childView instanceof EditText) {
                linearLayout.removeViewAt(i);
            }
        }
        TextView newTextView = getNewTextView();
        newTextView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newTextView.setText("Balance");
        linearLayout.addView(newTextView);


        EditText newEditText = getNewEditText();
        newEditText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newEditText.setHint(Double.toString(bankCard.getCurrentBalance()));
        linearLayout.addView(newEditText);

        newTextView = getNewTextView();
        newTextView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newTextView.setText("IBAN");
        linearLayout.addView(newTextView);


        newEditText = getNewEditText();
        newEditText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newEditText.setHint(bankCard.getIban());
        linearLayout.addView(newEditText);

        newTextView = getNewTextView();
        newTextView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newTextView.setText("Vorname");
        linearLayout.addView(newTextView);


        newEditText = getNewEditText();
        newEditText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newEditText.setHint(bankCard.getFirstName());
        linearLayout.addView(newEditText);

        newTextView = getNewTextView();
        newTextView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newTextView.setText("Nachname");
        linearLayout.addView(newTextView);


        newEditText = getNewEditText();
        newEditText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newEditText.setHint(bankCard.getLastName());
        linearLayout.addView(newEditText);
    }
    private void fillIDLinear(){
        linearLayout.removeAllViews();
        int childCount = linearLayout.getChildCount();
        for (int i = childCount - 1; i >= 0; i--) {
            View childView = linearLayout.getChildAt(i);
            if (childView instanceof EditText) {
                linearLayout.removeViewAt(i);
            }
        }
        TextView newTextView = getNewTextView();
        newTextView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newTextView.setText("Ausweisnummer");
        linearLayout.addView(newTextView);


        EditText newEditText = getNewEditText();
        newEditText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newEditText.setHint(identityCard.getId_Number());
        linearLayout.addView(newEditText);

        newTextView = getNewTextView();
        newTextView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newTextView.setText("Name");
        linearLayout.addView(newTextView);


        newEditText = getNewEditText();
        newEditText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newEditText.setHint(identityCard.getLastName());
        linearLayout.addView(newEditText);

        newTextView = getNewTextView();
        newTextView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newTextView.setText("Geburtsname");
        linearLayout.addView(newTextView);


        newEditText = getNewEditText();
        newEditText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newEditText.setHint(identityCard.getLastName());
        linearLayout.addView(newEditText);

        newTextView = getNewTextView();
        newTextView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newTextView.setText("Vorname");
        linearLayout.addView(newTextView);


        newEditText = getNewEditText();
        newEditText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newEditText.setHint(identityCard.getFirstName());
        linearLayout.addView(newEditText);

        newTextView = getNewTextView();
        newTextView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newTextView.setText("Geburtstag");
        linearLayout.addView(newTextView);


        newEditText = getNewEditText();
        newEditText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newEditText.setHint(identityCard.getBirthday());
        linearLayout.addView(newEditText);

        newTextView = getNewTextView();
        newTextView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newTextView.setText("Staatsangehörigkeit");
        linearLayout.addView(newTextView);

        newEditText = getNewEditText();
        newEditText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newEditText.setHint(identityCard.getNationality());
        linearLayout.addView(newEditText);

        newTextView = getNewTextView();
        newTextView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newTextView.setText("Geburtsort");
        newTextView.setTextColor(Color.WHITE);
        linearLayout.addView(newTextView);


        newEditText = getNewEditText();
        newEditText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newEditText.setHint(identityCard.getPlaceOfBirth());
        linearLayout.addView(newEditText);

        newTextView = getNewTextView();
        newTextView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newTextView.setText("Gültig bis");
        linearLayout.addView(newTextView);

        newEditText = getNewEditText();
        newEditText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newEditText.setHint(identityCard.getValidUntil());
        linearLayout.addView(newEditText);

        newTextView = getNewTextView();
        newTextView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newTextView.setText("Straße und Hausnummer");
        linearLayout.addView(newTextView);

        newEditText = getNewEditText();
        newEditText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newEditText.setHint(identityCard.getStreet_number());
        linearLayout.addView(newEditText);

        newTextView = getNewTextView();
        newTextView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newTextView.setText("Postleitzahl + Ort");
        linearLayout.addView(newTextView);

        newEditText = getNewEditText();
        newEditText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newEditText.setHint(identityCard.getPostalCode_city());
        linearLayout.addView(newEditText);
    }
    private void fillDriversLicenceLinear(){
        linearLayout.removeAllViews();
        int childCount = linearLayout.getChildCount();
        for (int i = childCount - 1; i >= 0; i--) {
            View childView = linearLayout.getChildAt(i);
            if (childView instanceof EditText) {
                linearLayout.removeViewAt(i);
            }
        }
        TextView newTextView = getNewTextView();
        newTextView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newTextView.setText("Ausweisnummer");
        linearLayout.addView(newTextView);


        EditText newEditText = getNewEditText();;
        newEditText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newEditText.setHint(driversLicense.getDriversLicenseID());
        linearLayout.addView(newEditText);

        newTextView = getNewTextView();
        newTextView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newTextView.setText("Name");
        linearLayout.addView(newTextView);

        newEditText = getNewEditText();;
        newEditText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newEditText.setHint(driversLicense.getLastName());
        linearLayout.addView(newEditText);

        newTextView = getNewTextView();
        newTextView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newTextView.setText("Vorname");
        linearLayout.addView(newTextView);


        newEditText = getNewEditText();;
        newEditText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newEditText.setHint(driversLicense.getFirstName());
        linearLayout.addView(newEditText);

        newTextView = getNewTextView();
        newTextView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newTextView.setText("Geburtstag");
        linearLayout.addView(newTextView);

        newEditText = getNewEditText();;
        newEditText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newEditText.setHint(driversLicense.getBirthday());
        linearLayout.addView(newEditText);

        newTextView = getNewTextView();
        newTextView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newTextView.setText("Geburtsort");
        linearLayout.addView(newTextView);

        newEditText = getNewEditText();;
        newEditText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newEditText.setHint(driversLicense.getPlaceOfBirth());
        linearLayout.addView(newEditText);

        newTextView = getNewTextView();
        newTextView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newTextView.setText("Gültig ab");
        linearLayout.addView(newTextView);

        newEditText = getNewEditText();;
        newEditText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newEditText.setHint(driversLicense.getValidFrom());
        linearLayout.addView(newEditText);

        newTextView = getNewTextView();
        newTextView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newTextView.setText("Gültig bis");
        linearLayout.addView(newTextView);

        newEditText = getNewEditText();;
        newEditText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newEditText.setHint(driversLicense.getValidUntil());
        linearLayout.addView(newEditText);

        newTextView = getNewTextView();
        newTextView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newTextView.setText("Ausgestellt von");
        linearLayout.addView(newTextView);

        newEditText = getNewEditText();
        newEditText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newEditText.setHint(driversLicense.getIssuedBy());
        linearLayout.addView(newEditText);
    }
    private void fillOrganDonorLinear(){
        linearLayout.removeAllViews();
        int childCount = linearLayout.getChildCount();
        for (int i = childCount - 1; i >= 0; i--) {
            View childView = linearLayout.getChildAt(i);
            if (childView instanceof EditText) {
                linearLayout.removeViewAt(i);
            }
        }
        TextView newTextView = getNewTextView();
        newTextView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newTextView.setText("Name");
        linearLayout.addView(newTextView);


        EditText newEditText = getNewEditText();
        newEditText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newEditText.setHint(organDonorCard.getLastName());
        linearLayout.addView(newEditText);

        newTextView = getNewTextView();
        newTextView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newTextView.setText("Vorname");
        linearLayout.addView(newTextView);


        newEditText = getNewEditText();
        newEditText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newEditText.setHint(organDonorCard.getFirstName());
        linearLayout.addView(newEditText);

        newTextView = getNewTextView();
        newTextView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newTextView.setText("Geburtstag");
        linearLayout.addView(newTextView);

        newEditText = getNewEditText();
        newEditText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newEditText.setHint(organDonorCard.getBirthday());
        linearLayout.addView(newEditText);

        newTextView = getNewTextView();
        newTextView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newTextView.setText("Straße");
        linearLayout.addView(newTextView);

        newEditText = getNewEditText();
        newEditText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newEditText.setHint(organDonorCard.getStreet_number());
        linearLayout.addView(newEditText);

        newTextView = getNewTextView();
        newTextView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newTextView.setText("PLZ, Wohnort");
        newEditText.setEnabled(false);
        linearLayout.addView(newTextView);

        newEditText = getNewEditText();
        newEditText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newEditText.setHint(organDonorCard.getPostalCode_city());
        linearLayout.addView(newEditText);

    }
    private void createCard(){
        cards = new ArrayList<>();

        if (bankCard != null)
            cards.add(bankCard);
        else if (driversLicense != null)
            cards.add(driversLicense);
        else if (organDonorCard != null)
            cards.add(organDonorCard);
        else if (identityCard != null) {
            cards.add(identityCard);
        }
        customViewAdapterCards = new CustomViewAdapterCards();
        customViewAdapterCards.add(cards);

        recyclerViewCards.setAdapter(customViewAdapterCards);
    }

    public void onXButtonClick(View view){
        finish(); // End current activity
    }
    public void onNFCButtonClick(View view){
        Button temp = findViewById(R.id.nfcButton);
        if (temp.getText().equals(getString(R.string.nfc_aktivieren)))
            temp.setText(getString(R.string.nfc_deaktivieren));
        else
            temp.setText(getString(R.string.nfc_aktivieren));
        //TO DO: NFC-Function
    }

}
