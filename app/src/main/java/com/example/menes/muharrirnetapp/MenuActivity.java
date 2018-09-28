package com.example.menes.muharrirnetapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.wasabeef.recyclerview.animators.FlipInLeftYAnimator;

public class MenuActivity extends AppCompatActivity {

    private CategoryAdapter categoryAdapter;
    private RecyclerView categoryRec;
    List<String> categories = new ArrayList<>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        final RecyclerView menuRecyclerView= findViewById(R.id.menuRec);
        menuRecyclerView.setHasFixedSize(true);
        setCategories();
    }

    public void onItemClick(int position){
        Intent intent = new Intent(this,CategoryActivity.class);
        intent.putExtra("category",position);
        startActivity(intent);
    }

    private void setCategories()
    {
        this.categories = Arrays.asList(getResources().getStringArray(R.array.categories));
        categoryRec = findViewById(R.id.menuRec);
        categoryRec.setLayoutManager(new GridLayoutManager(this,2));
        categoryAdapter = new CategoryAdapter(categories);
    }
}
