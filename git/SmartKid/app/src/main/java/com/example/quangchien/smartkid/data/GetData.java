package com.example.quangchien.smartkid.data;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GetData {
    public byte[] getImage(String name){
        byte[] img = null;
        Connection con;
        con = getConnection();        // Connect to database
        if (con != null)
        {
            // Change below query according to your own database.
            try{
                String query = "select Image from Image1 where Name = '"+name+"'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next())
                {
                    img = rs.getBytes("Image");
                }
                con.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

        return img;
    }



    public List<Image> getAllImage(){
        List<Image> list = new ArrayList<>();
        Connection con;
        con = getConnection();        // Connect to database
        if (con != null)
        {
            // Change below query according to your own database.
            try{
                String query = "select * from Image1";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next())
                {

                    Image img = new Image(rs.getString("Name"),rs.getBytes("Image"));
                    list.add(img);
                }

                con.close();

            }catch (Exception ex){
                ex.printStackTrace();
            }
        }



        return list;
    }


    public Connection getConnection(){

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;
        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL = "jdbc:jtds:sqlserver://192.168.1.105:1433;databaseName=testAndd;user=sa;password=nguyen123;";
            connection = DriverManager.getConnection(ConnectionURL);
        }
        catch (SQLException se)
        {
            se.printStackTrace();

        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            //  Log.e("error here 2 : ", e.getMessage());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return connection;
    }
}
