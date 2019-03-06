package com.example.focupokus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class start extends AppCompatActivity {
    Button round;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        round = findViewById(R.id.rou);
    }

    public void play (View view) {
        Intent play = new Intent(this, MainActivity.class);
        startActivity(play);
    }

    public void settings()
    {

    }
}
