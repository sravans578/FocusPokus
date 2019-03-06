package com.example.focupokus;


import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.media.MediaPlayer;
import android.view.HapticFeedbackConstants;


public class MainActivity extends AppCompatActivity {

    int shapes[] ={R.drawable.circle,R.drawable.cone,R.drawable.cylinder,R.drawable.hexagon,R.drawable.rectangle};
    GridView grid;
    TextView tv;
    TextView et;
    public int score =0;
    public String hi= "clicked!";
    MediaPlayer correctsound;
    MediaPlayer mediaPlayer;
    MediaPlayer wrongSound;
    private Vibrator myVib;



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

        mediaPlayer=MediaPlayer.create(MainActivity.this,R.raw.gamemusic);
        mediaPlayer.setLooping(true);
        boolean ismusic=true;
        if(ismusic) {
            mediaPlayer.start();
        }
        myVib = (Vibrator)getApplicationContext().getSystemService(VIBRATOR_SERVICE);
        final String sc =et.getText().toString();
        Adapter adapter = new Adapter(this, shapes);
        grid.setAdapter(adapter);
        grid.setHapticFeedbackEnabled(true);


        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> s, View v, int position, long id) {

                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                getWindow().getDecorView().performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY, HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);

                if(correctsound!=null )
                {
                        correctsound.stop();
                        correctsound.release();
                        wrongSound.stop();
                        wrongSound.release();
                }

                correctsound = MediaPlayer.create(MainActivity.this, R.raw.correctanstune);
                wrongSound = MediaPlayer.create(MainActivity.this, R.raw.wronanstune);


               if(position%2==0)
               {
                   correctsound.start();
               }
               else
               {
                   wrongSound.start();
               }
                v.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
                tv.setText(getResources().getResourceEntryName(shapes[position]));
                myVib.vibrate(200);


            }

        });






    }
    @Override
    protected void onPause()
    {
        super.onPause();
       mediaPlayer.release();

    }
}
