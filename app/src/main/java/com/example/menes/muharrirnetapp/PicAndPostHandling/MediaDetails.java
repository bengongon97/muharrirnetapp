package com.example.menes.muharrirnetapp.PicAndPostHandling;

import com.google.gson.annotations.SerializedName;

public class MediaDetails {

    @SerializedName("sizes")
    private SizesInPicture sizesInPicture;

    public MediaDetails(SizesInPicture sizesInPicture){
        this.sizesInPicture = sizesInPicture;
    }

    public SizesInPicture getSizesInPicture() {
        return sizesInPicture;
    }

    public void setSizesInPicture(SizesInPicture sizesInPicture) {
        this.sizesInPicture = sizesInPicture;
    }
}
