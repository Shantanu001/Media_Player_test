package com.example.media_player_test;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Splash_screen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		
		Timer timer = new Timer();

		timer.schedule(new TimerTask(){
		public void run(){
			startActivity(new Intent(Splash_screen.this,MainActivity.class));
		}
		}
		,5000);
	}

	

}
