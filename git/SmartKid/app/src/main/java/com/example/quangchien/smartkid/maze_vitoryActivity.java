package com.example.quangchien.smartkid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class maze_vitoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maze_vitory);
    }

    public void ClickToOK(View view) {
        Intent intent = this.getIntent();
        this.setResult(RESULT_OK, intent);
        finish();
    }
}
