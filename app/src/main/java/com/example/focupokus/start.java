package com.example.focupokus;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class start extends AppCompatActivity {
    Button playButton, settingsButton,rulesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

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
        Intent play = new Intent(this, MainActivity.class);
        startActivity(play);
    }
    public void displayRules(View v)
    {
        Dialog dialog = new Dialog(start.this);
        dialog.setContentView(R.layout.activity_settings);
        dialog.show();
    }
}
