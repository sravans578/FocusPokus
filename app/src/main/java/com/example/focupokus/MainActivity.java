package com.example.focupokus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    int shapes[] = {R.drawable.circle, R.drawable.cone, R.drawable.cylinder, R.drawable.hexagon, R.drawable.rectangle};
    String sampleText = "Hello";
    String shapesToSelect[] = {
            "Circle1", "Square1", "Triangle1",
            "Circle2", "Square2", "Triangle2",
            "Circle3", "Square3", "Triangle3",
            "Circle4", "Square4", "Triangle4",
            "Circle5", "Square5", "Triangle5",
            "Circle6", "Square6", "Triangle6",
    };
    String colorsToSelect[] = {
            "Red1", "Yellow1", "Blue1",
            "Red2", "Yellow2", "Blue2",
            "Red3", "Yellow3", "Blue3",
            "Red4", "Yellow4", "Blue4",
            "Red5", "Yellow5", "Blue5",
            "Red6", "Yellow6", "Blue6",
    };
    GridView grid;
    TextView tv;
    EditText et;
    TextView checkRandom;
    public int score = 0;
    public String hi = "clicked!";

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
//        RandomGenerator rg = new RandomGenerator(this,sampleText);
//        Log.i("Hello",rg.getSample());
//        checkRandom.setText(rg.getSample());


        /**
         * random generator with arrays
         */
        ArrayList<String> combinations = new ArrayList<String>();
        for (int i = 0; i < colorsToSelect.length; i++) {
            for (int j = 0; j < shapesToSelect.length; j++) {
                combinations.add(colorsToSelect[i] + " " + shapesToSelect[j]);
            }
        }

        //get 9 random shapes
        Set<String> shapeSet = new HashSet<String>();
        int random;
        while(shapeSet.size()!=9){
            random = new Random().nextInt(combinations.size());
            shapeSet.add(combinations.get(random));
        }
        Log.i("hello",shapeSet.toString());

        //get target object
        int target = new Random().nextInt(shapeSet.size());
        
        Log.i("target",);
    }
}

