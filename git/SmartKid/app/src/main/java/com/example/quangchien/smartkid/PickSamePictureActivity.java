package com.example.quangchien.smartkid;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

import pl.droidsonroids.gif.GifImageView;

public class PickSamePictureActivity extends AppCompatActivity {
    Bitmap anhBia = null;
    Bitmap anhNen = null;
    Bitmap[] source = {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null};
    int images[] = {R.drawable.trucxinh_a1, R.drawable.trucxinh_a3, R.drawable.trucxinh_a5, R.drawable.trucxinh_a7, R.drawable.trucxinh_a9, R.drawable.trucxinh_a11, R.drawable.trucxinh_a13, R.drawable.trucxinh_a15};
    int Vitri[] = {R.id.a1, R.id.a2, R.id.a3, R.id.a4, R.id.a5, R.id.a6, R.id.a7, R.id.a8, R.id.a9, R.id.a10, R.id.a11, R.id.a12, R.id.a13, R.id.a14, R.id.a15, R.id.a16};
    int soLuong[] = {2, 2, 2, 2, 2, 2, 2, 2};
    int ketQua[] = {17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17};
    int flag = 17, flag2 = 0;
    int pre = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            DataBaseHelper dt = new DataBaseHelper(this);
            Bitmap bitmap = BitmapFactory.decodeByteArray(dt.getImageById("trucxinh_a0"), 0, dt.getImageById("trucxinh_a0").length);
            anhBia = bitmap;
            bitmap = BitmapFactory.decodeByteArray(dt.getImageById("trucxinh_nen"), 0, dt.getImageById("trucxinh_nen").length);
            anhBia = bitmap;
            bitmap = BitmapFactory.decodeByteArray(dt.getImageById("trucxinh_a1"), 0, dt.getImageById("trucxinh_a1").length);
            source[0] = bitmap;
            bitmap = BitmapFactory.decodeByteArray(dt.getImageById("trucxinh_a3"), 0, dt.getImageById("trucxinh_a3").length);
            source[1] = bitmap;
            bitmap = BitmapFactory.decodeByteArray(dt.getImageById("trucxinh_a5"), 0, dt.getImageById("trucxinh_a5").length);
            source[2] = bitmap;
            bitmap = BitmapFactory.decodeByteArray(dt.getImageById("trucxinh_a7"), 0, dt.getImageById("trucxinh_a7").length);
            source[3] = bitmap;
            bitmap = BitmapFactory.decodeByteArray(dt.getImageById("trucxinh_a9"), 0, dt.getImageById("trucxinh_a9").length);
            source[4] = bitmap;
            bitmap = BitmapFactory.decodeByteArray(dt.getImageById("trucxinh_a11"), 0, dt.getImageById("trucxinh_a11").length);
            source[5] = bitmap;
            bitmap = BitmapFactory.decodeByteArray(dt.getImageById("trucxinh_a13"), 0, dt.getImageById("trucxinh_a13").length);
            source[6] = bitmap;
            bitmap = BitmapFactory.decodeByteArray(dt.getImageById("trucxinh_a15"), 0, dt.getImageById("trucxinh_a15").length);
            source[7] = bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_pick_same_picture);
        ImageView im = (ImageView) findViewById(R.id.a1);
        im.setImageBitmap(anhBia);
        im = (ImageView) findViewById(R.id.a2);
        im.setImageBitmap(anhBia);
        im = (ImageView) findViewById(R.id.a3);
        im.setImageBitmap(anhBia);
        im = (ImageView) findViewById(R.id.a4);
        im.setImageBitmap(anhBia);
        im = (ImageView) findViewById(R.id.a5);
        im.setImageBitmap(anhBia);
        im = (ImageView) findViewById(R.id.a6);
        im.setImageBitmap(anhBia);
        im = (ImageView) findViewById(R.id.a7);
        im.setImageBitmap(anhBia);
        im = (ImageView) findViewById(R.id.a8);
        im.setImageBitmap(anhBia);
        im = (ImageView) findViewById(R.id.a9);
        im.setImageBitmap(anhBia);
        im = (ImageView) findViewById(R.id.a10);
        im.setImageBitmap(anhBia);
        im = (ImageView) findViewById(R.id.a11);
        im.setImageBitmap(anhBia);
        im = (ImageView) findViewById(R.id.a12);
        im.setImageBitmap(anhBia);
        im = (ImageView) findViewById(R.id.a13);
        im.setImageBitmap(anhBia);
        im = (ImageView) findViewById(R.id.a14);
        im.setImageBitmap(anhBia);
        im = (ImageView) findViewById(R.id.a15);
        im.setImageBitmap(anhBia);
        im = (ImageView) findViewById(R.id.a16);
        im.setImageBitmap(anhBia);

        taoGame();
    }

    public void taoGame() {
        ImageView img = findViewById(R.id.a1);
        for (int i = 0; i < Vitri.length; i++) {
            img = findViewById(Vitri[i]);
            while (true) {
                Random random = new Random();
                int a = random.nextInt(8);
                if (soLuong[a] > 0) {
                    ketQua[i] = a;
                    soLuong[a]--;
                    break;
                }
            }
        }

    }

    public void btnClick(View view) {

        if (flag2 == 0) {

            Handler handler = new Handler();
            final ImageView img2 = findViewById(Vitri[Integer.parseInt(view.getTag().toString())]);

            if (flag == 17) {
                pre = Vitri[Integer.parseInt(view.getTag().toString())];
                img2.setImageBitmap(source[ketQua[Integer.parseInt(view.getTag().toString())]]);
                flag = ketQua[Integer.parseInt(view.getTag().toString())]; // flag se la 0,1,2, hoac 3
            } else if (pre != Vitri[Integer.parseInt(view.getTag().toString())]) {
                img2.setImageBitmap(source[ketQua[Integer.parseInt(view.getTag().toString())]]);
//            img2.setImageResource (pre);
                if (flag == ketQua[Integer.parseInt(view.getTag().toString())]) { // neu 2 anh giong nhau
                    flag2 = 1;
                    final ImageView img4 = findViewById(Vitri[Integer.parseInt(view.getTag().toString())]);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ImageView img3 = findViewById(pre);
                            img3.setVisibility(View.INVISIBLE);
                            img4.setVisibility(View.INVISIBLE);
                            flag2 = 0;
//                        img3.setImageResource (R.drawable.anhnull);
//                        img4.setImageResource (R.drawable.anhnull);
                            flag = 17;
                        }
                    }, 1000);


                } else {
                    flag2 = 1;
                    final ImageView img4 = findViewById(Vitri[Integer.parseInt(view.getTag().toString())]);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ImageView img3 = findViewById(pre);
                            img3.setImageBitmap(anhBia);
                            img4.setImageBitmap(anhBia);
                            flag2++;
                            //truong hop sai
                            flag2 = 0;
                            flag = 17;
                        }

                    }, 1000);


// Vi tri la ID,
                }

            }

        }

    }
}
