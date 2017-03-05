package com.example.media_player_test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class Sp extends Activity implements OnInitListener{
	 TextToSpeech tt;
	 TextView  tv;
	 TextView tv2;
	 Animation animations;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sp);
		tv = (TextView) findViewById(R.id.textView2);
		tv2  = (TextView) findViewById(R.id.textView2);
		tt = new TextToSpeech(this,this);
		animations = AnimationUtils.loadAnimation(this,R.anim.animation);
		tv.startAnimation(animations);
		tv2.startAnimation(animations);
		Thread t = new Thread(){
			public void run() {
			  try {
				  Thread.sleep(5000);
				 // startActivity(new Intent(Splash.this,MainActivity.class));
					startActivity(new Intent(Sp.this,MainActivity.class));
				
			} catch (InterruptedException e) {
				// TODO: handle exception
			}	
			}
		};
		t.start();
	}
	@Override
	public void onInit(int status) {
		// TODO Auto-generated method stub
		String msg = tv.getText().toString();
		tt.speak(msg,TextToSpeech.QUEUE_FLUSH,null);
		
	}

}
