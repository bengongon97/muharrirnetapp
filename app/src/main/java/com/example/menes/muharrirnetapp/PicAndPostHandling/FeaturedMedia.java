package com.example.menes.muharrirnetapp.PicAndPostHandling;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.lang.reflect.Array;

public class FeaturedMedia {

    @SerializedName("id")
    private Integer pictureId;
    @SerializedName("media_details")
    private MediaDetails mediaDetails;
    @SerializedName("code")
    private String restCode;

    public FeaturedMedia(Integer pictureId, MediaDetails mediaDetails){
        this.pictureId = pictureId;
        this.mediaDetails = mediaDetails;
    }

    public Integer getPictureId() {
        return pictureId;
    }

    public void setPictureId(Integer pictureId) {
        this.pictureId = pictureId;
    }

    public MediaDetails getMediaDetails() {
        return mediaDetails;
    }

    public void setMediaDetails(MediaDetails mediaDetails) {
        this.mediaDetails = mediaDetails;
    }

    public String getStatus() {
        //used when code: "rest_forbidden"
        return restCode;
    }
}
