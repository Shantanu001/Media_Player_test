package com.example.media_player_test;

import java.io.File;
import java.io.FilenameFilter;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.ToggleButton;

public class MainActivity extends Activity implements OnItemClickListener,OnCheckedChangeListener {
   int songIndex=0;
   MediaPlayer mplayer;
   ImageButton play,pause;
   File[] songs ;
   ToggleButton tb;
   ListView list;
   Handler handler = new Handler();
   SeekBar seek;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		list = (ListView) findViewById(R.id.listView1);
		tb  = (ToggleButton) findViewById(R.id.toggleButton1);
		list.setOnItemClickListener(this);
		tb.setOnCheckedChangeListener(this);
		play =(ImageButton) findViewById(R.id.btn_play);
		//mplayer = MediaPlayer.create(this,R.raw.year);
		seek = (SeekBar) findViewById(R.id.seekBar1);
		File sdCard = Environment.getExternalStorageDirectory();
		songs = sdCard.listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String filename) {
				// TODO Auto-generated method stub
				return filename.toLowerCase().endsWith(".mp3");
			}
		});
		//Creating  mediaplayer object
		mplayer = new MediaPlayer();
		//Initializing MediaPlayer object using setDataSource().
		try {
			mplayer.setDataSource(songs[songIndex].getAbsolutePath());
           mplayer.prepare();
		} catch (Exception e) {
			// TODO: handle exception
		}
		seekupdate();
	}

	public void btnplay(View v)
	{
		//check whether MediaPlayer is already started or not
		if(!mplayer.isPlaying())
		{
			//play the mediaplayer
			mplayer.start();
			//change button image on click
			play.setImageResource(android.R.drawable.ic_media_pause);
			
		}
		else
		{
			//pause the mediapalyer
			mplayer.pause();
			//change button image
			play.setImageResource(android.R.drawable.ic_media_play);
		}
	}
	@Override
		protected void onDestroy() {
			// TODO Auto-generated method stub
			super.onDestroy();
			mplayer.stop();
		}
	
public void btnFforward(View v)
{
  //Geting current position
   int currentPosition =  	mplayer.getCurrentPosition();
   int seekTime = 5000;
   mplayer.seekTo(currentPosition+seekTime);
}
public void btnrewind (View v)
{
	
	   int currentPosition =  	mplayer.getCurrentPosition();
	   int seekTime = 5000;
	   mplayer.seekTo(currentPosition-seekTime);}
public void btnnext(View v)
{
	songIndex++;
try {
	mplayer.reset();
	mplayer.setDataSource(songs[songIndex].getAbsolutePath());
	mplayer.prepare();
    mplayer.start();
} catch (Exception e) {
	// TODO: handle exception
}	
}
public void btnprevious(View v)
{
	songIndex--;
try {
	mplayer.reset();
	mplayer.setDataSource(songs[songIndex].getAbsolutePath());
	mplayer.prepare();
	mplayer.start();
} catch (Exception e) {
	// TODO: handle exception
}
	}
/*public void showallsong(View v)
{ 
	ArrayAdapter<File> adapter = new ArrayAdapter<File>(this,android.R.layout.simple_list_item_1,songs);
   list.setAdapter(adapter);	
	ArrayAdapter<File> adapter = new ArrayAdapter<File>(MainActivity.this, R.layout.custom_textview,songs);
	list.setAdapter(adapter);
}*/
public void toggle(View v)
{ /*
	ArrayAdapter<File> adapter = new ArrayAdapter<File>(this,android.R.layout.simple_list_item_1,songs);
   list.setAdapter(adapter);*/	
	ArrayAdapter<File> adapter = new ArrayAdapter<File>(MainActivity.this, R.layout.custom_textview,songs);
	list.setAdapter(adapter);
}
@Override
public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
	// TODO Auto-generated method stub
	try {
		mplayer.reset();
		mplayer.setDataSource(songs[arg2].getAbsolutePath());
		mplayer.prepare();
		mplayer.start();
		play.setImageResource(android.R.drawable.ic_media_pause);
	} catch (Exception e) {
		// TODO: handle exception
	}
	
}
Runnable run = new Runnable() {
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		seekupdate();
	}
};
public void seekupdate()
{
	seek.setMax(mplayer.getDuration());
    seek.setProgress(mplayer.getCurrentPosition());
    handler.postDelayed(run,100);
}

@Override
public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
	// TODO Auto-generated method stub
	if(isChecked)
	{
		list.setVisibility(ListView.VISIBLE);
		
	}
	else
	{
		list.setVisibility(ListView.INVISIBLE);
	}
}
public void change(View v)
{
	startActivity(new Intent (MainActivity.this,Sp.class));
}
}

