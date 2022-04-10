package com.example.aditya_prakash_nair_project2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class EnlargeImageActivity extends AppCompatActivity {

    private int landscapeID = -1;
    private int portraitID = -1;
    private ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        landscapeID = intent.getIntExtra(MainActivity.LANDSCAPE_ID, 0);
        portraitID = intent.getIntExtra(MainActivity.IMAGE_ID, 0);

        imgView = new ImageView(getApplicationContext());

//        Log.i("ImageViewActivity", "Image id = " + intent.getIntExtra(MainActivity.IMAGE_ID, 0));
        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            imgView.setImageResource(landscapeID);
        } else {
            imgView.setImageResource(portraitID);
        }

        setContentView(imgView);

        imgView.setOnClickListener((view) -> {

            Intent imageClickIntent = new Intent(EnlargeImageActivity.this, Facts.class);
            int pos = intent.getIntExtra(MainActivity.POS, 0);
            Log.i("Enlarger Activity","Postiton = " + pos);

            imageClickIntent.putExtra(MainActivity.POS, pos);
            startActivity(imageClickIntent);

        });
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        Log.i("Enlarege Activity","Landscape = " + landscapeID);
        super.onConfigurationChanged(newConfig);
        if(landscapeID != -1 && newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            imgView.setImageResource(landscapeID);
            setContentView(imgView);
        } else {
            imgView.setImageResource(portraitID);
            setContentView(imgView);
        }

    }
}