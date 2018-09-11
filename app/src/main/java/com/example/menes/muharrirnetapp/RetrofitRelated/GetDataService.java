package com.example.menes.muharrirnetapp.RetrofitRelated;

import com.example.menes.muharrirnetapp.BlogPost;
import com.example.menes.muharrirnetapp.PictureHandling.FeaturedMedia;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetDataService {

    // http://muharrir.net/wp-json/wp/v2/posts?_embed
    @GET("posts?_embed")
    Call<List<BlogPost>> getAllPosts();

    @GET("posts?_embed/{id}")
    Call<List<BlogPost>> getPostContent(@Path("id") String postId);

    @GET("media/{pic}")
    Call<FeaturedMedia> getPictureContent(@Path("pic") Integer pictureId);
}
