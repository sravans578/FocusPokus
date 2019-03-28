package com.example.focupokus;
import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Vibrator;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.media.MediaPlayer;
import android.view.HapticFeedbackConstants;
import android.widget.Toast;
import android.widget.ImageView;

import java.util.Random;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class MainActivity extends AppCompatActivity {
//defining shapes for the grid
int shapes[] = {R.drawable.ic_circle, R.drawable.ic_diamond, R.drawable.ic_hexagon, R.drawable.ic_pointed_star, R.drawable.ic_night_moon_phase};
int colors[] = {Color.RED,Color.BLUE,Color.MAGENTA,Color.YELLOW};


    ArrayList<Integer> shapeResult = new ArrayList<Integer>();
    ArrayList<Integer> colorResult = new ArrayList<Integer>();
    GridView grid;
    ImageView targetView;
    TextView textView, score_card;
    TextView et;
    TextView atr;
    Button go_home, restart;
    public int score =0;
	MediaPlayer correctSound;
	MediaPlayer mediaPlayer;
	MediaPlayer wrongSound;
	private Vibrator vibrateEffect;
	public int random;
	public static int attemptsRemaining;
    public JSONArray jsonArrayShapeColor = new JSONArray();

    @Override
    protected void onRestart() {
        super.onRestart();
        setContentView(R.layout.activity_main);
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {

        attemptsRemaining = 3;
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);



        //defining grid
        grid = findViewById(R.id.hello);
        targetView = findViewById(R.id.targetView);
        grid.setNumColumns(3);
        textView =findViewById(R.id.textView);
        et=findViewById(R.id.et);
        atr=findViewById(R.id.atr);


// game over dialog
        final Dialog game_over = new Dialog(this);
        game_over.setCancelable(false);
        game_over.setContentView(R.layout.activity_exit);
        score_card = game_over.findViewById(R.id.score_card);
        go_home = game_over.findViewById(R.id.home_button);
        restart = game_over.findViewById(R.id.restart_button);



        mediaPlayer=MediaPlayer.create(MainActivity.this,R.raw.gamemusic);
        mediaPlayer.setLooping(true);
        boolean isMusic=true;
			if(isMusic) {
				mediaPlayer.start();}
				vibrateEffect = (Vibrator)getApplicationContext().getSystemService(VIBRATOR_SERVICE);
			    grid.setHapticFeedbackEnabled(true);
        final  CountDownTimer timer = new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {
                et.setText("Time remaining: " + millisUntilFinished / 1000 );
            }

            public void onFinish() {
                if (attemptsRemaining == 0 ) {
                    //attemptsRemaining = 3;

                    score_card.setText("Score: " + score);
                    et.setText("Better luck next time!");}
                else
                { attemptsRemaining--;
                atr.setText("Attempts remaining : " + attemptsRemaining);
                this.start();
					}
            }
        };

        //creating json array of shapes and colors
        try {
            for (int kIndex = 0; kIndex < shapes.length; kIndex++) {
                for (int jIndex = 0; jIndex < colors.length; jIndex++) {
                    JSONObject objShapeColor = new JSONObject();
                    objShapeColor.put("shape", shapes[kIndex]);
                    objShapeColor.put("color", colors[jIndex]);
                    jsonArrayShapeColor.put(objShapeColor);
                }
            }
        } catch (Exception e) {
            Log.e("Error",e.getMessage());
        }

        onRandomShapeGenerator(jsonArrayShapeColor);


        final Adapter adapter = new Adapter(this,shapeResult,colorResult);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> s, View v, int position, long id) {

               // atr.setText("Attempts remaining : " + (attemptsRemaining ));
                timer.start();

                correctSound = MediaPlayer.create(MainActivity.this, R.raw.correctanstune);
                wrongSound = MediaPlayer.create(MainActivity.this, R.raw.wronanstune);


                            String match = shapeResult.get(random).toString()+ colorResult.get(random).toString();
                            String to_match = shapeResult.get(position).toString()+ colorResult.get(position).toString();
                            // checking if the shape is correct or not

                            if (match.equals(to_match)) {
                                score++;
                                correctSound.start();
                                grid.setAdapter(null);
                                shapeResult.clear();
                                colorResult.clear();
                                onRandomShapeGenerator(jsonArrayShapeColor);
                                grid.setAdapter(adapter);

                            }
                            // if not correct
                            else
                            {wrongSound.start();
                                attemptsRemaining = (attemptsRemaining) - 1;
                                atr.setText("Attempts remaining : " + (attemptsRemaining ));
                               // atr.setText(attemptsRemaining);
                                if(attemptsRemaining == 0)
                                {   et.setText("Better luck next time !!");
                                    timer.onFinish();
                                    game_over.show();

                                    go_home.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent home = new Intent(getApplicationContext(), start.class);
                                            startActivity(home);
                                        }
                                    });

                                    restart.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            game_over.cancel();
                                            finish();
                                            // ActivityOptions animation = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this);
                                            // sleeping for 1 second so as to make the transition smooth
//                                            try {
//                                                Thread.sleep(1000);
//                                            } catch (InterruptedException error) {
//                                                error.printStackTrace();
//                                            }
                                            //startActivity(getIntent(),animation.toBundle());
                                            startActivity(getIntent());
                                        }
                                    });

                                    }
                            Toast.makeText(getApplicationContext()," " + attemptsRemaining,Toast.LENGTH_LONG).show();
                                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                                getWindow().getDecorView().performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY, HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);
                                v.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
                                vibrateEffect.vibrate(200);

                            }
                            String score_1 = "Score : " + score;

                            textView.setText(score_1);




            }
        });

		}
	@Override
		protected void onPause()
		{
			super.onPause();
			mediaPlayer.release();

		}

		public void onRandomShapeGenerator(JSONArray arrayShapeColor){
        try {
            //Set for 9 unique objects
            Set<JSONObject> shapeSet = new HashSet<>();

            //random generator logic
            while (shapeSet.size() != 9) {
                random = new Random().nextInt(arrayShapeColor.length());
                shapeSet.add(arrayShapeColor.getJSONObject(random));

            }

            //iterator to iterate through unique objects from random
            Iterator itr = shapeSet.iterator();
            while (itr.hasNext()) {
                JSONObject obj = (JSONObject) itr.next();
                shapeResult.add(obj.getInt("shape")); //array of integers
                colorResult.add(obj.getInt("color"));

            }

            final ArrayList<JSONObject> list;
            final int target;

            //setting image and shapes for that image
            target = new Random().nextInt(shapeSet.size());
            random = target;
            list = new ArrayList<>(shapeSet);
            targetView.setImageResource(list.get(target).getInt("shape"));
            targetView.setColorFilter(list.get(target).getInt("color"));

        }
        catch(Exception e){
//            Log.e("",""+e);
        }

        return ;
        }
}

