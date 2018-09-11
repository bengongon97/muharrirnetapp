package com.example.menes.muharrirnetapp.PictureHandling;

import com.google.gson.annotations.SerializedName;

public class Author {

    @SerializedName("name")
    private String name;

    public Author(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
