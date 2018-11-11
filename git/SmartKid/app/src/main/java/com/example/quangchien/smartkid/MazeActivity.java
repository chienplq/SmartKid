package com.example.quangchien.smartkid;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.graphics.Bitmap;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MazeActivity extends AppCompatActivity {
    private static int INPUT = 1;
    GestureDetector gestureDetector;
    int khoangCach = 50, KC = 40;
    int vanToc = 0;
    int hangKhi = 3, cotKhi = 0, ngang = 35, doc = 45;
    int flagMazeOder = 1;
    int idMaze[][] = {
            {R.id.maze00, R.id.maze01, R.id.maze02, R.id.maze03, R.id.maze04, R.id.maze05, R.id.maze06, R.id.maze07},
            {R.id.maze10, R.id.maze11, R.id.maze12, R.id.maze13, R.id.maze14, R.id.maze15, R.id.maze16, R.id.maze17},
            {R.id.maze20, R.id.maze21, R.id.maze22, R.id.maze23, R.id.maze24, R.id.maze25, R.id.maze26, R.id.maze27},
            {R.id.maze30, R.id.maze31, R.id.maze32, R.id.maze33, R.id.maze34, R.id.maze35, R.id.maze36, R.id.maze37},
            {R.id.maze40, R.id.maze41, R.id.maze42, R.id.maze43, R.id.maze44, R.id.maze45, R.id.maze46, R.id.maze47},
            {R.id.maze50, R.id.maze51, R.id.maze52, R.id.maze53, R.id.maze54, R.id.maze55, R.id.maze56, R.id.maze57},
            {R.id.maze60, R.id.maze61, R.id.maze62, R.id.maze63, R.id.maze64, R.id.maze65, R.id.maze66, R.id.maze67},
            {R.id.maze70, R.id.maze71, R.id.maze72, R.id.maze73, R.id.maze74, R.id.maze75, R.id.maze76, R.id.maze77},

    };
    int maze[][] = {
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 0, 0, 0},
            {1, 1, 0, 0, 0, 0, 1, 1},
            {0, 0, 0, 1, 1, 1, 1, 1},
            {1, 1, 0, 1, 0, 0, 0, 1},
            {1, 1, 0, 1, 0, 1, 0, 1},
            {1, 1, 0, 0, 0, 1, 0, 1},
            {1, 1, 1, 1, 1, 1, 0, 1}};

    int maze1[][] = {
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 0, 0, 0},
            {1, 1, 0, 0, 0, 0, 1, 1},
            {0, 0, 0, 1, 1, 1, 1, 1},
            {1, 1, 0, 1, 0, 0, 0, 1},
            {1, 1, 0, 1, 0, 1, 0, 1},
            {1, 1, 0, 0, 0, 1, 0, 1},
            {1, 1, 1, 1, 1, 1, 0, 1}};
    int maze2[][] = {
            {1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 1, 0, 1},
            {1, 0, 1, 1, 0, 0, 0, 1},
            {1, 0, 0, 0, 1, 1, 1, 1},
            {1, 0, 1, 0, 0, 0, 0, 1},
            {1, 0, 0, 1, 1, 1, 0, 1},
            {1, 1, 1, 1, 1, 1, 0, 1}
    };
    int maze3[][] = {
            {1, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 1, 0, 1},
            {1, 0, 1, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 1, 0, 0},
            {1, 0, 1, 0, 0, 0, 1, 1},
            {1, 0, 1, 1, 1, 0, 1, 1},
            {1, 0, 0, 0, 1, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1}

    };
    int maze4[][] = {
            {1, 1, 1, 1, 1, 1, 0, 1},
            {1, 0, 1, 0, 1, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 1},
            {0, 0, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 1, 0, 1},
            {0, 0, 1, 0, 1, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1}

    };
    Timer T=new Timer();
    int count =0;
    Boolean fl = true;

    Bitmap[] source = {null, null, null, null, null, null, null, null};
    String flagChangeSource = "";

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
                            dth.saveTime(getDate());
                            if (dth.geLimitTime(getDate())<=dth.getSumTime(getDate())){
                                block();
                            }

                        }
                        }
                    });
                }
            };
            T.scheduleAtFixedRate(abc,10000, 10000);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            DataBaseHelper dt = new DataBaseHelper(this);
            Bitmap bitmap = BitmapFactory.decodeByteArray( dt.getImageById("conkhimaze"),0, dt.getImageById("conkhimaze").length);
            source[0] = bitmap;
            bitmap = BitmapFactory.decodeByteArray( dt.getImageById("anhnull"),0, dt.getImageById("anhnull").length);
            source[1] = bitmap;
            bitmap = BitmapFactory.decodeByteArray( dt.getImageById("gach"),0, dt.getImageById("gach").length);
            source[2] = bitmap;
            bitmap = BitmapFactory.decodeByteArray( dt.getImageById("maze_naichuoi"),0, dt.getImageById("maze_naichuoi").length);
            source[3] = bitmap;
            bitmap = BitmapFactory.decodeByteArray( dt.getImageById("maze_mtp"),0, dt.getImageById("maze_mtp").length);
            source[4] = bitmap;
            bitmap = BitmapFactory.decodeByteArray( dt.getImageById("maze_mtx"),0, dt.getImageById("maze_mtx").length);
            source[5] = bitmap;
            bitmap = BitmapFactory.decodeByteArray( dt.getImageById("maze_mtt"),0, dt.getImageById("maze_mtt").length);
            source[6] = bitmap;
            bitmap = BitmapFactory.decodeByteArray( dt.getImageById("maze_mtl"),0, dt.getImageById("maze_mtl").length);
            source[7] = bitmap;


        } catch (Exception e) {
            e.printStackTrace();
        }

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
//        View decorView = getWindow().getDecorView();
//        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_FULLSCREEN;
//        decorView.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.activity_maze);
        createMaze();
        ImageView img1 = findViewById(idMaze[hangKhi][cotKhi]);
        img1.setImageBitmap(source[0]);
        //img1.setImageResource(R.drawable.conkhimaze);

        gestureDetector = new GestureDetector(this, new MyGeture());
