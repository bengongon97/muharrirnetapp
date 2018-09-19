package com.example.menes.muharrirnetapp;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryView>{

    Context context;
    List<String> categories;

    @NonNull
    @Override
    public CategoryView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryView holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public interface OnItemClickListener {
        void onItemClick (int position);
    }


    public CategoryAdapter (Context context) {
        this.context = context;
    }

    public class CategoryView extends RecyclerView.ViewHolder {

        public CategoryView(View itemView) {
            super(itemView);
        }
    }
}
