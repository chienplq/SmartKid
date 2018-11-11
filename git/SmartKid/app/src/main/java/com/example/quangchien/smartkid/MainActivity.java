package com.example.quangchien.smartkid;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import com.example.quangchien.smartkid.data.GetData;

import com.example.quangchien.smartkid.data.Image;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {



    Intent intent;

    Timer T=new Timer();
    int count =0;
    Boolean fl = true;

    List<Image> list;


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
                            }
                            if (dth.geLimitTime(getDate())<=dth.getSumTime(getDate())){
                                block();
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

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        if(isOnline()){
        GetData data = new GetData();
        list = data.getAllImage(); // câu lệnh này để e đưa cái list lên bỏ vô đây
        try {
            DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
            for (int i=0;i< list.size(); i++){
                dataBaseHelper.saveImage(list.get(i).getName(), list.get(i).getImg()); //cái này là tên vs byte[] lấy
                //từ từ thằng trong list của e nhé
            }
        }catch (Exception e){
            e.printStackTrace();
        }}

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
    public void clickToPractice(View view) {
        Intent intent = new Intent(this,PracticeActivity.class);
        startActivity(intent);
    }


    public boolean isOnline() {
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnectedOrConnecting()){
            return true;
        }
        else{
            return false;
        }

    }
    public void clickToSetting(View view) {
        Intent intent = new Intent(this, SetTimePlay.class);
        startActivity(intent);
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
}
