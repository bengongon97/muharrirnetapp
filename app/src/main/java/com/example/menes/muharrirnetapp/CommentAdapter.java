package com.example.menes.muharrirnetapp;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.menes.muharrirnetapp.PicAndPostHandling.BlogPost;
import com.example.menes.muharrirnetapp.PicAndPostHandling.Comments;
import java.util.List;


public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentView>{

    BlogPost gottenPost;

    private EntranceAdapter.OnItemClickListener onItemClickListener;
    private List<Comments> justForLength;
    private Context context;

    public CommentAdapter(Context context, List<Comments> justForLength, BlogPost blogPost) {
        this.context = context;
        this.justForLength = justForLength;
        this.gottenPost = blogPost;
    }

    class CommentView extends RecyclerView.ViewHolder {
        TextView commentDateText;
        TextView commentContentText;
        TextView commentAuthorNameText;

        private CommentView(View itemView) {
            super(itemView);

            commentDateText = itemView.findViewById(R.id.commentDateText);
            commentAuthorNameText = itemView.findViewById(R.id.commentAuthorNameText);
            commentContentText = itemView.findViewById(R.id.commentContentText);
        }

    }

    @NonNull
    @Override
    public CommentAdapter.CommentView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.in_comment, parent, false);
        return new CommentAdapter.CommentView(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull final CommentAdapter.CommentView holder, final int position) {

        if(gottenPost.getEmbedded().getComments() != null){
            for (int y = 0; y < gottenPost.getEmbedded().getComments().size(); y++) {
                holder.commentAuthorNameText.setText(gottenPost.getEmbedded().getComments().get(0).get(y).getAuthor_name());
                holder.commentDateText.setText(gottenPost.getEmbedded().getComments().get(0).get(y).getDate());
                holder.commentContentText.setText(gottenPost.getEmbedded().getComments().get(0).get(y).getContent());
            }
        }
        else {
            holder.commentAuthorNameText.setText("Henüz bir yorum yapılmamış.");
            holder.commentDateText.setText("");
            holder.commentDateText.setVisibility(View.INVISIBLE);
            holder.commentContentText.setText("");
            holder.commentContentText.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return justForLength.size();
    }

}
