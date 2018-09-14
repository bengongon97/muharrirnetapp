package com.example.menes.muharrirnetapp.PicAndPostHandling;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Embedded {

    @SerializedName("wp:featuredmedia")
    private List<FeaturedMedia> featuredMedia;
    @SerializedName("author")
    private List<Author> author;
    @SerializedName("wp:term")
    private List<List<CategoriesAndTags>> categoryAndTags;

    public List<Author> getAuthor() {
        return author;
    }

    public void setAuthor(List<Author> author) {
        this.author = author;
    }

    public List<List<CategoriesAndTags>> getCategoryAndTags() {
        return categoryAndTags;
    }

    public void setCategoryAndTags(List<List<CategoriesAndTags>> categoryAndTags) {
        this.categoryAndTags = categoryAndTags;
    }

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
