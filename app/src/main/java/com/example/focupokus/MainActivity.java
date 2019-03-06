package com.example.focupokus;


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
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    int shapes[] ={R.drawable.circle,R.drawable.cone,R.drawable.cylinder,R.drawable.hexagon,R.drawable.rectangle};
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
        tv=findViewById(R.id.textView);
        et=findViewById(R.id.et);

        final CountDownTimer timer = new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {
                et.setText("seconds remaining: " + millisUntilFinished / 1000 + " :" + millisUntilFinished/100);
            }

            public void onFinish() {
                et.setText("Better luck next time !!!!!!!");
            }
        };




        final String sc =et.getText().toString();

        Adapter adapter = new Adapter(this, shapes);
        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> s, View v, int position, long id) {

                //if(getResources().getResourceEntryName(shapes[position]).equals(sc)) score++;
                //Toast.makeText(getApplicationContext(),getResources().getResourceEntryName(shapes[position]),Toast.LENGTH_LONG).show();
                //tv.setText(getResources().getResourceEntryName(shapes[position]));
                timer.start();


            }
        });


    }



}
