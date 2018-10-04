package com.example.menes.muharrirnetapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

public class MenuActivity extends AppCompatActivity {

    private CategoryAdapter categoryAdapter;
    private RecyclerView categoryRec;
    FloatingActionButton savedPostBtn;
    List<String> categories = new ArrayList<>();
    List<String> details = new ArrayList<>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        savedPostBtn = findViewById(R.id.savedPostsBtn);
        final RecyclerView menuRecyclerView= findViewById(R.id.menuRec);
        menuRecyclerView.setHasFixedSize(true);

        setCategories();

        savedPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, CategoryActivity.class);
                intent.putExtra("category","saved");
                startActivity(intent);
            }
        });
    }

    public void onItemClick(int position){
        Intent intent = new Intent(this,CategoryActivity.class);
        intent.putExtra("category",position);
        startActivity(intent);
    }

    private void setCategories()
    {
        this.categories = Arrays.asList(getResources().getStringArray(R.array.categories));
        this.details = Arrays.asList(getResources().getStringArray(R.array.details));
        categoryRec = findViewById(R.id.menuRec);
        categoryRec.setLayoutManager(new GridLayoutManager(this,2));
        categoryAdapter = new CategoryAdapter(categories,details);

        ScaleInAnimationAdapter adapter = new ScaleInAnimationAdapter(categoryAdapter);
        adapter.setDuration(500);

        categoryAdapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                MenuActivity.this.onItemClick(position);
            }
        });
        categoryRec.setAdapter(adapter);
    }
}
