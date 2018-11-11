package com.example.quangchien.smartkid;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class ObserverActivity extends AppCompatActivity {

    byte[] hinh=null, hinh1=null,hinh2=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            DataBaseHelper dt = new DataBaseHelper(this);
            hinh = dt.getImageById("prac_gau");
            hinh1= dt.getImageById("ap_cao");
            hinh2= dt.getImageById("plus");
        } catch (Exception e){
            e.printStackTrace();
        }

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_observer);

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
    public void clickToApply(View view) {

        Intent intent = new Intent(this,ApplyActivity.class);
        startActivity(intent);


    }
    @Override
    protected void onStop() {
        super.onStop();
        Intent intent = new Intent(ObserverActivity.this,MyMusicService.class);
        if(intent != null){
            stopService(intent);
        }
    }

    public void clickToArrange(View view) {
        Intent intent = new Intent(this,ArrangeActivity.class);
        startActivity(intent);

    }

    public void ClickToHome(View view) {
        finish();


    }
}
