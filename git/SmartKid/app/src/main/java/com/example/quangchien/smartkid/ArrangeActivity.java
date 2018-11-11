package com.example.quangchien.smartkid;

import android.content.ClipData;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import pl.droidsonroids.gif.GifImageView;

public class ArrangeActivity extends AppCompatActivity {
    Timer T=new Timer();
    int count =0;
    Boolean fl = true;
    ImageView gau1, gau2, gau3, gau4, gaua, gaub, gauc, gaud, st1, st2, st3, st4, sta, stb, stc, std;
    GifImageView chochay, mew;
    int order;
    int flag1,flag2;
    int flag = 0;
    Handler handler = new Handler();
    private Integer image1[] = {R.drawable.a1, R.drawable.truc1};
    private Integer image2[] = {R.drawable.a2, R.drawable.truc2};
    private Integer image3[] = {R.drawable.a3, R.drawable.truc3};
    private Integer image4[] = {R.drawable.a4, R.drawable.truc4};

    private Integer image5[] = {R.drawable.mew1, R.drawable.cho1};
    private Integer image6[] = {R.drawable.mew2, R.drawable.cho2};
    private Integer image7[] = {R.drawable.mew3, R.drawable.cho3};
    private Integer image8[] = {R.drawable.mew4, R.drawable.cho4};

    private Integer imageMo1[] = {R.drawable.amo1, R.drawable.trucmo1};
    private Integer imageMo2[] = {R.drawable.amo2, R.drawable.trucmo4};
    private Integer imageMo3[] = {R.drawable.amo3, R.drawable.trucmo2};
    private Integer imageMo4[] = {R.drawable.amo4, R.drawable.trucmo3};

    private Integer imageMo5[] = {R.drawable.mewmo1, R.drawable.chomo2};
    private Integer imageMo6[] = {R.drawable.mewmo2, R.drawable.chomo3};
    private Integer imageMo7[] = {R.drawable.mewmo3, R.drawable.chomo4};
    private Integer imageMo8[] = {R.drawable.mewmo4, R.drawable.chomo1};


    private Integer imageTo1[] = {R.drawable.chochay, R.drawable.gau};
    private Integer imageTo2[] = {R.drawable.mew, R.drawable.chosua};


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

                            if (count == 60) {
                                dth.saveTime(getDate());
                                count = 0;
                                if (dth.geLimitTime(getDate())<=dth.getSumTime(getDate())){
                                    block();
                                }
                            }
                            count++;
                        }
                        }
                    });
                }
            };
            T.scheduleAtFixedRate(abc,1000, 1000);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_arrange);

        LinearLayout abc= findViewById(R.id.lu);
        gau1 = (ImageView) findViewById(R.id.img1);
        gaua = (ImageView) findViewById(R.id.img11);
        gau2 = (ImageView) findViewById(R.id.img2);
        gaub = (ImageView) findViewById(R.id.img22);
        gau3 = (ImageView) findViewById(R.id.img3);
        gauc = (ImageView) findViewById(R.id.img55);
        gau4 = (ImageView) findViewById(R.id.img4);
        gaud = (ImageView) findViewById(R.id.img66);

        chochay = (GifImageView) findViewById(R.id.imgCho);
        mew = (GifImageView) findViewById(R.id.imgMew);

        st1 = (ImageView) findViewById(R.id.sutu1);
        sta = (ImageView) findViewById(R.id.img33);
        st2 = (ImageView) findViewById(R.id.sutu2);
        stb = (ImageView) findViewById(R.id.img44);
        st3 = (ImageView) findViewById(R.id.sutu3);
        stc = (ImageView) findViewById(R.id.img77);
        st4 = (ImageView) findViewById(R.id.sutu4);
        std = (ImageView) findViewById(R.id.img88);

        gaua.setOnDragListener(onDragListener);
        gaub.setOnDragListener(onDragListener);
        gauc.setOnDragListener(onDragListener);
        gaud.setOnDragListener(onDragListener);
        sta.setOnDragListener(onDragListener);
        stb.setOnDragListener(onDragListener);
        stc.setOnDragListener(onDragListener);
        std.setOnDragListener(onDragListener);
        abc.setOnDragListener(onDragListener);

        gau1.setOnTouchListener(onTouchListener);
        gau2.setOnTouchListener(onTouchListener);
        gau3.setOnTouchListener(onTouchListener);
        gau4.setOnTouchListener(onTouchListener);
        st1.setOnTouchListener(onTouchListener);
        st2.setOnTouchListener(onTouchListener);
        st3.setOnTouchListener(onTouchListener);
        st4.setOnTouchListener(onTouchListener);

    }

    View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            ClipData clipData = ClipData.newPlainText("", "");
            View.DragShadowBuilder builder = new View.DragShadowBuilder(view);
