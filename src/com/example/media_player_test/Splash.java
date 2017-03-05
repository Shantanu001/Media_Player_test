package com.example.media_player_test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class Splash extends Activity implements OnInitListener{
    TextView tv;
    TextToSpeech tt;
	Animation animation;
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv = (TextView) findViewById(R.id.textView2);
		//Instantiate texttospeech
		tt = new TextToSpeech(this,this);
		//Instantiate animation
		animation =AnimationUtils.loadAnimation(this,R.anim.animation);
		tv.startAnimation(animation);
		Thread t = new Thread(){
			public void run() {
			  try {
				  Thread.sleep(500);
				 // startActivity(new Intent(Splash.this,MainActivity.class));
				
			} catch (InterruptedException e) {
				// TODO: handle exception
			}	
			}
		};
		t.start();
		
	}
	/*public void play(View v)
	{
		String temp = tv.getText().toString();
		//tt.setPitch(1);
		//tt.setSpeechRate(100);
		tt.speak(temp,TextToSpeech.QUEUE_FLUSH,null);
	}*/
	/*
	 @Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		String temp = tv.getText().toString();
		tt.speak(temp,TextToSpeech.QUEUE_FLUSH,null);
	}*/
	@Override
	public void onInit(int status) {
		// TODO Auto-generated method stub
		String temp = tv.getText().toString();
		tt.speak(temp,TextToSpeech.QUEUE_FLUSH,null);
	}

	

}
