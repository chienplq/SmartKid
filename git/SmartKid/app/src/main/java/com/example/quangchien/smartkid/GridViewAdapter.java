package com.example.quangchien.smartkid;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.LayoutInflaterFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;



public class GridViewAdapter extends BaseAdapter {

    private Context context ;
    private Bitmap[] bitmap;

    public GridViewAdapter(Context context, Bitmap[] bitmaps) {
        this.context = context;
        this.bitmap = bitmaps;
    }

    @Override
    public int getCount() {
        return bitmap.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.gridview_row,null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imgView);
        imageView.setImageBitmap(bitmap[i]);

        return view;
    }
}