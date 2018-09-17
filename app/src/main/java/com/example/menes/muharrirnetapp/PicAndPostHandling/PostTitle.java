package com.example.menes.muharrirnetapp.PicAndPostHandling;

import com.google.gson.annotations.SerializedName;

public class PostTitle {
    @SerializedName("rendered")
    private String postTitle;

public PostTitle(String postTitle){
    this.postTitle = postTitle;
}

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }
}



/*"title": {
        "rendered": "ŞU TARAFA DOĞRU"
        },*/