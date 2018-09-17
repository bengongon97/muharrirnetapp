package com.example.menes.muharrirnetapp.PicAndPostHandling;

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