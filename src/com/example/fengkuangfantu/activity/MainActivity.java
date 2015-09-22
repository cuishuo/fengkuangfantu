package com.example.fengkuangfantu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.example.fengkuangfantu.R;


public class MainActivity extends BaseActivity {
	
	private TextView mainBeginTextView;
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        mainBeginTextView = (TextView)findViewById(R.id.mainBeginTextView);
	        initClicks();
	 }
	 
	 private void initClicks() {
		 mainBeginTextView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), FindImageActivity.class);
				startActivity(intent);
			}
		});
	}
	
}
