package com.example.quangchien.smartkid;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class ApplyActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    private static int INPUT = 1;
    LinearLayout target1;
    GifImageView run, eat, run1, eat1, congra;
    int flag = 0, thutu = 0;
    Handler handler = new Handler();
    Bitmap[] source = {null, null, null};
    byte[] test = null, test1 = null, test3 = null;
    Timer T=new Timer();
    int count =0;
    Boolean fl = true;
    @Override
    protected void onStop() {
        fl =false;
        super.onStop();
        Intent intent = new Intent(ApplyActivity.this, MyMusicService.class);
        if (intent == null) {
            startService(intent);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        fl= true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            final DataBaseHelper dth = new DataBaseHelper(this);
            TimerTask abc =new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable()
                    {
                        @Override
                        public void run()
                        {if (fl) {
                            dth.saveTime(getDate());
                            if (dth.geLimitTime(getDate())<=dth.getSumTime(getDate())){
                                block();
                            }

                        }
                        }
                    });
                }
            };
            T.scheduleAtFixedRate(abc,10000, 10000);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {

            DataBaseHelper dt = new DataBaseHelper(this);
            Bitmap bitmap = BitmapFactory.decodeByteArray(dt.getImageById("apply_tho1"), 0, dt.getImageById("apply_tho1").length);
            test = dt.getImageById("apply_tho");
            source[0] = bitmap;
            bitmap = BitmapFactory.decodeByteArray(dt.getImageById("apply_ngua1"), 0, dt.getImageById("apply_ngua1").length);
            test1 = dt.getImageById("apply_ngua");
            source[1] = bitmap;

            bitmap = BitmapFactory.decodeByteArray(dt.getImageById("anhnull"), 0, dt.getImageById("anhnull").length);
            source[2] = bitmap;
            test3 = dt.getImageById("congrats");

        } catch (Exception e) {
            e.printStackTrace();
        }


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        Intent intent = new Intent(ApplyActivity.this, MyMusicService.class);
        if (intent != null) {
            stopService(intent);
        }
        mediaPlayer = MediaPlayer.create(ApplyActivity.this, R.raw.bong);
        mediaPlayer.start();
        setContentView(R.layout.activity_apply);
        //gan anh gif
        GifImageView im = (GifImageView) findViewById(R.id.img12);
        try {
            GifDrawable gif = new GifDrawable(test);
            im.setImageDrawable(gif);
        } catch (Exception e) {
            e.printStackTrace();
        }

        im = (GifImageView) findViewById(R.id.img22);
        try {
            GifDrawable gif = new GifDrawable(test1);
            im.setImageDrawable(gif);
        } catch (Exception e) {
            e.printStackTrace();
        }
        im = (GifImageView) findViewById(R.id.congra);
        try {
            GifDrawable gif = new GifDrawable(test3);
            im.setImageDrawable(gif);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //anh thuong
        im = (GifImageView) findViewById(R.id.img1);
        im.setImageBitmap(source[0]);

        im = (GifImageView) findViewById(R.id.img2);
        im.setImageBitmap(source[1]);

        congra = (GifImageView) findViewById(R.id.congra);
        run = (GifImageView) findViewById(R.id.img1);
        run1 = (GifImageView) findViewById(R.id.img12);
        eat = (GifImageView) findViewById(R.id.img2);
        eat1 = (GifImageView) findViewById(R.id.img22);
        target1 = (LinearLayout) findViewById(R.id.apply);

        run.setOnDragListener(onDragListener);
        eat.setOnDragListener(onDragListener);
        target1.setOnDragListener(drag);


        run1.setOnTouchListener(onTouchListener);
        eat1.setOnTouchListener(onTouchListener);

    }


    View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            ClipData clipData = ClipData.newPlainText("", "");
            View.DragShadowBuilder builder = new View.DragShadowBuilder(view);
            view.startDrag(clipData, builder, view, 0);

            if (view.getId() == run1.getId()) {
                run1.setVisibility(View.INVISIBLE);
            }
            if (view.getId() == eat1.getId()) {
                eat1.setVisibility(View.INVISIBLE);
            }


            return true;
        }
    };

    View.OnDragListener onDragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int dragEvent = event.getAction();
            final View view = (View) event.getLocalState();
            switch (dragEvent) {
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:

                    run1.setVisibility(View.VISIBLE);
                    eat1.setVisibility(View.VISIBLE);

                    if (view.getId() == R.id.img12 && v.getId() == R.id.img1) {
                        final GifImageView im = (GifImageView) findViewById(R.id.img1);
                        try {
                            GifDrawable gif = new GifDrawable(test);
                            im.setImageDrawable(gif);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        //im.setImageBitmap(source[0]);//im.setImageResource(R.drawable.apply_tho);
                        mediaPlayer = MediaPlayer.create(ApplyActivity.this, R.raw.cach);
                        mediaPlayer.start();
                        final GifImageView im1 = (GifImageView) findViewById(R.id.img12);
                        im1.setImageBitmap(source[2]);//im1.setImageResource(R.drawable.anhnull);
                        flag++;
                        if (flag == 2) {
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                                        mediaPlayer.pause();
                                    }

                                    mediaPlayer = MediaPlayer.create(ApplyActivity.this, R.raw.yeah);
                                    mediaPlayer.start();

                                    LinearLayout target = (LinearLayout) findViewById(R.id.imgTarget);
                                    target.setVisibility(View.GONE);
                                    congra = (GifImageView) findViewById(R.id.congra);
                                    congra.setVisibility(View.VISIBLE);
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                                                mediaPlayer.pause();
                                            }
                                            changeImage();
                                        }
                                    }, 2000);

                                    flag = 0;


                                }
                            }, 2000);
                        }

                    } else if (view.getId() == R.id.img22 && v.getId() == R.id.img2) {

                        final GifImageView im = (GifImageView) findViewById(R.id.img2);
                        try {
                            GifDrawable gif = new GifDrawable(test1);
                            im.setImageDrawable(gif);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        // im.setImageBitmap(source[1]);//im.setImageResource(R.drawable.apply_ngua);
                        mediaPlayer = MediaPlayer.create(ApplyActivity.this, R.raw.cach);
                        mediaPlayer.start();
                        final GifImageView im1 = (GifImageView) findViewById(R.id.img22);
                        im1.setImageBitmap(source[2]);//im1.setImageResource(R.drawable.anhnull);
                        flag++;
                        if (flag == 2) {
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {


                                    if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                                        mediaPlayer.pause();
                                    }
                                    mediaPlayer = MediaPlayer.create(ApplyActivity.this, R.raw.yeah);
                                    mediaPlayer.start();
                                    LinearLayout target = (LinearLayout) findViewById(R.id.imgTarget);
                                    target.setVisibility(View.GONE);
                                    congra = (GifImageView) findViewById(R.id.congra);
                                    congra.setVisibility(View.VISIBLE);
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                                                mediaPlayer.pause();
                                            }
                                            changeImage();
                                        }
                                    }, 2000);

                                    flag = 0;

                                }

                            }, 2000);
                        }

                    }
                    break;

            }
            return true;
        }
    };

    public void changeImage() {
        //thutu++;
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
        Intent intent = new Intent(this, Apply2Activity.class);
        startActivityForResult(intent, INPUT);
        finish();


//        try {
////            run1.setVisibility(View.VISIBLE);
////            eat1.setVisibility(View.VISIBLE);
//            run.setImageResource(img1[thutu]);
//            run1.setImageResource(img3[thutu]);
//            eat.setImageResource(img2[thutu]);
//            eat1.setImageResource(img4[thutu]);
//        }catch ( Exception e ){
//            intent = new Intent(this,ObserverActivity.class);
//            startActivity(intent);
//        }
    }

    View.OnDragListener drag = new View.OnDragListener() {
        @Override
        public boolean onDrag(View view, DragEvent dragEvent) {
            int d = dragEvent.getAction();
            final View v = (View) dragEvent.getLocalState();
            switch (d) {
                case DragEvent.ACTION_DRAG_ENTERED:
//                    ImageView btn11= (ImageView) dragEvent.getLocalState();
//                    btn11.setVisibility(View.INVISIBLE);
//                    break;
                case DragEvent.ACTION_DRAG_EXITED:
//                    ImageView btn12= (ImageView) dragEvent.getLocalState();
//                    btn12.setVisibility(View.VISIBLE);
//                    break;
                case DragEvent.ACTION_DROP:
                    run1.setVisibility(View.VISIBLE);
                    eat1.setVisibility(View.VISIBLE);
                    break;

            }
            return true;
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == INPUT) {
            if (resultCode == RESULT_OK) {
                finish();
            }
        }
    }
    public String getDate(){
        Date today = new Date();
        int abc = today.getYear();
        return (today.getDate()+"/" +today.getMonth()+"/"+today.getYear());
    }
    public void block() {
        Intent intent = new Intent(this, SetTimePlay.class);
        startActivity(intent);
    }
}
