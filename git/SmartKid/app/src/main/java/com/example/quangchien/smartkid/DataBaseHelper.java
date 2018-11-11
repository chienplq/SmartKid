package com.example.quangchien.smartkid;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;

import java.io.*;


public class DataBaseHelper extends SQLiteOpenHelper {
    private Context mycontext;

    private String DB_PATH =  "data/data/com.example.quangchien.testsqlite/databases";
    private  String DB_NAME = "SmartKidDB.db";//thension may be .sqlite or .db
    public SQLiteDatabase myDataBase;
    //  private String DB_PATH = null;

    public DataBaseHelper(Context context) throws IOException {

        super(context,"SmartKidDB.db",null,1);
        this.mycontext=context;
        DB_PATH = "/data/data/"
                + mycontext.getApplicationContext().getPackageName()
                + "/databases/";
        boolean dbexist = checkdatabase();
        if (dbexist) {
            //System.out.println("Databasets");
            opendatabase();
        } else {
            System.out.println("Databasen't exist");
            createdatabase();
        }
    }

    public void createdatabase() throws IOException {
        boolean dbexist = checkdatabase();
        if(dbexist) {
            System.out.println(" Database exists.");
        } else {
            this.getReadableDatabase();
            try {
                copydatabase();
            } catch(IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkdatabase() {
        //SQLiteDatabase checkdb = null;
        boolean checkdb = false;
        try {
            String myPath = DB_PATH + DB_NAME;
            File dbfile = new File(myPath);
            //checkdb = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READWRITE);
            checkdb = dbfile.exists();
        } catch(SQLiteException e) {
            System.out.println("Databasen't exist");
        }
        return checkdb;
    }

    private void copydatabase() throws IOException {
        //Open your local db as the input stream
        InputStream myinput = mycontext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outfilename = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myoutput = new FileOutputStream(outfilename);

        // transfer byte to inputfile to outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myinput.read(buffer))>0){
            myoutput.write(buffer,0,length);
        }

        // Close the streams
        myoutput.flush();
        myoutput.close();
        myinput.close();
    }

    public void opendatabase() throws SQLException {
        //Open the database
        String mypath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(mypath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public synchronized void close() {
        if(myDataBase != null) {
            myDataBase.close();
        }
        super.close();
    }
    public byte[] getImageById(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("MazeActivity", new String[] {"Name","Image"}, "Name = ?", new String[] { id }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        byte[] result = cursor.getBlob(1);
        return result;
    }
    public boolean checkValidImage(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("MazeActivity", new String[] {"Name","Image"}, "Name = ?", new String[] { id }, null, null, null, null);
        return (cursor != null);
    }
    public void insertImage(String name, byte[] image){
        SQLiteDatabase db=getWritableDatabase();
        String sql="Insert into "+ DB_NAME+ " values (?,?)";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, name );
        statement.bindBlob(2, image);
        statement.executeInsert();
    }
    public void updateImage(String name, byte[] image){
        ContentValues cv = new ContentValues();
        cv.put("Image", image);
        myDataBase.update("MazeActivity", cv, "Name = " + name , null);
    }
    public void saveImage(String name, byte[] image){
        if (checkValidImage(name)){
            insertImage(name, image);
        } else {
            updateImage(name, image);
        }
    }
    public boolean checkTimeToday(String time){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("countTime", new String[] {"MyDay"}, "MyDay = ?", new String[] { time }, null, null, null, null);
        return (cursor != null);
    }
    public int getSumTime(String time){

        if (!checkTimeToday(time)) insertNewDay(time); ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("countTime", new String[] {"SumTimeToday"}, "MyDay = ?", new String[] { time }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        return (cursor.getInt(0));
    }
    public int geLimitTime(String time){

        if (!checkTimeToday(time)) insertNewDay(time); ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("countTime", new String[] {"Limit"}, "MyDay = ?", new String[] { time }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        return (cursor.getInt(0));
    }
    public void setSumTime(String time, int timeToday){
        ContentValues cv = new ContentValues();
        cv.put("SumTimeToday", timeToday);
        myDataBase.update("countTime", cv, "MyDay = " + time , null);
    }
    public void setLimitTime(String time, int limit){
        ContentValues cv = new ContentValues();
        cv.put("Limit", limit);
        myDataBase.update("countTime", cv, "MyDay = " + time , null);
    }
    public void insertNewDay(String time){
        SQLiteDatabase db=getWritableDatabase();
        String sql="Insert into "+ DB_NAME+ " values (?,?,?)";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, time);
        statement.bindDouble(2, 1);
        statement.bindDouble(3, 30);
        statement.executeInsert();
    }
    public void saveTime(String time){
        if (checkTimeToday(time)){
            setSumTime(time, getSumTime(time)+ 1);
        } else {
            insertNewDay(time);
        }
    }


    public Bitmap getBitmapbyID(String id) {
        Bitmap bm = BitmapFactory.decodeByteArray(getImageById(id), 0, getImageById(id).length);
        return perform(bm);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }
    public Bitmap perform(Bitmap inp) {
        Bitmap bmOut = Bitmap.createBitmap(inp.getWidth(), inp.getHeight(),
                inp.getConfig());
        int A, R, G, B;
        int w = inp.getWidth();
        int h = inp.getHeight();
        int[] colors = new int[w * h];
        inp.getPixels(colors, 0, w, 0, 0, w, h);
        int i = 0;
        int j = 0;
        int pos;
        int val;
        for (i = 0; i < h; i++) {
            for (j = 0; j < w; j++) {
                pos = i * w + j;
                A = (colors[pos] >> 24) & 0xFF;
                R = (colors[pos] >> 16) & 0xFF;
                G = (colors[pos] >> 8) & 0xFF;
                B = colors[pos] & 0xFF;
                //Thuật toán xử lý cho pixel tại vị trí (i,j)
                colors[pos] = Color.argb(A, R, G, B);
            }
        }
        bmOut.setPixels(colors, 0, w, 0, 0, w, h);
        return bmOut;
    }
}
