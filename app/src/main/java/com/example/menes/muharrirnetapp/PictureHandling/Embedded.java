package com.example.menes.muharrirnetapp.PictureHandling;

import com.example.menes.muharrirnetapp.PictureHandling.FeaturedMedia;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Embedded {

    @SerializedName("wp:featuredmedia")
    private List<FeaturedMedia> featuredMedia; //no need to get "protected" attribute

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
