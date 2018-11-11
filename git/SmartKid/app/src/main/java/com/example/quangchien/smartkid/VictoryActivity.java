package com.example.quangchien.smartkid;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class VictoryActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    Bitmap[] source = {null,null};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            DataBaseHelper dt = new DataBaseHelper(this);
            Bitmap bitmap = BitmapFactory.decodeByteArray(dt.getImageById("win"), 0, dt.getImageById("win").length);
            source[0] = bitmap;
            bitmap = BitmapFactory.decodeByteArray(dt.getImageById("win1"), 0, dt.getImageById("win1").length);
            source[1] = bitmap;

        } catch (Exception e){
            e.printStackTrace();
        }

        mediaPlayer = MediaPlayer.create(VictoryActivity.this, R.raw.gioi);
        mediaPlayer.start();
        setContentView(R.layout.activity_victory);

        ImageView im = (ImageView) findViewById(R.id.win);
        im.setImageBitmap(source[0]);
        im = (ImageView) findViewById(R.id.win1);
        im.setImageBitmap(source[1]);

    }

    public void clickToFinish(View view) {
            Intent intent1 = this.getIntent();
            this.setResult(RESULT_OK, intent1);
            mediaPlayer.stop();
           Intent intent = new Intent(VictoryActivity.this,MyMusicService.class);

            startService(intent);

            finish();
    }

}
