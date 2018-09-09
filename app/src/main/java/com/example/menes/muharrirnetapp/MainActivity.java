package com.example.menes.muharrirnetapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

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
                    Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }

            /* TODO: Query is successful but the class fields are not matching and I am too lazy to handle it now. I'll do it np*/

            @Override
            public void onFailure(Call<List<BlogPost>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void generateDataList(List<BlogPost> rows) {
        entrance = findViewById(R.id.mainRecyclerView);
        entAdapter = new EntranceAdapter(this,rows);
        RecyclerView.LayoutManager LayoutManager = new LinearLayoutManager(MainActivity.this);
        entrance.setLayoutManager(LayoutManager);
        entAdapter.setOnItemClickListener((EntranceAdapter.OnItemClickListener) this);
        entrance.setAdapter(entAdapter);
    }

    public void onItemClick(int position) {
        Intent intent = new Intent(this, PostActivity.class);
        intent.putExtra("id", rows.get(position).getPostId());
        startActivity(intent);
    }

}
