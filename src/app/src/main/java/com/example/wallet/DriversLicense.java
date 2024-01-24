package com.example.wallet;

import android.os.Parcel;
import android.os.Parcelable;

public class DriversLicense extends Card implements Parcelable {
    private String firstName;
    private String lastName;
    private String birthday;
    private String placeOfBirth;
    private String validFrom;
    private String validUntil;
    private String issuedBy;
    private String driversLicenseID;
    private int userID;

    DriversLicense(int newID, String cardname, String fName, String lName, String birth, String bPlace, String vFrom, String vUntil, String iBy, String d_ID){
        super(cardname);
        this.userID = newID;
        this.firstName = fName;
        this.lastName = lName;
        this.birthday = birth;
        this.placeOfBirth = bPlace;
        this.validFrom = vFrom;
        this.validUntil = vUntil;
        this.issuedBy = iBy;
        this.driversLicenseID = d_ID;
    }

    protected DriversLicense(Parcel in) {
        super("Drivers Licence");
        firstName = in.readString();
        lastName = in.readString();
        birthday = in.readString();
        placeOfBirth = in.readString();
        validFrom = in.readString();
        validUntil = in.readString();
        issuedBy = in.readString();
        driversLicenseID = in.readString();
        userID = in.readInt();
    }

    public static final Creator<DriversLicense> CREATOR = new Creator<DriversLicense>() {
        @Override
        public DriversLicense createFromParcel(Parcel in) {
            return new DriversLicense(in);
        }

        @Override
        public DriversLicense[] newArray(int size) {
            return new DriversLicense[size];
        }
    };

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

    public String getValidFrom() {
        return validFrom;
    }

    public String getValidUntil() {
        return validUntil;
    }

    public String getIssuedBy() {
        return issuedBy;
    }

    public String getDriversLicenseID() {
        return driversLicenseID;
    }

    public int getUserID() {
        return userID;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(birthday);
        dest.writeString(placeOfBirth);
        dest.writeString(validFrom);
        dest.writeString(validUntil);
        dest.writeString(issuedBy);
        dest.writeString(driversLicenseID);
        dest.writeInt(userID);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
