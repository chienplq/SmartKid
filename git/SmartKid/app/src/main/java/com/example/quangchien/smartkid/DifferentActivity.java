package com.example.quangchien.smartkid;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pl.droidsonroids.gif.GifImageView;

public class DifferentActivity extends AppCompatActivity {
    GridView gvImg;
    private static int INPUT = 1;
    int thutu = 0;
    Bitmap[] source1 = {null, null, null, null, null, null};
    Bitmap[] source2 = {null, null, null, null, null, null};
    Bitmap[] source = {null, null, null, null, null, null,
            null, null, null, null, null, null,
            null, null, null, null, null, null,
            null, null, null, null, null, null,
            null, null, null, null, null, null,
            null, null, null, null, null, null};
//    int [] img1 = {
//            R.drawable.sutu,R.drawable.ap_gau, R.drawable.khi, R.drawable.diff_voi, R.drawable.apply_chimcanhcut,
//            R.drawable.apply_meo, R.drawable.apply_cho,R.drawable.diff_khicon,R.drawable.diff_vet,R.drawable.diff_thocon,
//            R.drawable.diff_khicon,R.drawable.diff_heo,R.drawable.apply_huucaoco,R.drawable.apply_nai,R.drawable.apply_ngua,
//            R.drawable.diff_chocon
//    };
//    int [] img2 = {
//            R.drawable.khi,R.drawable.gautruc, R.drawable.chuottui,R.drawable.diff_voi1,R.drawable.diff_chimcanhcut
//            ,R.drawable.diff_meo1, R.drawable.dff_cho, R.drawable.diff_khicon1,R.drawable.diff_vet1,R.drawable.diff_thocon1,
//           R.drawable.diff_khicon1,R.drawable.diff_heo1,R.drawable.diff_huu1,R.drawable.diff_nai1, R.drawable.dff_ngua,
//            R.drawable.diff_chocon1
//    };
//    int[] img = {1,1,1,1,1,1,1,
//            1,1,1,1,1,1,1,
//            1,1,1,1,1,1,1,
//            1,1,1,1,1,1,1,
//            1,1,1,1,1,1,1,1
//
//    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_different);

        try {

            DataBaseHelper dt = new DataBaseHelper(this);
            Bitmap bitmap = BitmapFactory.decodeByteArray( dt.getImageById("diff_voi"),0, dt.getImageById("diff_voi").length);
            source1[0] = bitmap;
            bitmap = BitmapFactory.decodeByteArray( dt.getImageById("diff_khicon"),0, dt.getImageById("diff_khicon").length);
            source1[1] = bitmap;
            bitmap = BitmapFactory.decodeByteArray( dt.getImageById("diff_vet"),0, dt.getImageById("diff_vet").length);
            source1[2] = bitmap;
            bitmap = BitmapFactory.decodeByteArray( dt.getImageById("diff_thocon"),0, dt.getImageById("diff_thocon").length);
            source1[3] = bitmap;
            bitmap = BitmapFactory.decodeByteArray( dt.getImageById("diff_heo"),0, dt.getImageById("diff_heo").length);
            source1[4] = bitmap;
            bitmap = BitmapFactory.decodeByteArray( dt.getImageById("diff_chocon"),0, dt.getImageById("diff_chocon").length);
            source1[5] = bitmap;


            bitmap = BitmapFactory.decodeByteArray( dt.getImageById("diff_voi1"),0, dt.getImageById("diff_voi1").length);
            source2[0] = bitmap;
            bitmap = BitmapFactory.decodeByteArray( dt.getImageById("diff_khicon1"),0, dt.getImageById("diff_khicon1").length);
            source2[1] = bitmap;
            bitmap = BitmapFactory.decodeByteArray( dt.getImageById("diff_vet1"),0, dt.getImageById("diff_vet1").length);
            source2[2] = bitmap;
            bitmap = BitmapFactory.decodeByteArray( dt.getImageById("diff_thocon1"),0, dt.getImageById("diff_thocon1").length);
            source2[3] = bitmap;
            bitmap = BitmapFactory.decodeByteArray( dt.getImageById("diff_heo1"),0, dt.getImageById("diff_heo1").length);
            source2[4] = bitmap;
            bitmap = BitmapFactory.decodeByteArray( dt.getImageById("diff_chocon1"),0, dt.getImageById("diff_chocon1").length);
            source2[5] = bitmap;



        } catch (Exception e) {
            e.printStackTrace();
        }
        changeImage();

        gvImg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                try {
                    if(source[i] == source2[thutu]){
                        thutu++;
                        changeImage();

                    }
                }catch (Exception e){
                    finish();
                }

            }
        });


    }

    public void changeImage(){

        if(thutu <= source.length){
            for(int i = 0; i< source.length;i++){
                source[i] = source1[thutu];
            }
            Random r = new Random();
            int n = r.nextInt(source.length);

            source[n] = source2[thutu];

            gvImg = findViewById(R.id.gvImg);
            final GridViewAdapter gridViewAdapter = new GridViewAdapter(this, source);
            gvImg.setAdapter(gridViewAdapter);
        }else{
            Intent intent = new Intent(this,VictoryActivity.class);
            startActivityForResult(intent,INPUT);
            finish();
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

    public void clickToHome(View view) {
        finish();
    }
}
