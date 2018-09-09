package com.example.menes.muharrirnetapp.PictureHandling;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Embedded {

    @SerializedName("wp:featuredmedia")
    private List<FeaturedMedia> featuredMedia;

    public Embedded(List<FeaturedMedia> featuredMedia){
        this.featuredMedia = featuredMedia;
    }

    public List<FeaturedMedia> getFeaturedMedia() {
        return featuredMedia;
    }

    public void setFeaturedMedia(List<FeaturedMedia> featuredMedia) {
        this.featuredMedia = featuredMedia;
    }
}
