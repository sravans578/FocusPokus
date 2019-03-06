package com.example.focupokus;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.media.Image;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
import android.widget.ImageView;
import java.util.logging.Logger;
import java.util.Random;
import java.util.*;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //int shapes[] ={R.drawable.circle,R.drawable.cone,R.drawable.cylinder,R.drawable.hexagon,R.drawable.ic_star,R.drawable.ic_star,R.drawable.ic_star,R.drawable.ic_star,R.drawable.ic_star,R.drawable.ic_star,R.drawable.ic_star};


static int shapes[]={R.drawable.ic_circle,R.drawable.ic_pointed_star,R.drawable.ic_arrow_down_filled_triangle,R.drawable.ic_diamond,R.drawable.ic_hexagon,R.drawable.ic_circle,R.drawable.ic_circle,R.drawable.ic_circle,R.drawable.ic_circle};
static int shapes1[]= new int [25];
static Integer [] colour={Color.GREEN,Color.RED,Color.YELLOW,Color.BLUE,Color.CYAN, Color.MAGENTA,Color.LTGRAY,Color.BLACK};

static HashMap<Integer,Integer> hm=new HashMap<Integer,Integer>();


    public static Integer getRandomShape()
    {
        //int shapes[]={R.drawable.ic_circle,R.drawable.ic_pointed_star,R.drawable.ic_arrow_down_filled_triangle,R.drawable.ic_diamond,R.drawable.ic_hexagon,R.drawable.ic_circle};
        int rnd = new Random().nextInt(shapes.length);
        Log.d("shape", "shape" + rnd);
        return shapes[rnd];
    }

    public void  getRandomShape1()
    {
        for(int i=0;i<16;i++)
        {
            int shape=getRandomShape();
            shapes1[i]=shape;
        }

    }
    public static void  getRandomShapewithcolour()
    {
        //int shapes[]={R.drawable.ic_circle,R.drawable.ic_pointed_star,R.drawable.ic_arrow_down_filled_triangle,R.drawable.ic_diamond,R.drawable.ic_hexagon,R.drawable.ic_circle};
        for(int i=0;i<16;i++)
        {
            int rndshape = new Random().nextInt(shapes.length);
            int randcolour = new Random().nextInt(colour.length);
            hm.put(rndshape,randcolour);


        }

    }

    GridView grid;
    TextView tv;
    TextView et;
    public int score =0;
    public String hi= "clicked!";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        grid = findViewById(R.id.hello);
        grid.setNumColumns(4);
        tv=findViewById(R.id.textView);
        et=findViewById(R.id.et);





        final String sc =et.getText().toString();
        getRandomShape1();
        getRandomShapewithcolour();
        final Adapter adapter = new Adapter(this,hm);
        grid.setAdapter(adapter);



        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> s, View v, int position, long id) {

                //if(getResources().getResourceEntryName(shapes[position]).equals(sc)) score++;
                Toast.makeText(getApplicationContext(),getResources().getResourceEntryName(shapes1[position]),Toast.LENGTH_LONG).show();
                long i = grid.getItemIdAtPosition(position);
                tv.setText(getResources().getResourceEntryName(shapes1[position]));

                new CountDownTimer(10000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        et.setText("seconds remaining: " + millisUntilFinished / 1000);
                    }

                    public void onFinish() {
                        et.setText("Better luck next time !!!!!!!");
                    }
                }.start();

            }
        });
    }



}
