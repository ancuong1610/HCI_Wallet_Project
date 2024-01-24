package com.example.wallet;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private String username;
    private String password;
    private String email;
    private int userID;

    public User(String new_username, String new_password, String new_email) {
        this.username = new_username;
        this.email = new_email;
        this.password = new_password;
    }

    protected User(Parcel in) {
        username = in.readString();
        password = in.readString();
        email = in.readString();
        userID = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUserID(int newID) {
        this.userID = newID;
    }

    public int getUserID() {
        return userID;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(email);
        dest.writeInt(userID);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
