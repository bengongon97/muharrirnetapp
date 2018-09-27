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
    @SerializedName("replies")
    private List<List<Comments>> comments;

    public List<List<Comments>> getComments() {
        return comments;
    }

    public void setComments(List<List<Comments>> comments) {
        this.comments = comments;
    }

    public Embedded(List<FeaturedMedia> featuredMedia, List<Author> author, List<List<CategoriesAndTags>> categoryAndTags, List<List<Comments>> comments) {
        this.featuredMedia = featuredMedia;
        this.author = author;
        this.categoryAndTags = categoryAndTags;
        this.comments = comments;
    }

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
