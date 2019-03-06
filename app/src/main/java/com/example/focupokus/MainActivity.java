package com.example.focupokus;


import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    int shapes[] = {R.drawable.circle, R.drawable.cone, R.drawable.cylinder, R.drawable.hexagon, R.drawable.rectangle};
    int colors[] = {R.color.colorPrimary,R.color.colorAccent,R.color.colorPrimaryDark,R.color.colorYellow};

    GridView grid;
    TextView tv;
    TextView checkRandom;
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
        tv = findViewById(R.id.textView);
        et = findViewById(R.id.et);
        checkRandom = findViewById(R.id.checkRandom);


        final String sc = et.getText().toString();


        Adapter adapter = new Adapter(this, shapes);
        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> s, View v, int position, long id) {

                //if(getResources().getResourceEntryName(shapes[position]).equals(sc)) score++;
                //Toast.makeText(getApplicationContext(),getResources().getResourceEntryName(shapes[position]),Toast.LENGTH_LONG).show();
                tv.setText(getResources().getResourceEntryName(shapes[position]));
            }
        });

        JSONArray ja = new JSONArray();
        try {
        for (int k = 0; k < shapes.length; k++) {
            for (int j = 0; j < colors.length; j++) {
                JSONObject jo = new JSONObject();
                jo.put("shape", k);
                jo.put("color", j);
                ja.put(jo);
            }
        }
        } catch (Exception e) {
            Log.e("Error",e.getMessage());
        }


        //get 9 random shapes
        Set<Object> shapeSet = new HashSet<Object>();
        int random;
        while (shapeSet.size() != 9) {
            try{
                random = new Random().nextInt(ja.length());
                shapeSet.add(ja.get(random));
//                Log.i("",""+random);
            }
            catch(Exception e){

            }

        }
                new CountDownTimer(10000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        et.setText("seconds remaining: " + millisUntilFinished / 1000);
                    }

                    public void onFinish() {
                        et.setText("Better luck next time !!!!!!!");
                    }
                }.start();
        });

        int target = new Random().nextInt(shapeSet.size());
        ArrayList<Object> list = new ArrayList<Object>(shapeSet);
        Log.i("----Target---",""+list.get(target));
        
	ArrayList<Integer> shapeResult = new ArrayList<Integer>();
        ArrayList<Integer> colorResult = new ArrayList<Integer>();
        Iterator itr = shapeSet.iterator();
	while(itr.hasNext()){
		try{
			JSONObject obj = (JSONObject) itr.next();
			shapeResult.add(obj.getInt("shape"));
			colorResult.add(obj.getInt("color"));
		}
		catch(Exception e){
		}

	}

        }
        Log.i("Shapes",""+shapeResult);
        Log.i("Colors",""+colorResult);

}


