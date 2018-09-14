package com.example.menes.muharrirnetapp.PicAndPostHandling;

import com.google.gson.annotations.SerializedName;

public class CategoriesAndTags {

    @SerializedName("taxonomy")
    private String taxonomy;
    @SerializedName("name")
    private String categoryName;
    @SerializedName("id")
    private Integer categoryId;

    //IT IS NAMED "CATEGORY" BUT TAGS ARE ALSO STORED HERE. LOOK TAXONOMY; category FOR CATEGORİES AND post_tag FOR TAGS. FORMAT IS SAME.
    public CategoriesAndTags(String taxonomy, String categoryName, Integer categoryId) {
        this.taxonomy = taxonomy;
        this.categoryName = categoryName;
        this.categoryId = categoryId;
    }

    public String getTaxonomy() {
        return taxonomy;
    }

    public void setTaxonomy(String taxonomy) {
        this.taxonomy = taxonomy;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
