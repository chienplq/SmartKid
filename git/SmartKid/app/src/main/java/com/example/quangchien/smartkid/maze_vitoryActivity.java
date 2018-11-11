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

public class maze_vitoryActivity extends AppCompatActivity {
    byte[] hinh1=null, hinh2=null;
    Bitmap hinh = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            DataBaseHelper dt = new DataBaseHelper(this);
            Bitmap bitmap = BitmapFactory.decodeByteArray(dt.getImageById("maze_victory_caption"), 0, dt.getImageById("maze_victory_caption").length);
            hinh = bitmap;
            hinh1= dt.getImageById("maze_khian");
            hinh2= dt.getImageById("maze_khivui");
        } catch (Exception e){
            e.printStackTrace();
        }


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_maze_vitory);

        ImageView im = (ImageView) findViewById(R.id.nguoi);
        im.setImageBitmap(hinh);
        GifImageView im1 = (GifImageView) findViewById(R.id.khi);
        try {
            GifDrawable gif = new GifDrawable(hinh1);
            im1.setImageDrawable(gif);
        }catch (Exception e){
            e.printStackTrace();
        }
        im1 = (GifImageView) findViewById(R.id.khi1);
        try {
            GifDrawable gif = new GifDrawable(hinh2);
            im1.setImageDrawable(gif);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void ClickToOK(View view) {
        Intent intent = this.getIntent();
        this.setResult(RESULT_OK, intent);
        finish();
    }
}