//            view.setVisibility(View.INVISIBLE);
            view.startDrag(clipData, builder, view, 0);
            return true;
        }
    };

    View.OnDragListener onDragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, 0, 0, 0);
            int dragEvent = event.getAction();
            final View view = (View) event.getLocalState();

            switch (dragEvent) {
                case DragEvent.ACTION_DRAG_ENTERED:
                    ImageView btn= (ImageView) event.getLocalState();
                    btn.setVisibility(View.INVISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    ImageView btn11= (ImageView) event.getLocalState();
                    btn11.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DROP:

//                    ImageView btn= (ImageView) event.getLocalState();
//                    if(v instanceof LinearLayout){
//                        btn.setVisibility(View.VISIBLE);
//                    }
                    ImageView btn12= (ImageView) event.getLocalState();
                    btn12.setVisibility(View.VISIBLE);
                    v.setVisibility(View.VISIBLE);
                    if (view.getId() == R.id.img1 && v.getId() == R.id.img11) {
                        flag1++;
                        final ImageView gif = findViewById(R.id.img11);
                        gif.setImageResource(image1[order]);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                flag++;
                                if(flag == 10){
                                    changeImage();
                                    flag = 0;

                                }
                            }
                        },5000);
                        gaua.setLayoutParams(lp);
                        gau1.setVisibility(View.INVISIBLE);
                    } else if (view.getId() == R.id.img2 && v.getId() == R.id.img55) {
                        flag1++;
                        final ImageView img = findViewById(R.id.img55);
                        img.setImageResource(image2[order]);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                flag++;
                                if(flag == 10){
                                    changeImage();
                                    flag = 0;

                                }
                            }
                        },5000);
                        gaub.setLayoutParams(lp);
                        gau2.setVisibility(View.INVISIBLE);
                    } else if (view.getId() == R.id.img3 && v.getId() == R.id.img66) {
                        flag1++;
                        final ImageView img = findViewById(R.id.img66);
                        img.setImageResource(image3[order]);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                flag++;
                                if(flag == 10){
                                    changeImage();
                                    flag = 0;

                                }
                            }
                        },5000);
                        gauc.setLayoutParams(lp);
                        gau3.setVisibility(View.INVISIBLE);
                    } else if (view.getId() == R.id.img4 && v.getId() == R.id.img22) {
                        flag1++;
                        final ImageView img = findViewById(R.id.img22);
                        img.setImageResource(image4[order]);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                flag++;
                                if(flag == 10){
                                    changeImage();
                                    flag = 0;

                                }
                            }
                        },5000);
                        gaud.setLayoutParams(lp);
                        gau4.setVisibility(View.INVISIBLE);
                    }
                    else if (view.getId() == R.id.sutu1 && v.getId() == R.id.img88) {
                        flag2++;
                        final ImageView img = findViewById(R.id.img88);
                        img.setImageResource(image5[order]);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                flag++;
                                if(flag == 10){
                                    changeImage();
                                    flag = 0;

                                }
                            }
                        },5000);
                        sta.setLayoutParams(lp);
                        st1.setVisibility(View.INVISIBLE);
                    } else if (view.getId() == R.id.sutu2 && v.getId() == R.id.img33) {
                        flag2++;
                        final ImageView img = findViewById(R.id.img33);
                        img.setImageResource(image6[order]);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                flag++;
                                if(flag == 10){
                                    changeImage();
                                    flag = 0;

                                }
                            }
                        },5000);
                        stb.setLayoutParams(lp);
                        st2.setVisibility(View.INVISIBLE);
                    } else if (view.getId() == R.id.sutu3 && v.getId() == R.id.img44) {
                        flag2++;
                        final ImageView img = findViewById(R.id.img44);
                        img.setImageResource(image7[order]);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                flag++;
                                if(flag == 10){
                                    changeImage();
                                    flag = 0;

                                }
                            }
                        },5000);
                        stc.setLayoutParams(lp);
                        st3.setVisibility(View.INVISIBLE);
                    } else if (view.getId() == R.id.sutu4 && v.getId() == R.id.img77) {
                        flag2++;
                        final ImageView img = findViewById(R.id.img77);
                        img.setImageResource(image8[order]);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                flag++;
                                if(flag == 10){
                                    changeImage();
                                    flag = 0;

                                }
                            }
                        },5000);
                        std.setLayoutParams(lp);
                        st4.setVisibility(View.INVISIBLE);
                    }
                    if (flag1 == 4) {
                        gaua.setVisibility(View.INVISIBLE);
                        gaub.setVisibility(View.INVISIBLE);
                        gauc.setVisibility(View.INVISIBLE);
                        gaud.setVisibility(View.INVISIBLE);
                        chochay.setVisibility(View.VISIBLE);
                        flag1 ++;
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                flag++;
                                if(flag == 10){
                                    changeImage();
                                    flag = 0;

                                }
                            }
                        },5000);
                    }
                    if (flag2 == 4) {
                        sta.setVisibility(View.INVISIBLE);
                        stb.setVisibility(View.INVISIBLE);
                        stc.setVisibility(View.INVISIBLE);
                        std.setVisibility(View.INVISIBLE);
                        mew.setVisibility(View.VISIBLE);
                        flag2 ++;
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                flag++;
                                if(flag == 10){
                                    changeImage();
                                    flag = 0;

                                }
                            }
                        },5000);
                    }
                    break;
            }
            return true;

        }


