package com.example.menes.muharrirnetapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import com.example.menes.muharrirnetapp.RetrofitRelated.GetDataService;
import com.example.menes.muharrirnetapp.RetrofitRelated.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    List<BlogPost> rows = new ArrayList<>();

    private RecyclerView entrance;
    private EntranceAdapter entAdapter;
    SwipeRefreshLayout mySwipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();


        mySwipeRefresh = findViewById(R.id.mySwipeRefresh);
        mySwipeRefresh.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        // This method performs the actual data-refresh operation.
                        // The method calls setRefreshing(false) when it's finished.
                       // callForGetAllPosts();
                        mySwipeRefresh.setRefreshing(false); //NOT YET IMPLEMENTED.
                    }
                }
        );


        callForGetAllPosts();
    }

    private void callForGetAllPosts () {

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<BlogPost>> call = service.getAllPosts();

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
        entAdapter = new EntranceAdapter(this,rowsForBlog);
        entrance.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
//      entAdapter.setOnItemClickListener((EntranceAdapter.OnItemClickListener) this);
        entrance.setAdapter(entAdapter);
    }

    public void onItemClick(int position) {
        Intent intent = new Intent(this, PostActivity.class);
        intent.putExtra("id", rows.get(position).getPostId());
        startActivity(intent);
    }

}