//        for (int i = 0; i < 8; i++) {
//            for (int j = 0; j < 8; j++) {
//                if (maze[i][j] == 0) {
//                    ImageView img = findViewById(idMaze[i][j]);
//                    img.setOnTouchListener(new View.OnTouchListener() {
//                        @Override
//                        public boolean onTouch(View v, MotionEvent event) {
//                            gestureDetector.onTouchEvent(event);
//                            return true;
//                        }
//                    });
//                }
//            }
//        }
        LinearLayout linear_all = findViewById(R.id.lnlo_all);
        linear_all.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return true;
            }
        });

    }

    class MyGeture extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            //trái sang phải

            if (e2.getX() - e1.getX() > khoangCach && Math.abs(velocityX) > vanToc) {
//                Toast.makeText(MazeActivity.this, "trai s phai " + ((e2.getX() - e1.getX())/doc) , Toast.LENGTH_SHORT).show();
                for (int i = 0; i < ((e2.getX() - e1.getX()) / doc - 1); i++) {
                    if (cotKhi < 7) {
                        if (maze[hangKhi][cotKhi + 1] == 0) {
                            ImageView img = findViewById(idMaze[hangKhi][cotKhi]);

                            img.setImageResource(R.drawable.anhnull);
                            cotKhi++;
                            ImageView img1 = findViewById(idMaze[hangKhi][cotKhi]);
                            img1.setImageBitmap(source[0]);
                            // img1.setImageResource(R.drawable.conkhimaze);
                        }
                    }
                }
                checkAndChangeMaze();
            }
            //Phải sang trái
            if (e1.getX() - e2.getX() > khoangCach && Math.abs(velocityX) > vanToc) {

                for (int i = 0; i < (e1.getX() - e2.getX() / doc - 1); i++) {
                    if (cotKhi > 0) {
                        if (maze[hangKhi][cotKhi - 1] == 0) {
//                            Toast.makeText(MazeActivity.this, "phai sang trai " + ((e2.getX() - e1.getX())/doc) , Toast.LENGTH_SHORT).show();
                            ImageView img = findViewById(idMaze[hangKhi][cotKhi]);
                            img.setImageBitmap(source[1]);
                            //img.setImageResource(R.drawable.anhnull);
                            cotKhi--;
                            ImageView img1 = findViewById(idMaze[hangKhi][cotKhi]);
                            img1.setImageBitmap(source[0]);
                            //img1.setImageResource(R.drawable.conkhimaze);
                        }
                    }
                }
                checkAndChangeMaze();
            }
            //trên xuống dưới
            if (e2.getY() - e1.getY() > KC && Math.abs(velocityY) > vanToc) {
                for (int i = 0; i < ((e2.getY() - e1.getY()) / ngang - 1); i++) {
                    if (hangKhi < 7) {
                        if (maze[hangKhi + 1][cotKhi] == 0) {
//                            Toast.makeText(MazeActivity.this, "tren xun duoi " + ((e2.getX() - e1.getX())/doc) , Toast.LENGTH_SHORT).show();
                            ImageView img = findViewById(idMaze[hangKhi][cotKhi]);
                            img.setImageBitmap(source[1]);
                            //img.setImageResource(R.drawable.anhnull);
                            hangKhi++;
                            ImageView img1 = findViewById(idMaze[hangKhi][cotKhi]);
                            img1.setImageBitmap(source[0]);
                            //img1.setImageResource(R.drawable.conkhimaze);

                        }
                    }
                }
                checkAndChangeMaze();
            }
            if (e1.getY() - e2.getY() > khoangCach && Math.abs(velocityY) > vanToc) {
                for (int i = 0; i < ((e1.getY() - e2.getY()) / ngang - 1); i++) {
                    if (hangKhi > 0) {
                        if (maze[hangKhi - 1][cotKhi] == 0) {
//                            Toast.makeText(MazeActivity.this, "duoi len tren " + ((e2.getX() - e1.getX())/doc) , Toast.LENGTH_SHORT).show();
                            ImageView img = findViewById(idMaze[hangKhi][cotKhi]);
                            img.setImageBitmap(source[1]);
                            //img.setImageResource(R.drawable.anhnull);
                            hangKhi--;
                            ImageView img1 = findViewById(idMaze[hangKhi][cotKhi]);
                            img1.setImageBitmap(source[0]);
                            //img1.setImageResource(R.drawable.conkhimaze);
                        }
                    }
                }
                checkAndChangeMaze();
            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }

    }

    public void checkAndChangeMaze() {
        if (hangKhi == 7 && cotKhi == 6 && flagMazeOder == 1) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    maze[i][j] = maze3[i][j];
                }
            }
            flagMazeOder = 3;
            ImageView img = findViewById(idMaze[hangKhi][cotKhi]);
            img.setImageBitmap(source[1]);
            //img.setImageResource(R.drawable.anhnull);
            hangKhi = 0;
            cotKhi = 6;
            createMaze();
            ImageView img1 = findViewById(idMaze[hangKhi][cotKhi]);
            img1.setImageBitmap(source[0]);
            //img1.setImageResource(R.drawable.conkhimaze);
            return;
        }
        if (hangKhi == 0 && cotKhi == 6 && flagMazeOder == 3) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    maze[i][j] = maze1[i][j];
                }
            }
            flagMazeOder = 1;
            ImageView img = findViewById(idMaze[hangKhi][cotKhi]);
            img.setImageBitmap(source[1]);
            //img.setImageResource(R.drawable.anhnull);
            hangKhi = 7;
            cotKhi = 6;
            createMaze();
            ImageView img1 = findViewById(idMaze[hangKhi][cotKhi]);
            img1.setImageBitmap(source[0]);
            // img1.setImageResource(R.drawable.conkhimaze);
            return;
        }
        if (hangKhi == 1 && cotKhi == 7 && flagMazeOder == 1) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    maze[i][j] = maze2[i][j];
                }
            }
            flagMazeOder = 2;
            ImageView img = findViewById(idMaze[hangKhi][cotKhi]);
            img.setImageBitmap(source[1]);
            //img.setImageResource(R.drawable.anhnull);
            hangKhi = 1;
            cotKhi = 0;
            createMaze();
            ImageView img1 = findViewById(idMaze[hangKhi][cotKhi]);
            img1.setImageBitmap(source[0]);
            //img1.setImageResource(R.drawable.conkhimaze);

            return;
        }
        if (hangKhi == 1 && cotKhi == 0 && flagMazeOder == 2) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    maze[i][j] = maze1[i][j];
                }
            }
            flagMazeOder = 1;
            ImageView img = findViewById(idMaze[hangKhi][cotKhi]);
            img.setImageBitmap(source[1]);//img.setImageResource(R.drawable.anhnull);
            hangKhi = 1;
            cotKhi = 7;
            createMaze();
            ImageView img1 = findViewById(idMaze[hangKhi][cotKhi]);
            img1.setImageBitmap(source[0]);
            //img1.setImageResource(R.drawable.conkhimaze);

            return;
        }
        if (hangKhi == 1 && cotKhi == 7 && flagMazeOder == 1) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    maze[i][j] = maze2[i][j];
                }
            }
            flagMazeOder = 2;
            ImageView img = findViewById(idMaze[hangKhi][cotKhi]);
            img.setImageBitmap(source[1]);//img.setImageResource(R.drawable.anhnull);
            hangKhi = 1;
            cotKhi = 0;
            createMaze();
            ImageView img1 = findViewById(idMaze[hangKhi][cotKhi]);
            img1.setImageBitmap(source[0]);
            //img1.setImageResource(R.drawable.conkhimaze);

            return;
        }
        if (hangKhi == 7 && cotKhi == 6 && flagMazeOder == 2) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    maze[i][j] = maze4[i][j];
                }
            }
            flagMazeOder = 4;
            ImageView img = findViewById(idMaze[hangKhi][cotKhi]);
            img.setImageBitmap(source[1]);//img.setImageResource(R.drawable.anhnull);
            hangKhi = 0;
            cotKhi = 6;
            createMaze();
            ImageView img1 = findViewById(idMaze[hangKhi][cotKhi]);
            img1.setImageBitmap(source[0]);
            //img1.setImageResource(R.drawable.conkhimaze);

            return;
        }
        if (hangKhi == 0 && cotKhi == 6 && flagMazeOder == 4) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    maze[i][j] = maze2[i][j];
                }
            }
            flagMazeOder = 2;
            ImageView img = findViewById(idMaze[hangKhi][cotKhi]);
            img.setImageBitmap(source[1]);//img.setImageResource(R.drawable.anhnull);
            hangKhi = 7;
            cotKhi = 6;
            createMaze();
            ImageView img1 = findViewById(idMaze[hangKhi][cotKhi]);
            img1.setImageBitmap(source[0]);
            //img1.setImageResource(R.drawable.conkhimaze);

            return;
        }
        if (hangKhi == 3 && cotKhi == 7 && flagMazeOder == 3) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    maze[i][j] = maze4[i][j];
                }
            }
            flagMazeOder = 4;
            ImageView img = findViewById(idMaze[hangKhi][cotKhi]);
            img.setImageBitmap(source[1]);//img.setImageResource(R.drawable.anhnull);
            hangKhi = 3;
            cotKhi = 0;
            createMaze();
            ImageView img1 = findViewById(idMaze[hangKhi][cotKhi]);
            img1.setImageBitmap(source[0]);
            //img1.setImageResource(R.drawable.conkhimaze);
            return;
        }
        if (hangKhi == 3 && cotKhi == 0 && flagMazeOder == 4) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    maze[i][j] = maze3[i][j];
                }
            }
            flagMazeOder = 3;
            ImageView img = findViewById(idMaze[hangKhi][cotKhi]);
            img.setImageBitmap(source[1]);//img.setImageResource(R.drawable.anhnull);
            hangKhi = 3;
            cotKhi = 7;
            createMaze();
            ImageView img1 = findViewById(idMaze[hangKhi][cotKhi]);
            img1.setImageBitmap(source[0]);
            //img1.setImageResource(R.drawable.conkhimaze);
            return;
        }
        if (hangKhi == 6 && cotKhi == 7 && flagMazeOder == 3) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    maze[i][j] = maze4[i][j];
                }
            }
            flagMazeOder = 4;
            ImageView img = findViewById(idMaze[hangKhi][cotKhi]);
            img.setImageBitmap(source[1]);//img.setImageResource(R.drawable.anhnull);
            hangKhi = 6;
            cotKhi = 0;
            createMaze();
            ImageView img1 = findViewById(idMaze[hangKhi][cotKhi]);
            img1.setImageBitmap(source[0]);
            //img1.setImageResource(R.drawable.conkhimaze);

            return;
        }
        if (hangKhi == 6 && cotKhi == 0 && flagMazeOder == 4) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    maze[i][j] = maze3[i][j];
                }
            }
            flagMazeOder = 3;
            ImageView img = findViewById(idMaze[hangKhi][cotKhi]);
            img.setImageBitmap(source[1]);//img.setImageResource(R.drawable.anhnull);
            hangKhi = 6;
            cotKhi = 7;
            createMaze();
            ImageView img1 = findViewById(idMaze[hangKhi][cotKhi]);
            img1.setImageBitmap(source[0]);
            //img1.setImageResource(R.drawable.conkhimaze);
            return;
        }
        if (hangKhi == 6 && cotKhi == 7 && flagMazeOder == 4) {
            victory();
        }


    }

    public void victory() {
        Intent intent = new Intent(this, maze_vitoryActivity.class);
        startActivityForResult(intent, INPUT);

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

    public void createMaze() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (maze[i][j] == 1) {
                    ImageView img = findViewById(idMaze[i][j]);
                    img.setImageBitmap(source[2]);//                    img.setImageResource(R.drawable.gach);
                } else {
                    ImageView img = findViewById(idMaze[i][j]);
                    img.setImageBitmap(source[1]);//img.setImageResource(R.drawable.anhnull);
                }
            }
        }
        if (flagMazeOder == 1) {
            ImageView img;
            img = findViewById(R.id.maze17);
            img.setImageBitmap(source[4]);//img.setImageResource(R.drawable.maze_mtp);
            img = findViewById(R.id.maze76);
            img.setImageBitmap(source[5]);//img.setImageResource(R.drawable.maze_mtx);
        } else if (flagMazeOder == 2) {
            ImageView img;
            img = findViewById(R.id.maze10);
            img.setImageBitmap(source[6]);//img.setImageResource(R.drawable.maze_mtt);
            img = findViewById(R.id.maze76);
            img.setImageBitmap(source[5]);//img.setImageResource(R.drawable.maze_mtx);
        }
        if (flagMazeOder == 3) {
            ImageView img;
            img = findViewById(R.id.maze06);
            img.setImageBitmap(source[7]);//img.setImageResource(R.drawable.maze_mtl);
            img = findViewById(R.id.maze37);
            img.setImageBitmap(source[4]);//img.setImageResource(R.drawable.maze_mtp);
            img = findViewById(R.id.maze67);
            img.setImageBitmap(source[4]);//img.setImageResource(R.drawable.maze_mtp);
        }
        if (flagMazeOder == 4) {
            ImageView img;
            img = findViewById(R.id.maze06);
            img.setImageBitmap(source[7]);//img.setImageResource(R.drawable.maze_mtl);
            img = findViewById(R.id.maze30);
            img.setImageBitmap(source[6]);//img.setImageResource(R.drawable.maze_mtt);
            img = findViewById(R.id.maze60);
            img.setImageBitmap(source[6]);//img.setImageResource(R.drawable.maze_mtt);
            img = findViewById(R.id.maze67);
            img.setImageBitmap(source[3]);//img.setImageResource(R.drawable.maze_naichuoi);
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
