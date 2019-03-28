package com.example.focupokus;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.graphics.ColorUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import java.util.*;
import java.util.HashMap;
import java.util.Random;

public class Adapter extends BaseAdapter {

    Context con;
    ArrayList<Integer> gridShapes;
    ArrayList<Integer> gridColors;


    public Adapter(Context con, ArrayList<Integer> gridShapes, ArrayList<Integer> gridColors) {
        this.con = con;
        this.gridShapes = gridShapes;
        this.gridColors = gridColors;
    }

    @Override
    public int getCount() {//return gridShapes.size();
        return gridColors.size(); }

    @Override
    public Object getItem(int position)
    {
        return gridShapes.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView shape = new ImageView(con);
        Log.i("----Shape",""+gridShapes.get(position));
//        Log.i("----Color",""+gridColors);

        Log.i("color","color"+gridColors.get(position));
        //setting the grid images
        shape.setImageResource(gridShapes.get(position));
        shape.setScaleType(ImageView.ScaleType.CENTER_CROP);
        shape.setColorFilter(gridColors.get(position));

        shape.setLayoutParams(new GridView.LayoutParams(160,160));

        return shape;

    }


}

