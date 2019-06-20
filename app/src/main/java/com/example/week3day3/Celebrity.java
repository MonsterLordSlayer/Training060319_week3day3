package com.example.week3day3;

import android.os.Parcel;
import android.os.Parcelable;

public class Celebrity implements Parcelable {
    private String name;
    private String dob;
    private String fav="no";


    public Celebrity(String name, String dob, String fav) {
        this.name=name;
        this.dob=dob;
        this.fav=fav;


    }

    public static final Creator<Celebrity> CREATOR = new Creator<Celebrity>() {
        @Override
        public Celebrity createFromParcel(Parcel in) {
            return new Celebrity(in);
        }

        @Override
        public Celebrity[] newArray(int size) {
            return new Celebrity[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
    public void setFav(String fav){
        this.fav=fav;
    }
    public String getFav(){
        return fav;
    }

    protected Celebrity(Parcel in) {
        name = in.readString();
        dob = in.readString();
        fav = in.readString();


    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(dob);
        parcel.writeString(fav);
    }
}
