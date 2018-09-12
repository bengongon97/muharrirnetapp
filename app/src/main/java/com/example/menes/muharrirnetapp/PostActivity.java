package com.example.menes.muharrirnetapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.menes.muharrirnetapp.RetrofitRelated.GetDataService;
import com.example.menes.muharrirnetapp.RetrofitRelated.RetrofitClientInstance;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;

class PostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        String postId= getIntent().getStringExtra("EXTRA_SESSION_ID");

        getPostWithId(postId);

    }

    private void getPostWithId(String postId) {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<BlogPost>> call = service.getPostContent(postId);


    }
}
