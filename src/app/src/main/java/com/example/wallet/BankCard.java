package com.example.wallet;

import android.os.Parcel;
import android.os.Parcelable;


public class BankCard extends Card implements Parcelable {
    private int UserID;
    private String iban;
    private double currentBalance;
    private String firstName;
    private String lastName;

    BankCard(int newID, String cardName, String p_iban, double balance, String fName, String lName){
        super(cardName);
        this.UserID = newID;
        this.iban = p_iban;
        this.currentBalance = balance;
        this.firstName = fName;
        this.lastName = lName;
    }

    protected BankCard(Parcel in) {
        super("BankCard");
        iban = in.readString();
        firstName = in.readString();
        lastName = in.readString();
        UserID = in.readInt();
        currentBalance = in.readDouble();
    }

    public static final Creator<BankCard> CREATOR = new Creator<BankCard>() {
        @Override
        public BankCard createFromParcel(Parcel in) {
            return new BankCard(in);
        }

        @Override
        public BankCard[] newArray(int size) {
            return new BankCard[size];
        }
    };

    public String getIban() {
        return iban;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public int getUserID() {
        return UserID;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(iban);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeInt(UserID);
        dest.writeDouble(currentBalance);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}