package com.example.focupokus;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
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

    public void exit (View view) {

        AlertDialog.Builder ex = new AlertDialog.Builder(this);
        ex.setMessage(" Are you sure that you want to exit ? ")
            .setCancelable(false).setPositiveButton("YaY", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        })
                .setNegativeButton("NaY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                     dialog.cancel();
                    }
                });
        AlertDialog alertDialog = ex.create();
        alertDialog.show();
//
    }
}
