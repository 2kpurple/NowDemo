package com.example.nowdemo;

import com.example.nowdemo.MyView.OnClickListener;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {
	
	private MyView myView;
	private float vol;
	private RecordingThread thread;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		myView = (MyView) findViewById(R.id.myView1);
		myView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick() {
				// TODO Auto-generated method stub
				if(!myView.isRun()){
					thread = new RecordingThread(handler);
					thread.start();
				} else {
					thread.stopRecording();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			if(msg.what == 1){
				vol = msg.getData().getFloat("vol");
				myView.setCr(vol);
				myView.invalidate();
			}
		}
		
	};

}
