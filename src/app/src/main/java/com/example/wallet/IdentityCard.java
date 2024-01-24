package com.example.wallet;

import android.os.Parcel;
import android.os.Parcelable;

public class IdentityCard extends Card implements Parcelable {
    private String firstName;
    private String lastName;
    private String birthday;
    private String placeOfBirth;
    private String nationality;
    private String validUntil;
    private String issuedBy;
    private String id_Number;
    private String street_number;
    private String postalCode_city;
    private String birthname;
    private int userID;

    private String middle_name = "";

    public IdentityCard(int newId, String cardName, String i_ID, String lName, String p_birthname, String fName,
                        String birth, String nation, String bPlace, String vUntil) {
        super(cardName);
        this.userID = newId;
        this.id_Number = i_ID;
        this.lastName = lName;
        this.firstName = fName;
        this.birthday = birth;
        this.placeOfBirth = bPlace;
        this.validUntil = vUntil;
        this.nationality = nation;
        this.birthname = p_birthname;
        this.street_number = "sgsg 14";
        this.postalCode_city = "12345 Darmstadt";
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public String getValidUntil() {
        return validUntil;
    }

    public String getId_Number() {
        return id_Number;
    }

    public String getIssuedBy() {
        return issuedBy;
    }

    public int getUserID() {
        return userID;
    }

    public String getNationality() {
        return nationality;
    }

    public String getStreet_number() {
        return street_number;
    }

    public String getPostalCode_city() {
        return postalCode_city;
    }

    public String getBirthname() {
        return birthname;
    }

    // Parcelable implementation

    protected IdentityCard(Parcel in) {
        super("Identity Card");
        firstName = in.readString();
        lastName = in.readString();
        birthday = in.readString();
        placeOfBirth = in.readString();
        nationality = in.readString();
        validUntil = in.readString();
        issuedBy = in.readString();
        id_Number = in.readString();
        street_number = in.readString();
        postalCode_city = in.readString();
        birthname = in.readString();
        userID = in.readInt();
    }

    public static final Creator<IdentityCard> CREATOR = new Creator<IdentityCard>() {
        @Override
        public IdentityCard createFromParcel(Parcel in) {
            return new IdentityCard(in);
        }

        @Override
        public IdentityCard[] newArray(int size) {
            return new IdentityCard[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(birthday);
        dest.writeString(placeOfBirth);
        dest.writeString(nationality);
        dest.writeString(validUntil);
        dest.writeString(issuedBy);
        dest.writeString(id_Number);
        dest.writeString(street_number);
        dest.writeString(postalCode_city);
        dest.writeString(birthname);
        dest.writeInt(userID);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}

