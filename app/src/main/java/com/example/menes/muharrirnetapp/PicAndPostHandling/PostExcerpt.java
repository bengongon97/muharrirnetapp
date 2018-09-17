package com.example.menes.muharrirnetapp.PicAndPostHandling;

import com.google.gson.annotations.SerializedName;

public class PostExcerpt {

    @SerializedName("rendered")
    private String postExcerpt; //no need to get "protected" attribute

    public PostExcerpt(String postExcerpt){
        this.postExcerpt = postExcerpt;
    }

    public String getPostExcerpt() {
        return postExcerpt;
    }

    public void setPostExcerpt(String postExcerpt) {
        this.postExcerpt = postExcerpt;
    }
}
