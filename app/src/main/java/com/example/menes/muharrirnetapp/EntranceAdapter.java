package com.example.menes.muharrirnetapp;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.text.Html;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;



import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class EntranceAdapter extends RecyclerView.Adapter<EntranceAdapter.EntranceView>{


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

    class EntranceView extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView authorText;
        TextView dateText;
        TextView descText;
        TextView titleText;
        ImageView postImg;
        TextView categoriesText;

        private EntranceView(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            categoriesText = itemView.findViewById(R.id.categoriesText);
            authorText = itemView.findViewById(R.id.authorText);
            dateText = itemView.findViewById(R.id.dateText);
            descText = itemView.findViewById(R.id.postDescription);
            titleText = itemView.findViewById(R.id.postTitle);
            postImg = itemView.findViewById(R.id.postImg);
        }

        @Override
        public void onClick(View view) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(R.id.postImg);
            }
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

      final BlogPost row = myPosts.get(holder.getAdapterPosition());

      if(row.getStatus().equals("publish")) {

          holder.authorText.setText(row.getEmbedded().getAuthor().get(0).getName());

          holder.titleText.setText(row.getTitle().getPostTitle());
          String formattedExcerpt = Html.fromHtml(row.getExcerpt().getPostExcerpt()).toString();
          holder.descText.setText(formattedExcerpt);

          if(row.getEmbedded().getCategoryAndTags().get(0).size() != 0) {
              StringBuilder xd = new StringBuilder();
              String xdd = "";
              for (int i = 0; i < row.getEmbedded().getCategoryAndTags().get(0).size(); i++) {
                  xd.append(row.getEmbedded().getCategoryAndTags().get(0).get(i).getCategoryName());
                  xd.append(", ");
              }
              xdd =  xd.toString().substring(0, xd.length() - 2);
              holder.categoriesText.setText(xdd);
          }

          String date = row.getDate();
          if(Build.VERSION.SDK_INT > 25 && row.getDate() != null) {

              DateTimeFormatter parseFormatter
                      = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
              DateTimeFormatter newFormatter
                      = DateTimeFormatter.ofPattern("dd/MM/uuuu");
              String resultDate = LocalDateTime.parse(date, parseFormatter).format(newFormatter);

              holder.dateText.setText(resultDate);
          }
          else if (Build.VERSION.SDK_INT <= 25 && row.getDate() != null){
              SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
              SimpleDateFormat desiredFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

              Date dateForm = null;
              try {
                  dateForm = formatter.parse(row.getDate());
              } catch (ParseException e) {
                  e.printStackTrace();
                  holder.dateText.setText(row.getDate());
              }
              String dateString = desiredFormatter.format(dateForm);
              holder.dateText.setText(dateString); //MIGHT BE INEFFICIENT! //Am I using too much strings?? TODO:Şevval take a look at dis' :D
          }

          if (row.getEmbedded() != null) {

          Picasso.Builder builder = new Picasso.Builder(context);
          builder.downloader(new OkHttp3Downloader(context));

             if(row.getEmbedded().getFeaturedMedia() != null){
                 builder.build().load(row.getEmbedded().getFeaturedMedia().get(0).getMediaDetails().getSizesInPicture().getThumbnailInPicture().getSourceUrl())
                        .placeholder((R.drawable.muharrir_logo))
                        .error(R.drawable.muharrir_logo)
                        .into(holder.postImg);
             }
             else{
                 holder.postImg.setImageResource(R.drawable.muharrir_logo);
                 holder.postImg.setMaxHeight(150);
                 holder.postImg.setMaxWidth(150);
             }

          }

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

    public void appendNewRows(List<BlogPost> newRows, int pageno, int newItemCount) {
        myPosts.addAll(newRows);
        this.notifyItemRangeChanged(10*pageno ,newItemCount);
    }

    @Override
    public int getItemCount() {
        return myPosts.size();
    }

}
