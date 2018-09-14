package com.example.menes.muharrirnetapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Toast;

import com.example.menes.muharrirnetapp.RetrofitRelated.GetDataService;
import com.example.menes.muharrirnetapp.RetrofitRelated.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInRightAnimationAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity  implements EntranceAdapter.OnItemClickListener {

    ProgressDialog progressDialog;
    List<BlogPost> rows = new ArrayList<>();

    private RecyclerView entrance;
    private EntranceAdapter entAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        Integer pageNo = 1;
        callForGetAllPosts(pageNo);
    }

    private void callForGetAllPosts (Integer pageNo) {

        //todo: Read here xd
        // Only 10 posts because that's the page size. now we can do paging but we need to use scrollview
        // or handle by page numbers (new page for each page number) but I vote for scrollview.

        String page = pageNo.toString();

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<BlogPost>> call = service.getAllPosts(page);

        call.enqueue(new Callback<List<BlogPost>>() {
            @Override
            public void onResponse(Call<List<BlogPost>> call, Response<List<BlogPost>> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()){
                    rows = response.body();
                   generateDataList(rows);
                }
                else
                    Toast.makeText(MainActivity.this, "Response was not successful...Please try later!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<BlogPost>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Something went wrong...Please try again by swiping down!", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void generateDataList(List<BlogPost> rowsForBlog) {
        entrance = findViewById(R.id.mainRecyclerView);
        entrance.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));


        entAdapter = new EntranceAdapter(this,rowsForBlog);

        /*AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(entAdapter);
        alphaAdapter.setFirstOnly(false);
        alphaAdapter.setDuration(1500);
        alphaAdapter.setInterpolator(new OvershootInterpolator());
        entrance.setAdapter(new ScaleInAnimationAdapter(alphaAdapter));*/ //SO SO

       /* SlideInRightAnimationAdapter slideInRightAnimationAdapter = new SlideInRightAnimationAdapter(entAdapter);
        slideInRightAnimationAdapter.setFirstOnly(false);
        slideInRightAnimationAdapter.setDuration(500);
        entrance.setAdapter(slideInRightAnimationAdapter);*/ //BEST SO FAR

        /*SlideInBottomAnimationAdapter slideInBottomAnimationAdapter = new SlideInBottomAnimationAdapter(entAdapter);
        slideInBottomAnimationAdapter.setFirstOnly(false);
        slideInBottomAnimationAdapter.setDuration(1000);
        entrance.setAdapter(new ScaleInAnimationAdapter(slideInBottomAnimationAdapter));*/ //NOT SO GOOD.

        ScaleInAnimationAdapter scaleInAnimationAdapter = new ScaleInAnimationAdapter(entAdapter);
        scaleInAnimationAdapter.setFirstOnly(false);
        scaleInAnimationAdapter.setDuration(500);
        //scaleInAnimationAdapter.setInterpolator(new OvershootInterpolator());
        entrance.setAdapter(scaleInAnimationAdapter);



        entAdapter.setOnItemClickListener(MainActivity.this);
    }

    public void onItemClick(int position) {
      //  Toast.makeText(getApplicationContext(), position + " is clicked", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, PostActivity.class);
        intent.putExtra("postId", rows.get(position).getPostId().toString());
        startActivity(intent);
    }

}
