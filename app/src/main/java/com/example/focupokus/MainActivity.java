package com.example.focupokus;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.media.Image;

import android.os.Vibrator;
import android.os.CountDownTimer;
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

import android.widget.Toast;
import android.util.Log;
import android.widget.ImageView;
import java.util.logging.Logger;
import java.util.Random;
import java.util.*;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

	int shapes[] = {R.drawable.ic_circle, R.drawable.ic_diamond, R.drawable.ic_hexagon, R.drawable.ic_pointed_star, R.drawable.ic_night_moon_phase};
	//int colors[] = {R.color.colorPrimary,R.color.colorAccent,R.color.colorPrimaryDark,R.color.colorYellow};
	int colors[] = {Color.RED,Color.BLUE,Color.MAGENTA,Color.YELLOW};


	ArrayList<Integer> shapeResult = new ArrayList<Integer>();
	ArrayList<Integer> colorResult = new ArrayList<Integer>();
	GridView grid;
	TextView tv;
	TextView checkRandom;
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
			//tv = findViewById(R.id.textView);
			//et = findViewById(R.id.et);
			//checkRandom = findViewById(R.id.checkRandom);
			grid.setNumColumns(3);
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
			//Adapter adapter = new Adapter(this, shapes);
			//grid.setAdapter(adapter);
			grid.setHapticFeedbackEnabled(true);
			final CountDownTimer timer = new CountDownTimer(10000, 1000) {

				public void onTick(long millisUntilFinished) {
					et.setText("Time remaining: " + millisUntilFinished / 1000 );
				}

				public void onFinish() {
					et.setText("Better luck next time !!!!!!!");
				}
			};



			JSONArray ja = new JSONArray();
			try {
				for (int k = 0; k < shapes.length; k++) {
					for (int j = 0; j < colors.length; j++) {
						JSONObject jo = new JSONObject();
						jo.put("shape", shapes[k]);
						jo.put("color", colors[j]);
						ja.put(jo);
					}
				}
			} catch (Exception e) {
				Log.e("Error",e.getMessage());
			}


			//get 9 random shapes
			Set<JSONObject> shapeSet = new HashSet<>();
			int random;
			while (shapeSet.size() != 9) {
				try{
					random = new Random().nextInt(ja.length());
					shapeSet.add(ja.getJSONObject(random));
					Log.i("Shapeset------------",""+shapeSet);
				}
				catch(Exception e){

				}

			}

			int target = new Random().nextInt(shapeSet.size());
			ArrayList<Object> list = new ArrayList<Object>(shapeSet);
			//        Log.i("----Target---",""+list.get(target));


			Iterator itr = shapeSet.iterator();
			while(itr.hasNext()) {
				try {
					JSONObject obj = (JSONObject) itr.next();
					shapeResult.add(obj.getInt("shape")); //array of integers
					colorResult.add(obj.getInt("color"));
				} catch (Exception e) {
				}
			}
			Log.i("---------",""+shapeResult);



			final Adapter adapter = new Adapter(this,shapeResult,colorResult);
			grid.setAdapter(adapter);
			grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> s, View v, int position, long id) {
						timer.start();

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
					//tv.setText(getResources().getResourceEntryName(shapes[position]));
					myVib.vibrate(200);
					//if(getResources().getResourceEntryName(shapes[position]).equals(sc)) score++;
					//                Toast.makeText(getApplicationContext(),getResources().getResourceEntryName(shapes[position]),Toast.LENGTH_LONG).show();
					//                long i = grid.getItemIdAtPosition(position);
					//                Log.i("--------------",""+shapeResult);
					tv.setText(getResources().getResourceEntryName(shapeResult.get(position)));




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


