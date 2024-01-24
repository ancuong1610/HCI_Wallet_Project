package com.example.wallet;

import android.os.Parcel;
import android.os.Parcelable;

public class OrganDonorCard extends Card implements Parcelable {
    private String firstName;
    private String lastName;
    private String birthday;
    private String street_number;
    private String postalCode_city;

    private int userID;

    OrganDonorCard(int newID, String cardname, String fName, String lName, String birth, String str_nr, String p_city) {
        super(cardname);
        this.userID = newID;
        this.firstName = fName;
        this.lastName = lName;
        this.birthday = birth;
        this.street_number = str_nr;
        this.postalCode_city = p_city;
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

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getStreet_number() {
        return street_number;
    }

    public String getPostalCode_city() {
        return postalCode_city;
    }

    // Parcelable implementation

    protected OrganDonorCard(Parcel in) {
        super("OrganDonorCard");
        firstName = in.readString();
        lastName = in.readString();
        birthday = in.readString();
        street_number = in.readString();
        postalCode_city = in.readString();
        userID = in.readInt();
    }

    public static final Creator<OrganDonorCard> CREATOR = new Creator<OrganDonorCard>() {
        @Override
        public OrganDonorCard createFromParcel(Parcel in) {
            return new OrganDonorCard(in);
        }

        @Override
        public OrganDonorCard[] newArray(int size) {
            return new OrganDonorCard[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(birthday);
        dest.writeString(street_number);
        dest.writeString(postalCode_city);
        dest.writeInt(userID);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}

