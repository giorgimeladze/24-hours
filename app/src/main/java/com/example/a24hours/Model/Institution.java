package com.example.a24hours.Model;

import android.content.Intent;

import java.io.Serializable;

public class Institution {
    private String name;
    private String address;
    private String description;
    private String googleURL;
    private String phoneNumber;
    private boolean isFavorite;

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public Institution() {

    }

    public Institution(String name, String description, String address, String phoneNumber, String googleURL) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.googleURL = googleURL;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    public String getGoogleURL() {
        return googleURL;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
