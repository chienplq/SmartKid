package com.example.quangchien.smartkid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.List;

public class Chosse extends AppCompatActivity {

    int[] backGround = {R.drawable.animalbg, R.drawable.familybg, R.drawable.schoolbg};
    private int flag = 0;
    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_main);
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
    }

    public void ClickToMain(View view) {
        switch (flag) {
            case 0:
                Intent intent = new Intent(this, Chosse.class);
                startActivity(intent);
                break;
            default:
                break;
        }


    }


    public void sangPhai(View view) {
        if (flag < (backGround.length - 1)) {
            flag++;
            ImageView img = findViewById(R.id.imStyle);
            img.setImageResource(backGround[flag]);
        }
    }

    public void sangTrai(View view) {
        if (flag > 0) {
            flag--;
            ImageView img = findViewById(R.id.imStyle);
            img.setImageResource(backGround[flag]);
        }
    }

    public void clickToSetting(View view) {
        Intent intent = new Intent(this, SetTimePlay.class);
        startActivity(intent);
    }
}
