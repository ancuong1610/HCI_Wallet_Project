package com.example.wallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.text.InputType;
import android.view.View;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private EditText editTextName, editTextPassword;
    private Button buttonLogin, buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!isAdminInDB())
            addAdminToDB();
        editTextName = findViewById(R.id.editTextName);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonRegister = findViewById(R.id.buttonRegister);

        editTextName.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String password = editTextPassword.getText().toString();
                User currentUser = getLoginUser(name,password);
                if (currentUser!=null) {
                    // Password is valid, perform login
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    intent.putExtra("user",currentUser);
                    startActivity(intent);
                } else {
                    // Password is not valid, display an error message
                    Toast.makeText(MainActivity.this, "Ungültiges Passwort! Bitte überprüfen Ihre Passwort und Benutzername", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });
    }
    public User getLoginUser(String uname, String pwd){
        DataBaseHelper db = new DataBaseHelper(MainActivity.this);
        List<User> userList = db.getAllUsers();
        for (int i = 0; i < userList.size(); i ++) {
            String userName = userList.get(i).getUsername();
            String password = userList.get(i).getPassword();
            if (Objects.equals(userName, uname) && Objects.equals(password, pwd)){
                String email = userList.get(i).getEmail();
                int id = userList.get(i).getUserID();
                User returnUser = new User(userName,password,email);
                returnUser.setUserID(id);
                return returnUser;
            }
        }
        return null;
    }
    public boolean isAdminInDB() {
        DataBaseHelper db = new DataBaseHelper(MainActivity.this);
        List<User> userArrayList = db.getAllUsers();
        for (User user : userArrayList) {
            if (user.getUsername().equals("admin")) {
                return true;
            }
        }
        return false;
    }
    public void addAdminToDB(){
        DataBaseHelper db = new DataBaseHelper(MainActivity.this);
        User testUser = new User("admin","admin", "thai@admin.com");
        db.addUser(testUser);

        DriversLicense testDriver = new DriversLicense(1,
                "Drivers Licence",
                "Erika",
                "Mustermann",
                "12.08.1983",
                "Berlin",
                "01.02.2011",
                "01.02.2026",
                "Fuehrerscheinstelle",
                "ID123456");
        db.addDriversLicense(testDriver);

        IdentityCard testIdentity = new IdentityCard(1,
                "TestIdentityCard",
                "L01X00T47",
                "Mustermann",
                "Gabler",
                "Erika",
                "12.08.1983",
                "Deutsch",
                "Berlin",
                "01.08.2031");
        db.addIdentityCard(testIdentity);

        OrganDonorCard testDonor = new OrganDonorCard(1,
                "TestOrganDonor",
                "Erika",
                "Mustermann",
                "12.08.1983",
                "Musterstrasse 3",
                "10243 Berlin");
        db.addOrganDonorCard(testDonor);

        BankCard testBank = new BankCard(1,
                "TestBankCard",
                "DE8428112882246",
                500.0,
                "Erika",
                "Mustermann");
        db.addBankCard(testBank);

        BankCard testBank2 = new BankCard(1,
                "TestBankCard",
                "DE56789044554632",
                500.0,
                "Erika",
                "Mustermann");
        db.addBankCard(testBank2);

    }
}


