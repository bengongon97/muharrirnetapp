package com.example.menes.muharrirnetapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.menes.muharrirnetapp.RetrofitRelated.GetDataService;
import com.example.menes.muharrirnetapp.RetrofitRelated.RetrofitClientInstance;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity {

    ProgressDialog progressDialog; //DEPRECATED! OMG!
    BlogPost gottenPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        progressDialog = new ProgressDialog(PostActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        final ImageView postFeaturedImage = findViewById(R.id.postFeaturedImage);
        final TextView postTitle = findViewById(R.id.postTitle);
        final TextView dateAuthorCategories = findViewById(R.id.dateAuthorCategories);
        final TextView postContent = findViewById(R.id.postContent);
        final TextView tags = findViewById(R.id.tags);
        TextView commentsAndSoOn = findViewById(R.id.comments);


        String postId= getIntent().getStringExtra("postId");


        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<BlogPost> call = service.getPostContent(postId);

        call.enqueue(new Callback<BlogPost>() {
            @Override
            public void onResponse(Call<BlogPost> call, Response<BlogPost> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()){
                    gottenPost = response.body();

                    if(gottenPost.getEmbedded() != null) {

                        postTitle.setText(gottenPost.getTitle().getPostTitle()); //Title setting

                        String formattedExcerptinPost = Html.fromHtml(gottenPost.getContent().getPostContent()).toString().trim();
                        postContent.setText(formattedExcerptinPost); //Content setting

                        if(gottenPost.getEmbedded().getFeaturedMedia() != null) {
                            if (gottenPost.getEmbedded().getFeaturedMedia().get(0).getMediaDetails().getSizesInPicture().getLargePicture() != null) { //Picture Setting
                                Picasso.Builder builder = new Picasso.Builder(PostActivity.this);
                                builder.downloader(new OkHttp3Downloader(PostActivity.this));
                                builder.build().load(gottenPost.getEmbedded().getFeaturedMedia().get(0).getMediaDetails().getSizesInPicture().getLargePicture().getSourceUrl())
                                        .placeholder((R.drawable.muharrir_logo))
                                        .error(R.drawable.muharrir_logo)
                                        .into(postFeaturedImage);
                            } else if (gottenPost.getEmbedded().getFeaturedMedia().get(0).getMediaDetails().getSizesInPicture().getMediumLarge() != null) {
                                Picasso.Builder builder = new Picasso.Builder(PostActivity.this);
                                builder.downloader(new OkHttp3Downloader(PostActivity.this));
                                builder.build().load(gottenPost.getEmbedded().getFeaturedMedia().get(0).getMediaDetails().getSizesInPicture().getMediumLarge().getSourceUrl())
                                        .placeholder((R.drawable.muharrir_logo))
                                        .error(R.drawable.muharrir_logo)
                                        .into(postFeaturedImage);
                            } else if (gottenPost.getEmbedded().getFeaturedMedia().get(0).getMediaDetails().getSizesInPicture().getMediumPicture() != null) {
                                Picasso.Builder builder = new Picasso.Builder(PostActivity.this);
                                builder.downloader(new OkHttp3Downloader(PostActivity.this));
                                builder.build().load(gottenPost.getEmbedded().getFeaturedMedia().get(0).getMediaDetails().getSizesInPicture().getMediumPicture().getSourceUrl())
                                        .placeholder((R.drawable.muharrir_logo))
                                        .error(R.drawable.muharrir_logo)
                                        .into(postFeaturedImage);
                            } else {
                                Picasso.Builder builder = new Picasso.Builder(PostActivity.this);
                                builder.downloader(new OkHttp3Downloader(PostActivity.this));
                                builder.build().load(gottenPost.getEmbedded().getFeaturedMedia().get(0).getMediaDetails().getSizesInPicture().getThumbnailInPicture().getSourceUrl())
                                        .placeholder((R.drawable.muharrir_logo))
                                        .error(R.drawable.muharrir_logo)
                                        .into(postFeaturedImage);
                            }
                        }

                        String date = gottenPost.getDate(); //Date, Author and Tag setting
                        if(Build.VERSION.SDK_INT > 25 && date != null) {

                            DateTimeFormatter parseFormatter
                                    = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                            DateTimeFormatter newFormatter
                                    = DateTimeFormatter.ofPattern("dd/MM/uuuu");
                            String resultDate = LocalDateTime.parse(date, parseFormatter).format(newFormatter);

                            if(gottenPost.getEmbedded().getCategoryAndTags().get(0).size() != 0) {
                                StringBuilder xd = new StringBuilder();
                                String finalCategories = "";
                                for (int i = 0; i < gottenPost.getEmbedded().getCategoryAndTags().get(0).size(); i++) {
                                    xd.append(gottenPost.getEmbedded().getCategoryAndTags().get(0).get(i).getCategoryName());
                                    xd.append(", ");
                                }
                                finalCategories =  xd.toString().substring(0, xd.length() - 2);
                                String author = gottenPost.getEmbedded().getAuthor().get(0).getName();

                                String finalEverything = resultDate + "  /  " + author + "  /  " + finalCategories;
                                dateAuthorCategories.setText(finalEverything);
                            }
                        }
                        else{
                           dateAuthorCategories.setText("We still work on low API's");
                        }


                        if(gottenPost.getEmbedded().getCategoryAndTags().get(1).size() != 0) { //Tag Setting
                            StringBuilder builder1 = new StringBuilder();
                            String finalxdd = "";
                            for (int i = 0; i < gottenPost.getEmbedded().getCategoryAndTags().get(1).size(); i++) {
                                builder1.append(gottenPost.getEmbedded().getCategoryAndTags().get(1).get(i).getCategoryName());//written categoryname but it will take tags too
                                builder1.append(", ");
                            }
                            finalxdd =  builder1.toString().substring(0, builder1.length() - 2);
                            finalxdd = "Etiketler: " + finalxdd;
                            tags.setText(finalxdd);
                        } else{
                            tags.setText("No tags");
                        }
                    }

                    //Comment settings will come later.
                }
                else
                    Toast.makeText(PostActivity.this, "Response was not successful...Please try later!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<BlogPost> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(PostActivity.this, "Something went wrong...Please try again by swiping down!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
