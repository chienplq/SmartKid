package com.example.quangchien.smartkid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    List<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


//phai viet truc tiep vo Main nen k tao class khac dc e nhe
        list = list; // câu lệnh này để e đưa cái list lên bỏ vô đây
        try {
            DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
            for (int i=0;i< list.size(); i++){
            dataBaseHelper.saveImage("sadsda", null); //cái này là tên vs byte[] lấy
                //từ từ thằng trong list của e nhé
            }
        }catch (Exception e){
            e.printStackTrace();
        }





        //em viết vô phần a chừa trống này nha
        setContentView(R.layout.activity_chosse_style);
        intent = new Intent(MainActivity.this,MyMusicService.class);
        startService(intent);

    }


    //    public void ClickToFinish(View view) {
//        finish();
//    }

    public void clickToThinking(View view) {
        Intent intent = new Intent(this, thinkingActivity.class);
        startActivity(intent);

    }

    public void clickToObserver(View view) {
        Intent intent = new Intent(this,ObserverActivity.class);
        startActivity(intent);

    }

    public void clickToPractice(View view) {
        Intent intent = new Intent(this,PracticeActivity.class);
        startActivity(intent);
    }
}
