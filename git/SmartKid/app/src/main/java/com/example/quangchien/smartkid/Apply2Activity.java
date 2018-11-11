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

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class Apply2Activity extends AppCompatActivity {
    MediaPlayer mediaPlayerCach, mediaPlayeryeah;
    private static int INPUT = 1;
    LinearLayout target1,target;
    GifImageView anh1, anh2,anh3,anh4,anh11,anh22,anh33,anh44, congra;

    int flag = 0, thutu=0;
    Handler handler = new Handler();

    byte[] win = null;
    Bitmap[]  source= {null, null, null};
    Bitmap[]  source1= {null, null, null};
    Bitmap[]  source2= {null, null, null};
    Bitmap[]  source3= {null, null, null};
    byte[][] test1 = {null,null,null};
    byte[][] test2 = { null,null,null};
    byte[][] test3 =  {  null,null,null};
    byte[][] test4 ={  null,null,null};
    Bitmap anhNull = null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            DataBaseHelper dt = new DataBaseHelper(this);
            Bitmap bitmap = BitmapFactory.decodeByteArray( dt.getImageById("ap_ca1"),0, dt.getImageById("ap_ca1").length);
            test1[0]= dt.getImageById("ap_ca");
            win = dt.getImageById("congrats");
            source[0] = bitmap;
            bitmap = BitmapFactory.decodeByteArray( dt.getImageById("bo1"),0, dt.getImageById("bo1").length);
            test1[1]= dt.getImageById("bo");
            source[1] = bitmap;
            bitmap = BitmapFactory.decodeByteArray( dt.getImageById("anhnull"),0, dt.getImageById("anhnull").length);
            anhNull = bitmap;
            bitmap = BitmapFactory.decodeByteArray( dt.getImageById("ap_cao1"),0, dt.getImageById("ap_cao1").length);
            source[2] = bitmap;
            test1[2]= dt.getImageById("ap_cao");

            bitmap = BitmapFactory.decodeByteArray( dt.getImageById("ap_gau1"),0, dt.getImageById("ap_gau1").length);
            test2[0]= dt.getImageById("ap_gau");
            source1[0] = bitmap;
            bitmap = BitmapFactory.decodeByteArray( dt.getImageById("ap_cavoi1"),0, dt.getImageById("ap_cavoi1").length);
            test2[1]= dt.getImageById("ap_cavoi");
            source1[1] = bitmap;
            bitmap = BitmapFactory.decodeByteArray( dt.getImageById("ap_cho1"),0, dt.getImageById("ap_cho1").length);
            source1[2] = bitmap;
            test2[2]= dt.getImageById("ap_cho");

            bitmap = BitmapFactory.decodeByteArray( dt.getImageById("ap_meo1"),0, dt.getImageById("ap_meo1").length);
            test3[0]= dt.getImageById("ap_meo");
            source2[0] = bitmap;
            bitmap = BitmapFactory.decodeByteArray( dt.getImageById("ap_voi1"),0, dt.getImageById("ap_voi1").length);
            test3[1]= dt.getImageById("ap_voi");
            source2[1] = bitmap;
            bitmap = BitmapFactory.decodeByteArray( dt.getImageById("ap_vet1"),0, dt.getImageById("ap_vet1").length);
            source2[2] = bitmap;
            test3[2]= dt.getImageById("ap_vet");

            bitmap = BitmapFactory.decodeByteArray( dt.getImageById("apply_huucaoco1"),0, dt.getImageById("apply_huucaoco1").length);
            test4[0]= dt.getImageById("apply_huucaoco");
            source3[0] = bitmap;
            bitmap = BitmapFactory.decodeByteArray( dt.getImageById("ap_gautruc1"),0, dt.getImageById("ap_gautruc1").length);
            test4[1]= dt.getImageById("ap_gautruc");
            source3[1] = bitmap;
            bitmap = BitmapFactory.decodeByteArray( dt.getImageById("apply_tho1"),0, dt.getImageById("apply_tho1").length);
            source3[2] = bitmap;
            test4[2]= dt.getImageById("apply_tho");
        }catch (Exception e){
            e.printStackTrace();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_apply2 );

        mediaPlayerCach = MediaPlayer.create(Apply2Activity.this, R.raw.cach);
        mediaPlayeryeah = MediaPlayer.create(Apply2Activity.this, R.raw.yeah);

        GifImageView im = (GifImageView) findViewById(R.id.img1);
        try {
            GifDrawable gif = new GifDrawable(test4[0]);
            im.setImageDrawable(gif);
        }catch (Exception e){
            e.printStackTrace();
        }
        im = (GifImageView) findViewById(R.id.img2);
        try {
            GifDrawable gif = new GifDrawable(test2[0]);
            im.setImageDrawable(gif);
        }catch (Exception e){
            e.printStackTrace();
        }
        im = (GifImageView) findViewById(R.id.img3);
        try {
            GifDrawable gif = new GifDrawable(test3[0]);
            im.setImageDrawable(gif);
        }catch (Exception e){
            e.printStackTrace();
        }
        im = (GifImageView) findViewById(R.id.img4);
        try {
            GifDrawable gif = new GifDrawable(test1[0]);
            im.setImageDrawable(gif);
        }catch (Exception e){
            e.printStackTrace();
        }
        im = (GifImageView) findViewById(R.id.congra);
        try {
            GifDrawable gif = new GifDrawable(win);
            im.setImageDrawable(gif);
        }catch (Exception e){
            e.printStackTrace();
        }
        im = (GifImageView) findViewById(R.id.img11);
        im.setImageBitmap(source3[0]);
        im = (GifImageView) findViewById(R.id.img22);
        im.setImageBitmap(source1[0]);
        im = (GifImageView) findViewById(R.id.img33);
        im.setImageBitmap(source2[0]);
        im = (GifImageView) findViewById(R.id.img44);
        im.setImageBitmap(source[0]);

        anh1 = (GifImageView) findViewById(R.id.img1);
        anh2 = (GifImageView) findViewById(R.id.img2);
        anh3 = (GifImageView) findViewById(R.id.img3);
        anh4 = (GifImageView) findViewById(R.id.img4);
        anh11 = (GifImageView) findViewById(R.id.img11);
        anh22 = (GifImageView) findViewById(R.id.img22);
        anh33 = (GifImageView) findViewById(R.id.img33);
        anh44 = (GifImageView) findViewById(R.id.img44);
        target1 = (LinearLayout) findViewById(R.id.apply2);
        anh1.setOnTouchListener(onTouchListener);
        anh2.setOnTouchListener(onTouchListener);
        anh3.setOnTouchListener(onTouchListener);
        anh4.setOnTouchListener(onTouchListener);

        anh11.setOnDragListener(onDragListener);
        anh22.setOnDragListener(onDragListener);
        anh33.setOnDragListener(onDragListener);
        anh44.setOnDragListener(onDragListener);
        target1.setOnDragListener(drag);




    }


    View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            ClipData clipData = ClipData.newPlainText("","");
            View.DragShadowBuilder builder = new View.DragShadowBuilder(view);
            view.startDrag(clipData,builder,view,0);
            if(mediaPlayerCach.isPlaying()){
                mediaPlayerCach.stop();
            }

            if(view.getId() == anh1.getId()){
                anh1.setVisibility(View.INVISIBLE);
            }
            if(view.getId() == anh2.getId()){
                anh2.setVisibility(View.INVISIBLE);
            }
            if(view.getId() == anh3.getId()){
                anh3.setVisibility(View.INVISIBLE);
            }
            if(view.getId() == anh4.getId()){
                anh4.setVisibility(View.INVISIBLE);
            }


            return true;
        }
    };

    View.OnDragListener onDragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int dragEvent = event.getAction();
            final View view = (View) event.getLocalState();
            switch (dragEvent){
                case DragEvent.ACTION_DRAG_ENTERED:

                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:

                    anh1.setVisibility(View.VISIBLE);
                    anh2.setVisibility(View.VISIBLE);
                    anh3.setVisibility(View.VISIBLE);
                    anh4.setVisibility(View.VISIBLE);

                    if(view.getId() == R.id.img1 && v.getId() == R.id.img11){

                        mediaPlayerCach.start();
                        final  GifImageView im = (GifImageView) findViewById(R.id.img11);
                        try {
                            GifDrawable gif = new GifDrawable(test4[thutu]);
                            im.setImageDrawable(gif);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        final GifImageView im1 = (GifImageView) findViewById(R.id.img1);
                       im1.setImageBitmap(anhNull);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                flag++;
                                if(flag == 4){
                                        mediaPlayeryeah.start();

                                    target = (LinearLayout) findViewById(R.id.imgTarget) ;
                                    target.setVisibility(View.GONE);
                                    congra= (GifImageView) findViewById(R.id.congra);
                                    congra.setVisibility(View.VISIBLE);
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            changeImage();
                                        }
                                    },1500);

                                    flag = 0;

                                }
                            }
                        },2000);

                    }else if(view.getId() == R.id.img2 && v.getId() == R.id.img22){

                        mediaPlayerCach.start();
                        final GifImageView  im = (GifImageView) findViewById(R.id.img22);
                        try {
                            GifDrawable gif = new GifDrawable(test2[thutu]);
                            im.setImageDrawable(gif);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        final GifImageView im1 = (GifImageView) findViewById(R.id.img2);
                        im1.setImageBitmap(anhNull);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                flag++;
                                if(flag == 4){
                                        mediaPlayeryeah.start();

                                    target = (LinearLayout) findViewById(R.id.imgTarget) ;
                                    target.setVisibility(View.GONE);
                                    congra= (GifImageView) findViewById(R.id.congra);
                                    congra.setVisibility(View.VISIBLE);
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            changeImage();
                                        }
                                    },1500);

                                    flag = 0;

                                }
                            }
                        },2000);

                    }else
                    if(view.getId() == R.id.img3 && v.getId() == R.id.img33){

                        mediaPlayerCach.start();
                        final GifImageView im = (GifImageView) findViewById(R.id.img33);
                        try {
                            GifDrawable gif = new GifDrawable(test3[thutu]);
                            im.setImageDrawable(gif);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        final GifImageView im1 = (GifImageView) findViewById(R.id.img3);
                        im1.setImageBitmap(anhNull);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                flag++;
                                if(flag == 4){
                                        mediaPlayeryeah.start();

                                    target = (LinearLayout) findViewById(R.id.imgTarget) ;
                                    target.setVisibility(View.GONE);
                                    congra= (GifImageView) findViewById(R.id.congra);
                                    congra.setVisibility(View.VISIBLE);
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {

                                            changeImage();
                                        }
                                    },1500);

                                    flag = 0;

                                }
                            }
                        },2000);


                    }else if(view.getId() == R.id.img4 && v.getId() == R.id.img44){

                        mediaPlayerCach.start();
                        final GifImageView  im = (GifImageView) findViewById(R.id.img44);
                        try {
                            GifDrawable gif = new GifDrawable(test1[thutu]);
                            im.setImageDrawable(gif);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        final GifImageView im1 = (GifImageView) findViewById(R.id.img4);
                        im1.setImageBitmap(anhNull);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                flag++;
                                if(flag == 4){

                                    mediaPlayeryeah.start();

                                    target = (LinearLayout) findViewById(R.id.imgTarget) ;
                                    target.setVisibility(View.GONE);
                                    congra= (GifImageView) findViewById(R.id.congra);
                                    congra.setVisibility(View.VISIBLE);
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {

                                            changeImage();
                                        }
                                    },1500);

                                    flag = 0;

                                }
                            }
                        },2000);

                    }
                    break;

            }
            return true;
        }
    };

    public void changeImage(){
        if(mediaPlayeryeah.isPlaying()){
            mediaPlayeryeah.pause();
        }
        thutu++;
        target = (LinearLayout) findViewById(R.id.imgTarget) ;
        target.setVisibility(View.VISIBLE);

        congra = (GifImageView) findViewById(R.id.congra);
        congra.setVisibility(View.GONE);

        if(thutu < test1.length) {
            GifImageView im = (GifImageView) findViewById(R.id.img1);
            try {
                GifDrawable gif = new GifDrawable(test4[thutu]);
                im.setImageDrawable(gif);
            }catch (Exception e){
                e.printStackTrace();
            }
            im = (GifImageView) findViewById(R.id.img2);
            try {
                GifDrawable gif = new GifDrawable(test2[thutu]);
                im.setImageDrawable(gif);
            }catch (Exception e){
                e.printStackTrace();
            }
            im = (GifImageView) findViewById(R.id.img3);
            try {
                GifDrawable gif = new GifDrawable(test3[thutu]);
                im.setImageDrawable(gif);
            }catch (Exception e){
                e.printStackTrace();
            }
            im = (GifImageView) findViewById(R.id.img4);
            try {
                GifDrawable gif = new GifDrawable(test1[thutu]);
                im.setImageDrawable(gif);
            }catch (Exception e){
                e.printStackTrace();
            }

            im = (GifImageView) findViewById(R.id.img11);
            im.setImageBitmap(source3[thutu]);
            im = (GifImageView) findViewById(R.id.img22);
            im.setImageBitmap(source1[thutu]);
            im = (GifImageView) findViewById(R.id.img33);
            im.setImageBitmap(source2[thutu]);
            im = (GifImageView) findViewById(R.id.img44);
            im.setImageBitmap(source[thutu]);

        }else {
            Intent intent = new Intent(this,VictoryActivity.class);
            startActivityForResult(intent,INPUT);
            finish();
//            Intent intent = this.getIntent();
//            this.setResult(RESULT_OK, intent);
//            finish();
        }
    }
    View.OnDragListener drag = new View.OnDragListener() {
        @Override
        public boolean onDrag(View view, DragEvent dragEvent) {
            int d = dragEvent.getAction();
            final View v = (View) dragEvent.getLocalState();
            switch (d){
                case DragEvent.ACTION_DRAG_ENTERED:
//                    ImageView btn11= (ImageView) dragEvent.getLocalState();
//                    btn11.setVisibility(View.INVISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
//                    ImageView btn12= (ImageView) dragEvent.getLocalState();
//                    btn12.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DROP:
                    anh1.setVisibility(View.VISIBLE);
                    anh2.setVisibility(View.VISIBLE);
                    anh3.setVisibility(View.VISIBLE);
                    anh4.setVisibility(View.VISIBLE);
                    break;

            }  return true;}
    };
    protected void onStop() {
        super.onStop();
        Intent intent = new Intent(Apply2Activity.this,MyMusicService.class);
        if(intent == null){
            startService(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == INPUT) {
            if (resultCode == RESULT_OK) {

                finish();

            }
        }
    }
}
