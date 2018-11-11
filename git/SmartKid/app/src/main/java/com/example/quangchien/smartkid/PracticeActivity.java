package com.example.quangchien.smartkid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class PracticeActivity extends AppCompatActivity {
    byte[] hinh = null, hinh1 = null, hinh2 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try {
            DataBaseHelper dt = new DataBaseHelper(this);
            hinh = dt.getImageById("apply_tho");
            hinh1= dt.getImageById("prac_gau");
            hinh2= dt.getImageById("plus");
        } catch (Exception e){
            e.printStackTrace();
        }

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_practice);

        GifImageView im1 = (GifImageView) findViewById(R.id.img1);
        try {
            GifDrawable gif = new GifDrawable(hinh);
            im1.setImageDrawable(gif);
        }catch (Exception e){
            e.printStackTrace();
        }
        im1 = (GifImageView) findViewById(R.id.img2);
        try {
            GifDrawable gif = new GifDrawable(hinh1);
            im1.setImageDrawable(gif);
        }catch (Exception e){
            e.printStackTrace();
        }
        im1 = (GifImageView) findViewById(R.id.imgPlus);
        try {
            GifDrawable gif = new GifDrawable(hinh2);
            im1.setImageDrawable(gif);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void clickToDifferent(View view) {
        Intent intent = new Intent(this,DifferentActivity.class);
        startActivity(intent);
    }

    public void clickToSimilar(View view) {
        Intent intent = new Intent(this,PickSamePictureActivity.class);
        startActivity(intent);
    }

    protected void onStop() {
        super.onStop();
        Intent intent = new Intent(PracticeActivity.this,MyMusicService.class);
        if(intent != null){
            stopService(intent);
        }
    }

    public void ClickToHome(View view) {
        finish();
    }
}
