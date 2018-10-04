package com.example.menes.muharrirnetapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.menes.muharrirnetapp.PicAndPostHandling.BlogPost;
import com.example.menes.muharrirnetapp.RetrofitRelated.GetDataService;
import com.example.menes.muharrirnetapp.RetrofitRelated.RetrofitClientInstance;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity implements EntranceAdapter.OnItemClickListener {

    SearchView searchBar;
    ProgressBar pBar;
    FloatingActionButton menuBtn;
    List<BlogPost> rows = new ArrayList<>();

    private RecyclerView entrance;
    private EntranceAdapter entAdapter;
    private SimpleCursorAdapter suggestionsAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //Initially invisible progressbar
        pBar = findViewById(R.id.searchLoadBar);
        pBar.setVisibility(View.INVISIBLE);
        menuBtn = findViewById(R.id.menu_button);
        searchBar = findViewById(R.id.search_bar);

        /*
        suggestionsAdapter = new SimpleCursorAdapter(SearchActivity.this,
                android.R.layout.simple_list_item_1,
                null,
                from,
                to,
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        */
        searchBar.setSuggestionsAdapter(suggestionsAdapter);

        searchBar.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                return false;
            }
        });

        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                pBar.setVisibility(View.VISIBLE);
                getSearchResults(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // populateAdapter(newText);
                return true;
            }
        });

        searchBar.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionSelect(int position) {
                return false;
            }

            @Override
            public boolean onSuggestionClick(int position) {
                return false;
            }
        });

        searchBar.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override

            public boolean onClose() {
                Log.w("onClose", "onClose triggered");
                //todo: çalışmıyor. çalışşşşmıyorrrrrrrr.
                return false;
            }
        });

    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, PostActivity.class);
        intent.putExtra("postId", rows.get(position).getPostId().toString());
        startActivity(intent);
    }

    /*
    public Cursor getSuggestions(String newText) {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<BlogPost>> call = service.getSuggestions(newText);
        call.enqueue(new Callback<List<BlogPost>>() {
            @Override
            public void onResponse(Call<List<BlogPost>> call, Response<List<BlogPost>> response) {
                if (response.isSuccessful()){
                    rows = response.body();
                    generateDataList(rows); }
                else {
                    Toast.makeText(SearchActivity.this, getString(R.string.err_sugg), Toast.LENGTH_SHORT ).show();
                }
            }
            @Override
            public void onFailure(Call<List<BlogPost>> call, Throwable t) {
                Toast.makeText(SearchActivity.this, getString(R.string.err_sugg), Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }
    */

    public void getSearchResults(String query){

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<BlogPost>> call = service.getSearchResults(query);

        call.enqueue(new Callback<List<BlogPost>>() {
            @Override
            public void onResponse(Call<List<BlogPost>> call, Response<List<BlogPost>> response) {
                if (response.isSuccessful()){
                    rows = response.body();
                    generateDataList(rows);
                }
                else
                    Toast.makeText(SearchActivity.this, getString(R.string.err_resp), Toast.LENGTH_SHORT).show();

                pBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<List<BlogPost>> call, Throwable t) {
                pBar.setVisibility(View.INVISIBLE);
                Toast.makeText(SearchActivity.this, getString(R.string.err_fail), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generateDataList(List<BlogPost> rowsForBlog) {
        entrance = findViewById(R.id.searchRecyclerView);
        entrance.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));

        entAdapter = new EntranceAdapter(this,rowsForBlog);

        /*ScaleInAnimationAdapter scaleInAnimationAdapter = new ScaleInAnimationAdapter(entAdapter);
        scaleInAnimationAdapter.setFirstOnly(false);
        scaleInAnimationAdapter.setDuration(500);
        scaleInAnimationAdapter.setInterpolator(new OvershootInterpolator());
        entrance.setAdapter(scaleInAnimationAdapter);*/

        entrance.setAdapter(entAdapter);

        entAdapter.setOnItemClickListener(SearchActivity.this);
    }

    /*
    private void populateAdapter(String query) {
        Cursor result = getSuggestions(query);
        suggestionsAdapter.changeCursor(result);
    }
    */
}
