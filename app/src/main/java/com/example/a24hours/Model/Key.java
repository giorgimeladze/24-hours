package com.example.a24hours.Model;

public class Key {

    private String textTitle;
    private int titleIcon;

    public Key(String textTitle, int titleIcon) {
        this.textTitle = textTitle;
        this.titleIcon = titleIcon;
    }

    public String getTextTitle() {
        return textTitle;
    }

    public int getTitleIcon() {
        return titleIcon;
    }
}
