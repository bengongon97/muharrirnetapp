package com.example.menes.muharrirnetapp.PicAndPostHandling;

import com.google.gson.annotations.SerializedName;

public class SizesInPicture {

    @SerializedName("thumbnail")
    private ThumbnailInPicture thumbnailInPicture;
    @SerializedName("medium_large")
    private MediumLarge mediumLarge;
    @SerializedName("medium")
    private MediumPicture mediumPicture;
    @SerializedName("large")
    private LargePicture largePicture;

    public SizesInPicture(ThumbnailInPicture thumbnailInPicture, MediumLarge mediumLarge, MediumPicture mediumPicture, LargePicture largePicture) {
        this.thumbnailInPicture = thumbnailInPicture;
        this.mediumLarge = mediumLarge;
        this.mediumPicture = mediumPicture;
        this.largePicture = largePicture;
    }

    public LargePicture getLargePicture() {
        return largePicture;
    }

    public void setLargePicture(LargePicture largePicture) {
        this.largePicture = largePicture;
    }

    public MediumPicture getMediumPicture() {
        return mediumPicture;
    }

    public void setMediumPicture(MediumPicture mediumPicture) {
        this.mediumPicture = mediumPicture;
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
