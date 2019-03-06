package com.example.focupokus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class start extends AppCompatActivity {
    Button round;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_start);

        round = findViewById(R.id.rou);
    }

    public void play (View view) {
        Intent play = new Intent(this, MainActivity.class);
        startActivity(play);
    }

    public void exit (View view) {
        finish();
        System.exit(0);
    }
}
