package com.example.quangchien.smartkid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import pl.droidsonroids.gif.GifImageView;

public class FeedAnimalActivity extends AppCompatActivity {
    GestureDetector gestureDetector;
    int khoangCach = 100;
    int vanToc = 100;
    int flag = 0, soLan = 0;
    Handler handler = new Handler();
    Bitmap home = null;
    Bitmap[] source = {null, null, null, null, null, null, null, null};
    Bitmap[] source2 = {null, null};
    Timer T=new Timer();

    Boolean fl = true;
//    int thucAnList[] = {R.drawable.feedanimal_banh, R.drawable.feedanimal_banhtroinuoc, R.drawable.feedanimal_banhtn,
//            R.drawable.feedanimal_banhtn2, R.drawable.feedanimal_banhngot, R.drawable.feedanimal_banhngot2,
//            R.drawable.feedanimal_banhngot3, R.drawable.feedanimal_banhngot4};

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
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
//        getWindow().addFlags(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
//        View decorView = getWindow().getDecorView();
//        ;
//        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_FULLSCREEN;
//        decorView.setSystemUiVisibility(uiOptions);
        try {

            DataBaseHelper dt = new DataBaseHelper(this);
            Bitmap bitmap = BitmapFactory.decodeByteArray( dt.getImageById("feedanimal_banh"),0, dt.getImageById("feedanimal_banh").length);
            source[0] = bitmap;
            bitmap = BitmapFactory.decodeByteArray( dt.getImageById("homebutton"),0, dt.getImageById("homebutton").length);
            home = bitmap;
            bitmap = BitmapFactory.decodeByteArray( dt.getImageById("feedanimal_banhtroinuoc"),0, dt.getImageById("feedanimal_banhtroinuoc").length);
            source[1] = bitmap;
            bitmap = BitmapFactory.decodeByteArray( dt.getImageById("feedanimal_banhtn"),0, dt.getImageById("feedanimal_banhtn").length);
            source[2] = bitmap;
            bitmap = BitmapFactory.decodeByteArray( dt.getImageById("feedanimal_banhtn2"),0, dt.getImageById("feedanimal_banhtn2").length);
            source[3] = bitmap;
            bitmap = BitmapFactory.decodeByteArray( dt.getImageById("feedanimal_banhngot"),0, dt.getImageById("feedanimal_banhngot").length);
            source[4] = bitmap;
            bitmap = BitmapFactory.decodeByteArray( dt.getImageById("feedanimal_banhngot2"),0, dt.getImageById("feedanimal_banhngot2").length);
            source[5] = bitmap;
            bitmap = BitmapFactory.decodeByteArray( dt.getImageById("feedanimal_banhngot3"),0, dt.getImageById("feedanimal_banhngot3").length);
            source[6] = bitmap;
            bitmap = BitmapFactory.decodeByteArray( dt.getImageById("feedanimal_banhngot4"),0, dt.getImageById("feedanimal_banhngot4").length);
            source[7] = bitmap;
            bitmap = BitmapFactory.decodeByteArray( dt.getImageById("feedanimal_pony"),0, dt.getImageById("feedanimal_pony").length);
            source2[0] = bitmap;
            bitmap = BitmapFactory.decodeByteArray( dt.getImageById("feedanimal_pikachu"),0, dt.getImageById("feedanimal_pikachu").length);
            source2[1] = bitmap;

        } catch (Exception e) {
            e.printStackTrace();
        }

        setContentView(R.layout.activity_feed_animal);
        ImageView imageView1 = findViewById(R.id.feedanimal_pikachu);
        imageView1.setImageBitmap(source2[1]);
        imageView1 = findViewById(R.id.feedanimal_pony);
        imageView1.setImageBitmap(source2[0]);
        imageView1 = findViewById(R.id.feedanimal_thucan);
        imageView1.setImageBitmap(source[0]);
        imageView1 = findViewById(R.id.feedanimal_img_pikachuwish);
        imageView1.setImageBitmap(source[0]);
        LinearLayout ln1 = findViewById(R.id.feedanimal_layout_ponywish);
        ln1.setVisibility(View.INVISIBLE);
        ImageView imageView = findViewById(R.id.feedanimal_thucan);
        gestureDetector = new GestureDetector(this, new MyGeture());
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return true;
            }
        });
    }

    class MyGeture extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            //trái sang phải
            if (e2.getX() - e1.getX() > khoangCach && Math.abs(velocityX) > vanToc) {
                if (flag >= (source.length / 2)) {
                    final ImageView image = findViewById(R.id.feedanimal_thucan);
                    image.setVisibility(View.INVISIBLE);
                    final GifImageView im = findViewById(R.id.feedanimal_pony);
                    im.setImageResource(R.drawable.feedanimal_pony_eating);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            im.setImageBitmap(source2[0]);//im.setImageResource(R.drawable.feedanimal_pony);
                            image.setVisibility(View.VISIBLE);
                            if (soLan>12) {finish();}
                            doiAnh();
                        }
                    }, 2000);


                }
//                 im.setImageResource(R.drawable.pikachu1);

            }
            //Phải sang trái
            if (e1.getX() - e2.getX() > khoangCach && Math.abs(velocityX) > vanToc) {
                if (flag < (source.length / 2)) {
                    final GifImageView im = findViewById(R.id.feedanimal_pikachu);
                    im.setImageResource(R.drawable.feedanimal_pikachu_eating);
                    final ImageView image = findViewById(R.id.feedanimal_thucan);
                    image.setVisibility(View.INVISIBLE);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            im.setImageBitmap(source2[1]);//im.setImageResource(R.drawable.feedanimal_pikachu);
                            image.setVisibility(View.VISIBLE);
                            if (soLan>12) {finish();}
                            doiAnh();
                        }
                    }, 2000);
//                 im.setImageResource(R.drawable.pony);
                }
            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }

        void doiAnh() {
            ImageView anh = findViewById(R.id.feedanimal_thucan);
            Random rd = new Random();
            int num = rd.nextInt(source.length);

            anh.setImageBitmap(source[num]);
            flag = num;
            if (flag < (source.length / 2)) {
                LinearLayout ln = findViewById(R.id.feedanimal_layout_pikachuwish);
                ln.setVisibility(View.VISIBLE);
                ImageView img = findViewById(R.id.feedanimal_img_pikachuwish);
                img.setImageBitmap(source[flag]);
                LinearLayout ln1 = findViewById(R.id.feedanimal_layout_ponywish);
                ln1.setVisibility(View.INVISIBLE);
            } else {
                LinearLayout ln = findViewById(R.id.feedanimal_layout_pikachuwish);
                ln.setVisibility(View.INVISIBLE);

                LinearLayout ln1 = findViewById(R.id.feedanimal_layout_ponywish);
                ln1.setVisibility(View.VISIBLE);
                ImageView img = findViewById(R.id.feedanimal_img_ponywish);
                img.setImageBitmap(source[flag]);
            }
            soLan++;
        }
    }

    public void ClickToFinish(View view) {
        finish();
    }
    public void impotImage(){


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
    @Override
    protected void onResume() {
        super.onResume();
        fl= true;
    }


    @Override
    protected void onStop() {
        super.onStop();
        fl =false;

    }
}
