package com.example.quangchien.smartkid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class PracticeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_practice);
    }
    public void clickToDifferent(View view) {
        Intent intent = new Intent(this,DifferentActivity.class);
        startActivity(intent);
    }

    public void clickToSimilar(View view) {
        Intent intent = new Intent(this,PickSamePictureActivity.class);
        startActivity(intent);
    }

    protected void onStop() {
        super.onStop();
        Intent intent = new Intent(PracticeActivity.this,MyMusicService.class);
        if(intent != null){
            stopService(intent);
        }
    }

    public void ClickToHome(View view) {
        finish();
    }
}
