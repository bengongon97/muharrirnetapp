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

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

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
        TextView authorText;
        TextView dateText;
        TextView descText;
        TextView titleText;
        ImageView postImg;
        LinearLayout lnrLayout;

        private EntranceView(View itemView) {
            super(itemView);

            lnrLayout =  itemView.findViewById(R.id.mainLayout);
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
        return new EntranceView(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull final EntranceView holder, final int position) {

      BlogPost row = myPosts.get(holder.getAdapterPosition());


      if(row.getStatus().equals("publish")){

          holder.authorText.setText(row.getAuthor());
          holder.dateText.setText(row.getDate());
          holder.titleText.setText(row.getTitle().getPostTitle());
          holder.descText.setText(row.getExcerpt().getPostExcerpt());

          //TODO: WHY ONLY 10 POSTS ARE ENTERED, AND THOUGHTS ABOUT HOW DO WE PAGINATE IN OUR OWN APP? HOW MANY ON OUR MAIN SCREEN? ALL? 5?

       Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(row.getEmbedded().getFeaturedMedia().get(1).getMediaDetails().getSizesInPicture().getThumbnailInPicture().getSourceUrl())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.postImg);


          holder.itemView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  if(onItemClickListener != null) {
                      onItemClickListener.onItemClick(holder.getAdapterPosition());
                  }
              }
          });
      }
    }

    @Override
    public int getItemCount() {
        return myPosts.size();
    }

}
