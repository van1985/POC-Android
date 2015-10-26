package com.poc_android.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by vanden on 10/21/15.
 */
public class UserData implements Parcelable {

    private String username;
    private int password;
    private int userID; //DNI
    private String name;
    private String lastName;

    public UserData(){}

    public UserData(String username, int userID, String name, String lastName, int password){
        this.username = username;
        this.userID = userID;
        this.name = name;
        this.lastName = lastName;
        this.password = password;
    }

    public UserData(Parcel in) {
        this.username =in.readString();
        this.userID = in.readInt();
        this.name = in.readString();
        this.lastName = in.readString();
        this.password = in.readInt();
    }

    public int getPassword(){
        return password;
    }

    public void setPassword(int pass){
        this.password = pass;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String user){
        this.username = user;
    }

    public int getUserID(){ return this.userID; }

    public static final Creator<UserData> CREATOR = new Creator<UserData>() {
        @Override
        public UserData createFromParcel(Parcel in) {
            return new UserData(in);
        }

        @Override
        public UserData[] newArray(int size) {
            return new UserData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeInt(userID);
        dest.writeString(name);
        dest.writeString(lastName);
        dest.writeInt(password);
    }
}
