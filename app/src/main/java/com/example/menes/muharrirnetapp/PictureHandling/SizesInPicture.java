package com.example.menes.muharrirnetapp.PictureHandling;

import com.google.gson.annotations.SerializedName;

public class SizesInPicture {

    @SerializedName("thumbnail")
    private ThumbnailInPicture thumbnailInPicture;
    @SerializedName("medium_large")
    private MediumLarge mediumLarge;

    public SizesInPicture(ThumbnailInPicture thumbnailInPicture, MediumLarge mediumLarge) {
        this.thumbnailInPicture = thumbnailInPicture;
        this.mediumLarge = mediumLarge;
    }

    public ThumbnailInPicture getThumbnailInPicture() {
        return thumbnailInPicture;
    }

    public void setThumbnailInPicture(ThumbnailInPicture thumbnailInPicture) {
        this.thumbnailInPicture = thumbnailInPicture;
    }

    public MediumLarge getMediumLarge() {
        return mediumLarge;
    }

    public void setMediumLarge(MediumLarge mediumLarge) {
        this.mediumLarge = mediumLarge;
    }
}
