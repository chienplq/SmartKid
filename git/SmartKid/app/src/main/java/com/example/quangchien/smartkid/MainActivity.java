package com.example.quangchien.smartkid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);



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
