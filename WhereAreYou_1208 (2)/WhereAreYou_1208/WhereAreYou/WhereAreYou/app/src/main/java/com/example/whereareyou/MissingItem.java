package com.example.whereareyou;

public class MissingItem {
    private String image;
    private String text;

    public String getImage() {
        return this.image;
    }

    public String getText() {
        return this.text;
    }

    public MissingItem(String image, String text) {
        this.image = image;
        this.text = text;
    }
}
