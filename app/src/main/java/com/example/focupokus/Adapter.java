package com.example.focupokus;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class Adapter extends BaseAdapter {

    Context con;
    int[] shapes;

    public Adapter(Context con, int [] a){
        this.con = con;
        this.shapes = a;
    }

    @Override
    public int getCount() {
        return shapes.length;
    }

    @Override
    public Object getItem(int position) {
        return shapes[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView shape = new ImageView(con);

        shape.setImageResource(shapes[position]);
        shape.setScaleType( ImageView.ScaleType.CENTER_CROP);
        shape.setHapticFeedbackEnabled(true);
        shape.setLayoutParams(new GridView.LayoutParams(500,500));
        return shape;

    }
}
