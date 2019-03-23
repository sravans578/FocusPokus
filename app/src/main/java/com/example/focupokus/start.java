package com.example.focupokus;

import android.app.Dialog;
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
import android.widget.LinearLayout;
import android.widget.Toast;
import android.os.Vibrator;

public class start extends AppCompatActivity {
    Button playButton, settingsButton,rulesButton;
    private Vibrator myVib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_start);
        myVib = (Vibrator)getApplicationContext().getSystemService(VIBRATOR_SERVICE);

        playButton = findViewById(R.id.playButton);
        settingsButton = findViewById(R.id.settingsButton);
        rulesButton = findViewById(R.id.rulesButton);

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Dialog dialog = new Dialog(start.this);

                dialog.setContentView(R.layout.activity_settings);
                dialog.show();
            }
        });
    }

    public void play (View view) {
        myVib.vibrate(200);
        Log.d("hello","hi"+myVib.hasVibrator());
        Intent play = new Intent(this, MainActivity.class);
        startActivity(play);
    }

    public void displayRules(View v)
    {
        Dialog dialog = new Dialog(start.this);
        dialog.setContentView(R.layout.activity_settings);
        dialog.show();
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
    }
}
