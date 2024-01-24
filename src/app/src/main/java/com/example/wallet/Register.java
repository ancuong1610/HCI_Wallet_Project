package com.example.wallet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    private EditText editTextEmail, editTextUsername, editTextPassword, editTextRepeatPassword;
    private Button buttonRegister;
    private CheckBox agreementCheck;
    private TextView emailWarn, usernameWarn, passWarn, repeatpassWarn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextRepeatPassword = findViewById(R.id.editTextRepeatPassword);
        buttonRegister = findViewById(R.id.buttonRegister);

        emailWarn = findViewById(R.id.emailWarn);
        usernameWarn = findViewById(R.id.usernameWarm);
        passWarn = findViewById(R.id.passWarn);
        repeatpassWarn = findViewById(R.id.repeatpassWarn);

        showSnackbar();
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                showSnackbar();
                String email = editTextEmail.getText().toString();
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();
                String repeatPassword = editTextRepeatPassword.getText().toString();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(repeatPassword)) {
                    emailWarn.setVisibility(View.VISIBLE);
                    emailWarn.setText("E-Mail erfordelich");
                    usernameWarn.setVisibility(View.VISIBLE);
                    usernameWarn.setText("Benutzername erfordelich");
                    passWarn.setVisibility(View.VISIBLE);
                    passWarn.setText("Passwort erfordelich");
                    repeatpassWarn.setVisibility(View.VISIBLE);
                    repeatpassWarn.setText("Passwort wiederholen erfordelich");
                    return;
                }

                if (!isValidEmail(email)) {
                    emailWarn.setVisibility(View.VISIBLE);
                    emailWarn.setText("Falsches E-Mail-Format");
                    return;
                }

                if (!isValidPassword(password)) {
                    passwordHint();
                    passWarn.setVisibility(View.VISIBLE);
                    passWarn.setText("Falsches Passwort-Format");
                    return;
                }

                if (!password.equals(repeatPassword)) {
                    repeatpassWarn.setVisibility(View.VISIBLE);
                    repeatpassWarn.setText("Passwort wiederholen erfordelich");
                    return;
                }
                // Registration logic
                DataBaseHelper db = new DataBaseHelper(Register.this);
                db.addUser(new User(username,password,email));

                Intent intent = new Intent(Register.this, MainActivity.class);
                startActivity(intent);

            }
        });


    }

    private boolean isValidEmail(String email) {
        // Simple email validation using regex
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidPassword(String password) {
        // Check password length
        if (password.length() < 8 || password.length() > 16) {
            return false;
        }

        // Check for at least one uppercase letter, one lowercase letter, and one digit
        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isUpperCase(c)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowercase = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            }

            // Check for three consecutive characters
            if (i > 1 && password.charAt(i) == password.charAt(i - 1) + 1 &&
                    password.charAt(i - 1) == password.charAt(i - 2) + 1) {
                return false;
            }
        }

        if (!hasUppercase || !hasLowercase || !hasDigit) {
            return false;
        }

        // Check for common keyboard layout patterns
        String[] forbiddenPatterns = {"qwerty", "asdfg", "zxcvb", "qazwsx", "1q2w3e4r", "12345"};
        for (String pattern : forbiddenPatterns) {
            if (password.toLowerCase().contains(pattern)) {
                return false;
            }
        }

        // Check for spaces
        if (password.contains(" ")) {
            return false;
        }
        return true;
    }

    private void showSnackbar(){
        emailWarn.setVisibility(View.GONE);
        usernameWarn.setVisibility(View.GONE);
        passWarn.setVisibility(View.GONE);
        repeatpassWarn.setVisibility(View.GONE);
    }

    private void passwordHint() {
        Toast.makeText(Register.this, "Das Passwort muss mindestens 6 bis 18 Zeichen enthalten. Eine Kombination aus Großbuchstaben, Kleinbuchstaben, Zahlen und Symbolen.", Toast.LENGTH_LONG).show();
    }



}