//void batHinh(){
//            for (int i=0;i<8;i++){
//                if (fl[i]==false) {
//                    ImageView img = findViewById(anh[i]);
//                    img.setVisibility(View.VISIBLE);
//                }
//            }
//        }
    };


    public void changeImage(){
        order++;
        if(order < image1.length) {

            gau1.setVisibility(View.VISIBLE);
            gau1.setImageResource(image1[order]);
            gau2.setVisibility(View.VISIBLE);
            gau2.setImageResource(image2[order]);
            gau3.setVisibility(View.VISIBLE);
            gau3.setImageResource(image3[order]);
            gau4.setVisibility(View.VISIBLE);
            gau4.setImageResource(image4[order]);
            st1.setVisibility(View.VISIBLE);
            st1.setImageResource(image5[order]);
            st2.setVisibility(View.VISIBLE);
            st2.setImageResource(image6[order]);
            st3.setVisibility(View.VISIBLE);
            st3.setImageResource(image7[order]);
            st4.setVisibility(View.VISIBLE);
            st4.setImageResource(image8[order]);

            chochay.setImageResource(imageTo1[order]);
            chochay.setVisibility(View.GONE);
            mew.setImageResource(imageTo2[order]);
            mew.setVisibility(View.GONE);


            gaua.setVisibility(View.VISIBLE);
            gaua.setImageResource(imageMo1[order]);
            gaub.setVisibility(View.VISIBLE);
            gaub.setImageResource(imageMo2[order]);
            gauc.setVisibility(View.VISIBLE);
            gauc.setImageResource(imageMo3[order]);
            gaud.setVisibility(View.VISIBLE);
            gaud.setImageResource(imageMo4[order]);

            sta.setVisibility(View.VISIBLE);
            sta.setImageResource(imageMo5[order]);
            stb.setVisibility(View.VISIBLE);
            stb.setImageResource(imageMo6[order]);
            stc.setVisibility(View.VISIBLE);
            stc.setImageResource(imageMo7[order]);
            std.setVisibility(View.VISIBLE);
            std.setImageResource(imageMo8[order]);
            flag1=0;
            flag2=0;
        }else {
            finish();
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
