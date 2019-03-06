package com.example.focupokus;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.os.Vibrator;

public class start extends AppCompatActivity {
    Button round;
    private Vibrator myVib;
    private View myView;
    MediaPlayer correctsound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        myVib = (Vibrator)getApplicationContext().getSystemService(VIBRATOR_SERVICE);

        round = findViewById(R.id.rou);
        round.setHapticFeedbackEnabled(true);
        View view = findViewById(R.id.rou);
        view.performHapticFeedback(HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);
        getWindow().getDecorView().performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY, HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);
        myView = (View) this.findViewById(R.id.rou);



    }

    public void play (View view) {
        myVib.vibrate(200);
        Log.d("hello","hi"+myVib.hasVibrator());
        Intent play = new Intent(this, MainActivity.class);
        startActivity(play);
    }
}
