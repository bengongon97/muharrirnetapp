package com.example.menes.muharrirnetapp.PictureHandling;

import com.google.gson.annotations.SerializedName;

public class SizesInPicture {

    @SerializedName("thumbnail")
    private ThumbnailInPicture thumbnailInPicture;

    public SizesInPicture(ThumbnailInPicture thumbnailInPicture){
        this.thumbnailInPicture = thumbnailInPicture;
    }

    public ThumbnailInPicture getThumbnailInPicture() {
        return thumbnailInPicture;
    }

    public void setThumbnailInPicture(ThumbnailInPicture thumbnailInPicture) {
        this.thumbnailInPicture = thumbnailInPicture;
    }
}
