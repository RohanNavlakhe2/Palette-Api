package com.example.paletteapi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.palette.graphics.Palette;

import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.List;

public class PaletteEffect extends AppCompatActivity {

    private ImageView storeImg;
    private LinearLayout storeImgBg;
    private int img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette_effect);

        storeImg = findViewById(R.id.storeImg);
        storeImgBg = findViewById(R.id.storeImgBg);

        img = getIntent().getIntExtra("img", 0);

        puttingColorToCollapsingToolbar();

    }

    private void puttingColorToCollapsingToolbar() {
        Glide.with(getApplicationContext())
                .asBitmap()
                .load(img)                //first load the image
                .thumbnail(0.5f)
                .into(new BitmapImageViewTarget(storeImg) {  //location where you want to load image
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        super.onResourceReady(resource, transition);

                        //creating palette instance
                        Palette.from(resource).generate(new Palette.PaletteAsyncListener() {
                            @Override
                            public void onGenerated(@Nullable Palette palette) {
                                //We are taking default color as colorPrimary because in case
                                //if palette or swatch is null then this color should be applied.
                                int backgroundColor = ContextCompat.getColor(PaletteEffect.this, R.color.colorPrimary);
                                if (palette != null) {
                                    Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
                                    //By default we have 6 types of swatches you can choose anyone of them.
                                    //palette.getDarkMutedSwatch()
                                    // palette.getDarkVibrantSwatch()
                                    //palette.getLightMutedSwatch()
                                    //palette.getLightVibrantSwatch()
                                    //palette.getMutedSwatch()
                                    //Here I'm taking vibrant swatch
                                    //There are more than 6 swathces available
                                    //If you want to try all swatches then do like that:
                                    //List<Palette.Swatch> swatches=palette.getSwatches();

                                    if (vibrantSwatch != null)
                                        backgroundColor = vibrantSwatch.getRgb();


                                }
                                storeImgBg.setBackgroundColor(backgroundColor);
                                //changing toolbar color
                                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(backgroundColor));

                            }
                        });
                    }
                });


    }
}
