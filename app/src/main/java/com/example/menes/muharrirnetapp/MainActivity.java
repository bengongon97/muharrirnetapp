package com.example.menes.muharrirnetapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.menes.muharrirnetapp.PicAndPostHandling.BlogPost;
import com.example.menes.muharrirnetapp.RetrofitRelated.GetDataService;
import com.example.menes.muharrirnetapp.RetrofitRelated.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.FlipInTopXAnimator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity  implements EntranceAdapter.OnItemClickListener {

    Integer page = 1;
    ProgressDialog progressDialog;
    ProgressBar pBar;
    FloatingActionButton menuBtn;
    ImageView searchBtn;

    List<BlogPost> rows = new ArrayList<>();
    private RecyclerView entrance;
    private EntranceAdapter entAdapter;

    int[] lastVisibleItem = new int[1];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchBtn= findViewById(R.id.searchBtn);
        menuBtn = findViewById(R.id.menu_button);

        final ImageView book = findViewById(R.id.bookLogo);
        final ImageView appname = findViewById(R.id.muharrirLogo);

        //Initially invisible progressbar
        pBar = findViewById(R.id.postLoadBar);
        pBar.setVisibility(View.INVISIBLE);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage(getString(R.string.loading_msg));
        progressDialog.show();

        final Integer pageNo = 1;
        callForGetAllPosts(pageNo);

        final RecyclerView mRecyclerView = findViewById(R.id.mainRecyclerView);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setItemAnimator(new FlipInTopXAnimator());

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                if(dy > 0) //check for scroll down
                {
                    StaggeredGridLayoutManager mLayoutManager = (StaggeredGridLayoutManager) recyclerView.getLayoutManager();
                    mLayoutManager.findLastCompletelyVisibleItemPositions(lastVisibleItem);

                    if ( lastVisibleItem[0] + 1  == 10* page)
                    {
                        Log.v("scroll", "Last Item Reached!!!");
                        getNextPage();
                    }

                }
            }
        });

        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

    }

    private void getNextPage() {
        pBar.setVisibility(View.VISIBLE);
        page++;
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<BlogPost>> call = service.getAllPosts(page.toString());

        call.enqueue(new Callback<List<BlogPost>>() {

            @Override
            public void onResponse(Call<List<BlogPost>> call, Response<List<BlogPost>> response) {
                pBar.setVisibility(View.INVISIBLE);
                if (response.isSuccessful()){
                    List<BlogPost> newRows = response.body();
                    entAdapter.appendNewRows(newRows, page, rows.size());
                }
                else
                    Toast.makeText(MainActivity.this, getString(R.string.err_resp), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<BlogPost>> call, Throwable t) {
                pBar.setVisibility(View.INVISIBLE);
                Toast.makeText(MainActivity.this, getString(R.string.err_fail), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void callForGetAllPosts (Integer pageNo) {
        String page = pageNo.toString();

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<BlogPost>> call = service.getAllPosts(page);

        call.enqueue(new Callback<List<BlogPost>>() {
            @Override
            public void onResponse(Call<List<BlogPost>> call, Response<List<BlogPost>> response) {

                if (response.isSuccessful()){
                    rows = response.body();
                    generateDataList(rows);
                    progressDialog.dismiss();
                }
                else {
                    Toast.makeText(MainActivity.this, getString(R.string.err_resp), Toast.LENGTH_SHORT ).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<BlogPost>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, getString(R.string.err_fail), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generateDataList(List<BlogPost> rowsForBlog) {
        entrance = findViewById(R.id.mainRecyclerView);
        entrance.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));

        entAdapter = new EntranceAdapter(this,rowsForBlog);

        ScaleInAnimationAdapter scaleInAnimationAdapter = new ScaleInAnimationAdapter(entAdapter);
        scaleInAnimationAdapter.setFirstOnly(false);
        scaleInAnimationAdapter.setDuration(500);
        scaleInAnimationAdapter.setInterpolator(new OvershootInterpolator());
        entrance.setAdapter(scaleInAnimationAdapter);

        entAdapter.setOnItemClickListener(MainActivity.this);
    }

    public void onItemClick(int position) {
        Intent intent = new Intent(this, PostActivity.class);
        intent.putExtra("postId", rows.get(position).getPostId().toString());
        startActivity(intent);
    }
}


