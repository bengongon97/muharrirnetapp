package com.example.menes.muharrirnetapp.PicAndPostHandling;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.menes.muharrirnetapp.PostActivity;
import com.squareup.picasso.Picasso;

public class PicassoImageGetter implements Html.ImageGetter {

    final Resources resources;

    final Picasso pablo;

    final TextView textView;

    final Context context;

    public PicassoImageGetter(final Context context, final TextView textView, final Resources resources, final Picasso pablo) {
        this.textView = textView;
        this.resources = resources;
        this.pablo = pablo;
        this.context = context;
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public Drawable getDrawable(final String source) {
        final BitmapDrawablePlaceHolder result = new BitmapDrawablePlaceHolder();

        new AsyncTask<Void, Void, Bitmap>() {

            @Override
            protected Bitmap doInBackground(final Void... meh) {
                try {
                    return pablo.load(source).get();
                } catch (Exception e) {
                    return null;
                }
            }

            @Override
            protected void onPostExecute(final Bitmap bitmap) {
                try {
                    final BitmapDrawable drawable = new BitmapDrawable(resources, bitmap);

                    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

                    result.setDrawable(drawable);

                    WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
                    try {
                        Display display = wm.getDefaultDisplay();
                        Point size = new Point();
                        display.getSize(size);
                        int width = size.x;
                        //int height = size.y;

                        result.setBounds(0, 0, width, drawable.getIntrinsicHeight());
                    } catch (NullPointerException e) {
                        result.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                    }

                    textView.setText(textView.getText()); // invalidate() doesn't work correctly...
                } catch (Exception e) {
                    //things
                }
            }

        }.execute((Void) null);

        return result;
    }

    @SuppressWarnings("Deprecation")
    static class BitmapDrawablePlaceHolder extends BitmapDrawable {

        protected Drawable drawable;

        @Override
        public void draw(final Canvas canvas) {
            if (drawable != null) {
                drawable.draw(canvas);
            }
        }

        public void setDrawable(Drawable drawable) {
            this.drawable = drawable;
        }

    }
}