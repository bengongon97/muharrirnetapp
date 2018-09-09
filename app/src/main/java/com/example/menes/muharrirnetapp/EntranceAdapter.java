package com.example.menes.muharrirnetapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.List;

public class EntranceAdapter extends RecyclerView.Adapter<EntranceAdapter.EntranceView> {

    public interface OnItemClickListener {
        void onItemClick (int position);
    }

    private EntranceAdapter.OnItemClickListener onItemClickListener;
    private List<BlogPost> myPosts;
    private Context context;

    public EntranceAdapter(Context context, List<BlogPost> myPosts) {
        this.context = context;
        this.myPosts = myPosts;
    }

    public void setOnItemClickListener(EntranceAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    //not sure if we need all of them OR why we need these.
    class EntranceView extends RecyclerView.ViewHolder {
        TextView tagsText;
        TextView authorText;
        TextView dateText;
        TextView descText;
        TextView titleText;
        ImageView postImg;
        LinearLayout lnrLayout;

        public EntranceView(View itemView) {
            super(itemView);

            lnrLayout =  itemView.findViewById(R.id.mainLayout);
            tagsText = itemView.findViewById(R.id.tagsText);
            authorText = itemView.findViewById(R.id.authorText);
            dateText = itemView.findViewById(R.id.dateText);
            descText = itemView.findViewById(R.id.postDescription);
            titleText = itemView.findViewById(R.id.postTitle);
            postImg = itemView.findViewById(R.id.postImg);
        }
    }

    @NonNull
    @Override
    public EntranceView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.in_main, parent, false);
        EntranceView entranceView = new EntranceView(layoutView);
        return entranceView;
    }

    @Override
    public void onBindViewHolder(@NonNull EntranceView holder, final int position) {

        BlogPost row = myPosts.get(position);
        holder.tagsText.setText(row.getTag());
        holder.authorText.setText(row.getAuthor());
        holder.dateText.setText(row.getDate().toString());
        holder.titleText.setText(row.getTitle());

        //todo: Not sure how to handle the image, it comes as  "featured_media": 339 or sth like that.

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener != null) {
                    onItemClickListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return myPosts.size();
    }

}
