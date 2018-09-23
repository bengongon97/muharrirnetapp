package com.example.menes.muharrirnetapp;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;

import java.lang.reflect.Array;
import java.util.List;

public class CategoryAdapter extends ArrayAdapter<CategoryAdapter.CategoryView>{

    Context context;
    List<String> categories;

    public CategoryAdapter(@NonNull Context context, int resource, @NonNull List<CategoryView> objects) {
        super(context, resource, objects);
    }

    public interface OnItemClickListener {
        void onItemClick (int position);
    }

    /*

    @NonNull
    @Override
    public CategoryView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.category, parent, false);
        return new CategoryAdapter.CategoryView(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryView holder, int position) {
        final String row = categories.get(holder.getAdapterPosition());



    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
    */

    public class CategoryView extends RecyclerView.ViewHolder {

        public CategoryView(View itemView) {
            super(itemView);
        }
    }
}
