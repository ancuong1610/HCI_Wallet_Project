package com.example.wallet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String USER_ID = "UserID";
    public static final String Table_USER_LIST = "UserList";
    public static final String Table_DRIVERS_LICENSES = "DriversLicenses";
    public static final String Table_IDENTITY_CARDS = "IdentityCards";
    public static final String Table_ORGAN_DONOR_CARDS = "OrganDonorCards";
    public static final String Table_BANK_CARDS = "BankCards";
    public static final String Column_USERNAME = "Username";
    public static final String Column_Password = "Password";
    public static final String Column_FIRSTNAME = "Vorname";
    public static final String Column_LASTNAME = "Nachname";
    public static final String Column_BIRTHDAY = "Geburtstag";
    public static final String Column_BIRTHPLACE = "Geburtsort";
    public static final String Column_VALID_FROM = "GueltigAb";
    public static final String Column_VALID_UNTIL = "GueltigBis";
    public static final String Column_ISSUED_BY = "ausgestelltVon";
    public static final String Column_DRIVING_LICENSE_ID = "Ausweisnummer";
    public static final String Column_NATIONALITY = "Nationalitaet";
    public static final String Column_IDENTITY_CARD_ID = "Ausweisnr";
    public static final String Column_STREET = "StrasseHausnr";
    public static final String Column_POSTALCODE_CITY = "PLZOrt";
    public static final String Column_IBAN = "IBAN";
    public static final String Column_BALANCE = "Kontostand";
    public static final String Column_EMAIL = "Email";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "wallet.db", null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + Table_USER_LIST + " ("
                + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Column_USERNAME + " TEXT UNIQUE, "
                + Column_Password + " TEXT, "
                + Column_EMAIL + " TEXT UNIQUE )";
        db.execSQL(createTableStatement);

        createTableStatement = "CREATE TABLE " + Table_DRIVERS_LICENSES + " ("
                + USER_ID + " INTEGER PRIMARY KEY REFERENCES " + Table_USER_LIST + " (" + USER_ID + "), "
                + Column_FIRSTNAME + " TEXT, "
                + Column_LASTNAME + " TEXT, "
                + Column_BIRTHDAY + " TEXT, "
                + Column_BIRTHPLACE + " TEXT, "
                + Column_VALID_FROM + " TEXT, "
                + Column_VALID_UNTIL + " TEXT, "
                + Column_ISSUED_BY + " TEXT, "
                + Column_DRIVING_LICENSE_ID + " TEXT UNIQUE)";
        db.execSQL(createTableStatement);

        createTableStatement = "CREATE TABLE " + Table_IDENTITY_CARDS + " ("
                + USER_ID + " INTEGER PRIMARY KEY REFERENCES " + Table_USER_LIST + " (" + USER_ID + "), "
                + Column_IDENTITY_CARD_ID + " TEXT UNIQUE,"
                + Column_FIRSTNAME + " TEXT, "
                + Column_LASTNAME + " TEXT, "
                + Column_BIRTHDAY + " TEXT, "
                + Column_BIRTHPLACE + " TEXT, "
                + Column_STREET + " TEXT, "
                + Column_POSTALCODE_CITY + " TEXT, "
                + Column_NATIONALITY + " TEXT, "
                + Column_VALID_UNTIL + " TEXT, "
                + Column_ISSUED_BY + " TEXT)";
        db.execSQL(createTableStatement);

        createTableStatement = "CREATE TABLE " + Table_ORGAN_DONOR_CARDS + " ("
                + USER_ID + " INTEGER PRIMARY KEY REFERENCES " + Table_USER_LIST + " (" + USER_ID + "), "
                + Column_FIRSTNAME + " TEXT, "
                + Column_LASTNAME + " TEXT, "
                + Column_BIRTHDAY + " TEXT, "
                + Column_STREET + " TEXT, "
                + Column_POSTALCODE_CITY + " TEXT)";

        db.execSQL(createTableStatement);

        createTableStatement = "CREATE TABLE " + Table_BANK_CARDS + " ("
                + USER_ID + " INTEGER REFERENCES " + Table_USER_LIST + " (" + USER_ID + "), "
                + Column_FIRSTNAME + " TEXT, "
                + Column_LASTNAME + " TEXT, "
                + Column_BALANCE + " REAL, "
                + Column_IBAN + " TEXT PRIMARY KEY UNIQUE)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + Table_USER_LIST);
        db.execSQL("DROP TABLE " + Table_BANK_CARDS);
        db.execSQL("DROP TABLE " + Table_IDENTITY_CARDS);
        db.execSQL("DROP TABLE " + Table_DRIVERS_LICENSES);
        db.execSQL("DROP TABLE " + Table_ORGAN_DONOR_CARDS);
        onCreate(db);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Column_USERNAME, user.getUsername());
        cv.put(Column_Password, user.getPassword());
        cv.put(Column_EMAIL, user.getEmail());

        db.insert(Table_USER_LIST, null, cv);
        db.close();
    }

    public void addDriversLicense(DriversLicense newDriver) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USER_ID, newDriver.getUserID());
        cv.put(Column_FIRSTNAME, newDriver.getFirstName());
        cv.put(Column_LASTNAME, newDriver.getLastName());
        cv.put(Column_BIRTHDAY, newDriver.getBirthday());
        cv.put(Column_BIRTHPLACE, newDriver.getPlaceOfBirth());
        cv.put(Column_VALID_FROM, newDriver.getValidFrom());
        cv.put(Column_VALID_UNTIL, newDriver.getValidUntil());
        cv.put(Column_DRIVING_LICENSE_ID, newDriver.getDriversLicenseID());
        cv.put(Column_ISSUED_BY, newDriver.getIssuedBy());

        db.insert(Table_DRIVERS_LICENSES, null, cv);
    }

    public void addIdentityCard(IdentityCard newID_Card) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USER_ID, newID_Card.getUserID());
        cv.put(Column_IDENTITY_CARD_ID, newID_Card.getId_Number());
        cv.put(Column_FIRSTNAME, newID_Card.getFirstName());
        cv.put(Column_LASTNAME, newID_Card.getLastName());
        cv.put(Column_BIRTHDAY, newID_Card.getBirthday());
        cv.put(Column_BIRTHPLACE, newID_Card.getPlaceOfBirth());
        cv.put(Column_VALID_UNTIL, newID_Card.getValidUntil());
        cv.put(Column_NATIONALITY, newID_Card.getNationality());
        cv.put(Column_ISSUED_BY, newID_Card.getIssuedBy());
        cv.put(Column_STREET, newID_Card.getStreet_number());
        cv.put(Column_POSTALCODE_CITY, newID_Card.getPostalCode_city());

        db.insert(Table_IDENTITY_CARDS, null, cv);
    }

    public void addOrganDonorCard(OrganDonorCard newDonor) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USER_ID, newDonor.getUserID());
        cv.put(Column_FIRSTNAME, newDonor.getFirstName());
        cv.put(Column_LASTNAME, newDonor.getLastName());
        cv.put(Column_BIRTHDAY, newDonor.getBirthday());
        cv.put(Column_STREET, newDonor.getStreet_number());
        cv.put(Column_POSTALCODE_CITY, newDonor.getPostalCode_city());

        db.insert(Table_ORGAN_DONOR_CARDS, null, cv);
    }

    public void addBankCard(BankCard newBank) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USER_ID, newBank.getUserID());
        cv.put(Column_FIRSTNAME, newBank.getFirstName());
        cv.put(Column_LASTNAME, newBank.getLastName());
        cv.put(Column_IBAN, newBank.getIban());
        cv.put(Column_BALANCE, newBank.getCurrentBalance());

        db.insert(Table_BANK_CARDS, null, cv);
    }

    public List<User> getAllUsers() {
        List<User> returnList = new ArrayList<>();
        String selectQuery = "Select * FROM " + Table_USER_LIST;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int userID = cursor.getInt(0);
                String username = cursor.getString(1);
                String password = cursor.getString(2);
                String email = cursor.getString(3);
                User newUser = new User(username, password, email);
                newUser.setUserID(userID);
                returnList.add(newUser);

            } while (cursor.moveToNext());
        } else {
        }
        cursor.close();
        db.close();
        return returnList;
    }

    public IdentityCard getIdentityCardByID(int id) {
        String selectQuery = "Select * FROM " + Table_IDENTITY_CARDS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        IdentityCard temp = null;
        if (cursor.moveToFirst()) {
            do {
                if (cursor.getInt(0) == id) {
                    int userID = cursor.getInt(0);
                    String cardID = cursor.getString(1);
                    String firstname = cursor.getString(2);
                    String lastname = cursor.getString(3);
                    String birthday = cursor.getString(4);
                    String birthplace = cursor.getString(5);
                    String nationality = cursor.getString(8);
                    String validUntil = cursor.getString(9);
                    temp = new IdentityCard(userID, "Identity Card",
                            cardID, lastname, lastname,
                            firstname, birthday, nationality, birthplace, validUntil);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return temp;
    }

    public DriversLicense getDriversLicenseByID(int id) {
        String selectQuery = "Select * FROM " + Table_DRIVERS_LICENSES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        DriversLicense temp = null;
        if (cursor.moveToFirst()) {
            do {
                if (cursor.getInt(0) == id) {
                    String firstname = cursor.getString(1);
                    String lastname = cursor.getString(2);
                    String birthday = cursor.getString(3);
                    String birthplace = cursor.getString(4);
                    String validFrom = cursor.getString(5);
                    String validUntil = cursor.getString(6);
                    String issuedBy = cursor.getString(7);
                    String driverId = cursor.getString(8);
                    temp = new DriversLicense(id, "Drivers License",
                            firstname, lastname, birthday,
                            birthplace, validFrom, validUntil,
                            issuedBy, driverId);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return temp;
    }

    public OrganDonorCard getOrganDonorCardByID(int id) {
        String selectQuery = "Select * FROM " + Table_ORGAN_DONOR_CARDS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        OrganDonorCard temp = null;
        if (cursor.moveToFirst()) {
            do {
                if (cursor.getInt(0) == id) {
                    String firstname = cursor.getString(1);
                    String lastname = cursor.getString(2);
                    String birthday = cursor.getString(3);
                    String street = cursor.getString(4);
                    String postalCity = cursor.getString(5);
                    temp = new OrganDonorCard(id, "Organ Donor Card",
                            firstname, lastname, birthday, street, postalCity);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return temp;
    }

    public BankCard getBankCardByID(int id) {
        String selectQuery = "Select * FROM " + Table_BANK_CARDS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        BankCard temp = null;
        if (cursor.moveToFirst()) {
            do {
                if (cursor.getInt(1) == id) {
                    int userID = cursor.getInt(1);
                    String firstname = cursor.getString(2);
                    String lastname = cursor.getString(3);
                    double balance = cursor.getDouble(4);
                    String iban = cursor.getString(5);

                    temp = new BankCard(userID, "Bank Card",
                            iban, balance, firstname, lastname);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return temp;
    }

    public ArrayList<BankCard> getBankCardsByUserID(int userID) {
        String selectQuery = "SELECT * FROM " + Table_BANK_CARDS + " WHERE " + USER_ID + " = " + userID;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<BankCard> bankCards = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String firstname = cursor.getString(1);
                String lastname = cursor.getString(2);
                double balance = cursor.getDouble(3);
                String iban = cursor.getString(4);

                BankCard bankCard = new BankCard(id, "Bank Card", iban, balance, firstname, lastname);
                bankCards.add(bankCard);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return bankCards;
    }

    public ArrayList<Card> getAllCardsOfUserById(int id) {
        ArrayList<Card> returnList = new ArrayList<>();
        if (getIdentityCardByID(id) != null)
            returnList.add(getIdentityCardByID(id));
        if (getDriversLicenseByID(id) != null)
            returnList.add(getDriversLicenseByID(id));
        if (getOrganDonorCardByID(id) != null)
            returnList.add(getOrganDonorCardByID(id));
        returnList.addAll(getBankCardsByUserID(id));

        return returnList;
    }

    public boolean checkIfCardExists(int id, String table, Card card) {
        String selectQuery = "Select * FROM " + table;
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    if (cursor.getInt(0) == id) {
                        switch (table) {
                            case Table_DRIVERS_LICENSES:
                            case Table_IDENTITY_CARDS:
                            case Table_ORGAN_DONOR_CARDS:
                                return true;
                            case Table_BANK_CARDS:
                                if (card instanceof BankCard) {
                                    BankCard bankCard = (BankCard) card;
                                    String iban = cursor.getString(4);
                                    if (iban.equals(bankCard.getIban())) {
                                        return true;
                                    }
                                }
                                break;
                            default:
                                System.out.println("Wrong Table Name!!!");
                                break;
                        }
                    }
                } while (cursor.moveToNext());
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}