package com.example.quangchien.smartkid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class thinkingActivity extends AppCompatActivity {
    byte[] hinh1=null;
    Bitmap hinh = null, hinh2 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            DataBaseHelper dt = new DataBaseHelper(this);
            Bitmap bitmap = BitmapFactory.decodeByteArray(dt.getImageById("mecung"), 0, dt.getImageById("mecung").length);
            hinh = bitmap;
            bitmap = BitmapFactory.decodeByteArray(dt.getImageById("homebutton"), 0, dt.getImageById("homebutton").length);
            hinh2 = bitmap;
            hinh1= dt.getImageById("feedanimal");
        } catch (Exception e){
            e.printStackTrace();
        }

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_thinking);

        ImageView im = (ImageView) findViewById(R.id.mecung);
        im.setImageBitmap(hinh);
        im = (ImageView) findViewById(R.id.back);
        im.setImageBitmap(hinh2);
        GifImageView im1 = (GifImageView) findViewById(R.id.feed);
        try {
            GifDrawable gif = new GifDrawable(hinh1);
            im1.setImageDrawable(gif);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void ClickToFinish(View view) {
        finish();
    }

    public void ClickToMaze(View view) {
        Intent intent = new Intent(this, MazeActivity.class);
        startActivity(intent);

    }

    public void ClickToFeedAnimal(View view) {
        Intent intent = new Intent(this, FeedAnimalActivity.class);
        startActivity(intent);
    }
}
