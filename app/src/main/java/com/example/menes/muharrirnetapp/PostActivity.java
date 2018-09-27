package com.example.menes.muharrirnetapp;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Html;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.menes.muharrirnetapp.PicAndPostHandling.BlogPost;
import com.example.menes.muharrirnetapp.PicAndPostHandling.PicassoImageGetter;
import com.example.menes.muharrirnetapp.RetrofitRelated.GetDataService;
import com.example.menes.muharrirnetapp.RetrofitRelated.RetrofitClientInstance;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
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
        progressDialog.setMessage("Yükleniyor...");
        progressDialog.show();

        final ImageView postFeaturedImage = findViewById(R.id.postFeaturedImage);
        final TextView postTitle = findViewById(R.id.postTitle);
        final TextView dateAuthorCategories = findViewById(R.id.dateAuthorCategories);
        final TextView postContent = findViewById(R.id.postContent);
        final TextView tags = findViewById(R.id.tags);
        final TextView commentAuthorNameText = findViewById(R.id.commentAuthorNameText);
        final TextView commentDateText = findViewById(R.id.commentDateText);
        final TextView commentContentText = findViewById(R.id.commentContentText);


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


                        String content = gottenPost.getContent().getPostContent(); //Content setting
                        Picasso.Builder maBuilder = new Picasso.Builder(PostActivity.this);
                        Picasso pablo = maBuilder.build();
                        PicassoImageGetter imageGetter = new PicassoImageGetter(PostActivity.this,postContent, getResources(), pablo);
                        Spannable html;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                            html = (Spannable) Html.fromHtml(content, Html.FROM_HTML_MODE_LEGACY, imageGetter, null);
                        } else {
                            html = (Spannable) Html.fromHtml(content, imageGetter, null);
                        }
                        postContent.setText(html);
                        postContent.setMovementMethod(LinkMovementMethod.getInstance());

                        if (gottenPost.getEmbedded().getFeaturedMedia() != null) {
                        String code = gottenPost.getEmbedded().getFeaturedMedia().get(0).getStatus();
                        if (code == null || !code.equals("rest_forbidden")) {
                            //Picture Setting
                                if (gottenPost.getEmbedded().getFeaturedMedia().get(0).getMediaDetails().getSizesInPicture().getLargePicture() != null) {
                                    Picasso.Builder builder = new Picasso.Builder(PostActivity.this);
                                    builder.downloader(new OkHttp3Downloader(PostActivity.this));
                                    builder.build().load(gottenPost.getEmbedded().getFeaturedMedia().get(0).getMediaDetails().getSizesInPicture().getLargePicture().getSourceUrl())
                                            .placeholder((R.drawable.muharrir_logo))
                                            .error(R.drawable.muharrir_logo)
                                            .into(postFeaturedImage);
                                    postFeaturedImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
                                } else if (gottenPost.getEmbedded().getFeaturedMedia().get(0).getMediaDetails().getSizesInPicture().getMediumLarge() != null) {
                                    Picasso.Builder builder = new Picasso.Builder(PostActivity.this);
                                    builder.downloader(new OkHttp3Downloader(PostActivity.this));
                                    builder.build().load(gottenPost.getEmbedded().getFeaturedMedia().get(0).getMediaDetails().getSizesInPicture().getMediumLarge().getSourceUrl())
                                            .placeholder((R.drawable.muharrir_logo))
                                            .error(R.drawable.muharrir_logo)
                                            .into(postFeaturedImage);
                                    postFeaturedImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
                                } else if (gottenPost.getEmbedded().getFeaturedMedia().get(0).getMediaDetails().getSizesInPicture().getMediumPicture() != null) {
                                    Picasso.Builder builder = new Picasso.Builder(PostActivity.this);
                                    builder.downloader(new OkHttp3Downloader(PostActivity.this));
                                    builder.build().load(gottenPost.getEmbedded().getFeaturedMedia().get(0).getMediaDetails().getSizesInPicture().getMediumPicture().getSourceUrl())
                                            .placeholder((R.drawable.muharrir_logo))
                                            .error(R.drawable.muharrir_logo)
                                            .into(postFeaturedImage);
                                    postFeaturedImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
                                } else {
                                    Picasso.Builder builder = new Picasso.Builder(PostActivity.this);
                                    builder.downloader(new OkHttp3Downloader(PostActivity.this));
                                    builder.build().load(gottenPost.getEmbedded().getFeaturedMedia().get(0).getMediaDetails().getSizesInPicture().getThumbnailInPicture().getSourceUrl())
                                            .placeholder((R.drawable.muharrir_logo))
                                            .error(R.drawable.muharrir_logo)
                                            .into(postFeaturedImage);
                                    postFeaturedImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
                                }
                            }
                        }

                        String date = gottenPost.getDate(); //Date, Author and Tag setting


                        StringBuilder xd = new StringBuilder();
                        String finalCategories = "";
                        String author = "";
                        String  finalEverything = "";

                        if(gottenPost.getEmbedded().getCategoryAndTags().get(0).size() != 0) {

                            for (int i = 0; i < gottenPost.getEmbedded().getCategoryAndTags().get(0).size(); i++) {
                                xd.append(gottenPost.getEmbedded().getCategoryAndTags().get(0).get(i).getCategoryName());
                                xd.append(", ");
                            }
                            finalCategories =  xd.toString().substring(0, xd.length() - 2);
                            author = gottenPost.getEmbedded().getAuthor().get(0).getName();

                        if(Build.VERSION.SDK_INT > 25 && date != null) {
                            DateTimeFormatter parseFormatter
                                    = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                            DateTimeFormatter newFormatter
                                    = DateTimeFormatter.ofPattern("dd/MM/uuuu");
                            String resultDate = LocalDateTime.parse(date, parseFormatter).format(newFormatter);

                                finalEverything = resultDate + "  /  " + author + "  /  " + finalCategories;
                                dateAuthorCategories.setText(finalEverything);
                            } else if (Build.VERSION.SDK_INT <= 25 && date != null){
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
                            SimpleDateFormat desiredFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

                            Date dateForm = null;
                            try {
                                dateForm = formatter.parse(date);
                            } catch (ParseException e) {
                                e.printStackTrace();
                                dateAuthorCategories.setText(date);
                            }
                            String dateString = desiredFormatter.format(dateForm);

                                finalEverything = dateString + "  /  " + author + "  /  " + finalCategories;
                                dateAuthorCategories.setText(finalEverything);
                        }
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
                            tags.setText("Etiket bulunamadı.");
                        }
                    }

                    //Comments will be handled in adapter

                    RecyclerView commentsRecyclerView = findViewById(R.id.commentsRecyclerView);
                    commentsRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));

                    CommentAdapter commentAdapter = new CommentAdapter(PostActivity.this,gottenPost.getEmbedded().getComments(),gottenPost);

                    ScaleInAnimationAdapter scaleInAnimationAdapter = new ScaleInAnimationAdapter(commentAdapter);
                    scaleInAnimationAdapter.setFirstOnly(false);
                    scaleInAnimationAdapter.setDuration(500);
                    scaleInAnimationAdapter.setInterpolator(new OvershootInterpolator());
                    commentsRecyclerView.setAdapter(scaleInAnimationAdapter);

                }
                else
                    Toast.makeText(PostActivity.this, R.string.err_resp, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<BlogPost> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(PostActivity.this, R.string.err_fail, Toast.LENGTH_SHORT).show();
            }
        });
    }
}