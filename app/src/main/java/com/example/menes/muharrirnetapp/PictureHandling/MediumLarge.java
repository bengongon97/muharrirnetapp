package com.example.menes.muharrirnetapp.PictureHandling;

import com.google.gson.annotations.SerializedName;

public class MediumLarge {

    @SerializedName("source_url")
    private String sourceUrl;

    public MediumLarge(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }
}
