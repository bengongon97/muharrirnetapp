package com.example.menes.muharrirnetapp;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryView> {

    private CategoryAdapter.OnItemClickListener onItemClickListener;
    private List<String> categories;

    public CategoryAdapter(List<String> categories) {
        this.categories = categories;
    }

    public interface OnItemClickListener {
        void onItemClick (int position);
    }

    class CategoryView extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView categoryName;
        FloatingActionButton button;

        private  CategoryView(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);

            categoryName =itemView.findViewById(R.id.category_name);
            button = itemView.findViewById(R.id.category_button);
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(R.id.category_button);
            }
        }
    }

    @NonNull
    @Override
    public CategoryView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.category, parent, false);
        return new CategoryView(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryView holder, int position) {

    }

    public void setOnItemClickListener(CategoryAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }


}

