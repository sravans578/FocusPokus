package com.example.focupokus;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Vibrator;

public class start extends AppCompatActivity {
    private Button playButton, settingsButton,rulesButton, yesButton, noButton, nextButton;
    private Vibrator myVib;
    private ImageView imgCancel,imgClose,icCancel;
    private LinearLayout settingLayout,linSample;
    private TextView tvContent,tvCorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_start);
        myVib = (Vibrator)getApplicationContext().getSystemService(VIBRATOR_SERVICE);

        final Dialog settingDialog = new Dialog(start.this);
        settingDialog.setContentView(R.layout.activity_settings);

        final Dialog playDialog = new Dialog(start.this);
        playDialog.setContentView(R.layout.activity_gameplay);

        playButton = findViewById(R.id.playButton);
        settingsButton = findViewById(R.id.settingsButton);
        rulesButton = settingDialog.findViewById(R.id.rulesButton);
        nextButton = playDialog.findViewById(R.id.nextButton);
        tvContent = playDialog.findViewById(R.id.tvContent);
        tvCorrect = playDialog.findViewById(R.id.tvCorrect);
        linSample = playDialog.findViewById(R.id.llSample);

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //final Dialog dialog =  new Dialog(start.this);
                //dialog.setContentView(R.layout.activity_settings);
                settingDialog.show();


                imgClose = settingDialog.findViewById(R.id.imageView_close);

                imgClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        settingDialog.cancel();
                    }
                });
            }
        });

        rulesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingDialog.dismiss();
                playDialog.show();
                firstStep(v);

                icCancel = playDialog.findViewById(R.id.imgCancel);
                icCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        playDialog.cancel();
                    }
                });
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nextButton.getText()=="OK")
                {
                    playDialog.cancel();
                    settingDialog.show();
                }
                else {
                    nextStep(v);
                }
            }
        });
    }

    public void play (View view) {
        myVib.vibrate(200);
        Log.d("hello","hi"+myVib.hasVibrator());
        Intent play = new Intent(this, MainActivity.class);
        startActivity(play);
    }

    public void firstStep(View v)
    {
        tvContent.setText(R.string.gamePlay);
        tvCorrect.setText(R.string.correct);
        linSample.setVisibility(View.VISIBLE);
        nextButton.setText("Next");
    }

    public void nextStep(View v)
    {
        tvContent.setText(R.string.step2);
        tvCorrect.setText(R.string.tryitout);
        linSample.setVisibility(View.GONE);
        nextButton.setText("OK");
    }

    public void exit (View view) {

        final Dialog dialog = new Dialog(start.this);
        dialog.setContentView(R.layout.activity_exit);
        dialog.show();

        imgCancel = dialog.findViewById(R.id.icClose);
        yesButton = dialog.findViewById(R.id.yesButton);
        noButton = dialog.findViewById(R.id.noButton);

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
    }
}
