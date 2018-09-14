package com.example.menes.muharrirnetapp.PicAndPostHandling;

import com.google.gson.annotations.SerializedName;

public class MediumPicture {

    @SerializedName("source_url")
    private String sourceUrl;

    public MediumPicture(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }
}
