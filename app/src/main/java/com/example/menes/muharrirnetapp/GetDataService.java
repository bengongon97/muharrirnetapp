package com.example.menes.muharrirnetapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetDataService {

    @GET("/wp-json/wp/v2/posts")
    Call<List<BlogPost>> getAllPosts();

    @GET("/wp-json/wp/v2/posts/{id}")
    Call<List<BlogPost>> getPostContent(@Path("id") String postId);
}
