package com.example.menes.muharrirnetapp;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.menes.muharrirnetapp.PicAndPostHandling.BlogPost;
import com.example.menes.muharrirnetapp.PicAndPostHandling.Comments;
import com.example.menes.muharrirnetapp.PicAndPostHandling.PicassoImageGetter;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentView>{

    BlogPost gottenPost;

    private EntranceAdapter.OnItemClickListener onItemClickListener;
    private List<List<Comments>> justForLength;
    private Context context;

    public CommentAdapter(Context context, List<List<Comments>> justForLength, BlogPost blogPost) {
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

        if(gottenPost.getCommentStatus().equals("open")) {
            if (justForLength != null) {
                String commentAuthor = "";
                String date = "";
                String resultDate = "";
                String content = "";
                Spannable html = null;

                for (int y = 0; y < gottenPost.getEmbedded().getComments().size(); y++) {


                    commentAuthor = "<b>" + gottenPost.getEmbedded().getComments().get(0).get(y).getAuthor_name() + "</b> " + " dedi ki:";//Author setting



                    date = gottenPost.getEmbedded().getComments().get(0).get(y).getDate(); //Date conversion and setting
                    if(Build.VERSION.SDK_INT > 25 && date != null) {
                        DateTimeFormatter parseFormatter
                                = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                        DateTimeFormatter newFormatter
                                = DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm");
                         resultDate = LocalDateTime.parse(date, parseFormatter).format(newFormatter);

                        holder.commentDateText.setText(resultDate);
                    } else if (Build.VERSION.SDK_INT <= 25 && date != null) {
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
                        SimpleDateFormat desiredFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());

                        Date dateForm = null;
                        try {
                            dateForm = formatter.parse(date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                            holder.commentDateText.setText("Tarih görüntülenemiyor."); //????? NOT SO SURE
                        }
                        resultDate = desiredFormatter.format(dateForm);
                    }


                    content = gottenPost.getEmbedded().getComments().get(0).get(y).getContent().getCommentContent(); //Content setting

                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        html = (Spannable) Html.fromHtml(content, Html.FROM_HTML_MODE_LEGACY, null, null);
                    } else {
                        html = (Spannable) Html.fromHtml(content, null, null);
                    }


                    holder.commentAuthorNameText.setText(Html.fromHtml(commentAuthor));
                    holder.commentDateText.setText(resultDate);
                    holder.commentContentText.setText(html);
                    holder.commentContentText.setMovementMethod(LinkMovementMethod.getInstance());
                }


            }else {
                holder.commentAuthorNameText.setText("Henüz bir yorum yapılmamış.");
                holder.commentDateText.setText("");
                holder.commentDateText.setVisibility(View.GONE);
                holder.commentContentText.setText("");
                holder.commentContentText.setVisibility(View.GONE);
            }
        } else {
            holder.commentAuthorNameText.setText("Yorumlar devre dışı bırakıldı.");
            holder.commentDateText.setText("");
            holder.commentDateText.setVisibility(View.GONE);
            holder.commentContentText.setText("");
            holder.commentContentText.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        if(justForLength != null)
        return justForLength.get(0).size();
        else
            return 1;
    }
}
