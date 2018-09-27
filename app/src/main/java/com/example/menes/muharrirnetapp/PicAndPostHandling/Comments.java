package com.example.menes.muharrirnetapp.PicAndPostHandling;

import com.google.gson.annotations.SerializedName;

public class Comments {
    @SerializedName("date")
    private String date;
    @SerializedName("content")
    private String content;
    @SerializedName("author_name")
    private String author_name;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public Comments(String date, String content, String author_name) {
        this.date = date;
        this.content = content;
        this.author_name = author_name;
    }
}
