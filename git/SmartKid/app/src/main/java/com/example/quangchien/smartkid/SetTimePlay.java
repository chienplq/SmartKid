package com.example.quangchien.smartkid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Date;
import java.util.Random;

public class SetTimePlay extends AppCompatActivity {

    int a = 0, b=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_time_play);
        sai();
        try {
            DataBaseHelper dth = new DataBaseHelper(this);
            TextView played = findViewById(R.id.played);
            played.setText("Hôm nay đã chơi: " + dth.getSumTime(getDate()) +" phút");


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void clickToConfirm(View view) {
        EditText edt = findViewById(R.id.edtAnswer);
        String z = edt.getText().toString();
        int sum = a+b;
        if (z.equals(sum+""))
        {
            try {
                DataBaseHelper dth = new DataBaseHelper(this);
                EditText edt1= findViewById(R.id.tgchoi);
                int num = Integer.parseInt(edt1.getText().toString()) ;
                dth.setLimitTime(getDate(),num);

            } catch (Exception e) {
                e.printStackTrace();
            }
                finish();
        } else {
            sai();
        }

    }
    public void sai(){
        TextView tv = findViewById(R.id.txtMath);
        tv.setText(randomMath());
    }
    public String randomMath(){
        Random rd = new Random();
        a = 10+ rd.nextInt(10);
        b = 11+ rd.nextInt(11);


            return (a+" + "+ b +" = ");

    }
    public String getDate(){
        Date today = new Date();
        return (today.getDate()+"/" +today.getMonth()+"/"+today.getYear());
    }
}
