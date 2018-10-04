package com.example.menes.muharrirnetapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryView> {

    private CategoryAdapter.OnItemClickListener onItemClickListener;
    private List<String> categories;
    private List<String> details;

    public CategoryAdapter(List<String> categories,List<String> details) {
        this.categories = categories;
        this.details = details;
    }

    public interface OnItemClickListener {
        void onItemClick (int position);
    }

    class CategoryView extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView categoryName;
        TextView categoryDetail;

        private  CategoryView(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            categoryName = itemView.findViewById(R.id.categoryName);
            categoryDetail = itemView.findViewById(R.id.categoryDetail);
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(R.id.categoryName);
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
        String categoryName = categories.get(holder.getAdapterPosition());
        String categoryDetail = details.get(holder.getAdapterPosition());
        holder.categoryName.setText(categoryName);
        holder.categoryDetail.setText(categoryDetail);
    }

    public void setOnItemClickListener(CategoryAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}

