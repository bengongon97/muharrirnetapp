package com.example.menes.muharrirnetapp.PicAndPostHandling;

import com.google.gson.annotations.SerializedName;

public class CommentContent {

    @SerializedName("rendered")
    private String commentContent;

    public CommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }
}
