package com.example.menes.muharrirnetapp.RetrofitRelated;

import com.example.menes.muharrirnetapp.BlogPost;
import com.example.menes.muharrirnetapp.PictureHandling.FeaturedMedia;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetDataService {

    // http://muharrir.net/wp-json/wp/v2/posts?_embed
    /*  ?page=n returns the nth page. By default it returns the first page content
     *  ?per_page=N returns N records per page.
     *  ?offset=x starts from the xth record and retrieves a page
     */

    @GET("posts?_embed")
    Call<List<BlogPost>> getAllPosts(@Query("page") String pageNo);

    @GET("posts/{id}?_embed")
    Call<BlogPost> getPostContent(@Path("id") String postId);

    @GET("media/{pic}")
    Call<FeaturedMedia> getPictureContent(@Path("pic") Integer pictureId);
}
