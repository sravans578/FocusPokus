package com.example.focupokus;

import android.content.Context;
import android.graphics.Color;
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
    static HashMap<Integer,Integer> hashMap=new HashMap<Integer,Integer>();
    //int[] shapes;
    static int shapes[]={R.drawable.ic_circle,R.drawable.ic_pointed_star,R.drawable.ic_arrow_down_filled_triangle,R.drawable.ic_diamond,R.drawable.ic_hexagon};


    //int shapes1[]={R.drawable.ic_circle,R.drawable.ic_pointed_star,R.drawable.ic_arrow_down_filled_triangle,R.drawable.ic_diamond,R.drawable.ic_hexagon};




    public Adapter(Context con, HashMap<Integer,Integer> m)
    {
        this.con = con;
        this.hashMap = m;

//    public Adapter(Context con, int [] a){
//        this.con = con;
//        this.shapes = a;
    }

    @Override
    public int getCount() {
        return shapes.length;
    }

    @Override
    public Object getItem(int position)
    {
        return shapes[position];
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView shape = new ImageView(con);
        Integer i =getRandomShape();
        //shape.setImageResource(i);
        shape.setImageResource(shapes[position]);
       //shape.setTag(Integer.valueOf(position));

        shape.setScaleType(ImageView.ScaleType.CENTER_CROP);
        //shape.setColorFilter(Color.parseColor(l));

        shape.setColorFilter(getRandomColor());
        Log.d("hello","hi"+position);



        shape.setLayoutParams(new GridView.LayoutParams(169,169));

        return shape;

    }

    public static Integer getRandomColor()
    {
        Integer [] colour={Color.GREEN,Color.RED,Color.YELLOW,Color.BLUE,Color.CYAN, Color.MAGENTA,Color.LTGRAY,Color.BLACK};
        int rnd = new Random().nextInt(colour.length);
        Log.d("colour","colour"+rnd);
        return colour[rnd];
    }

    public static Integer getRandomShape()
    {
        //int shapes[]={R.drawable.ic_circle,R.drawable.ic_pointed_star,R.drawable.ic_arrow_down_filled_triangle,R.drawable.ic_diamond,R.drawable.ic_hexagon,R.drawable.ic_circle};
        int rnd = new Random().nextInt(shapes.length);
        Log.d("shape","shape"+rnd);
        return shapes[rnd];
    }


}

