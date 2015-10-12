package com.example.fengkuangfantu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.fengkuangfantu.R;

public class WelcomeActivity extends BaseActivity {
	
	private TextView text1;
	private TextView text2;
	private TextView text3;
	private TextView text4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
//		text1 = (TextView)findViewById(R.id.text1);
//		text2 = (TextView)findViewById(R.id.text2);
//		text3 = (TextView)findViewById(R.id.text3);
//		text4 = (TextView)findViewById(R.id.text4);
		initViews();
		
	}
	
	private void initViews() {
		mHandler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), MainActivity.class);
				startActivity(intent);
				finish();
			}
		}, 2000);
//		Animation animation=AnimationUtils.loadAnimation(this, R.anim.xuanzhuan);//xuanzhuan
////		Animation animation=AnimationUtils.loadAnimation(this, R.anim.actin);//����
////		Animation animation=AnimationUtils.loadAnimation(this, R.anim.suofang);//suo fang
//		text1.startAnimation(animation);
//		animation.setFillAfter(true);
//		text2.startAnimation(animation);
//		animation.setFillAfter(true);
//		text3.startAnimation(animation);
//		animation.setFillAfter(true);
//		text4.startAnimation(animation);
//		animation.setFillAfter(true);
//		animation.setAnimationListener(new AnimationListener() {
//			
//			@Override
//			public void onAnimationStart(Animation animation) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void onAnimationRepeat(Animation animation) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void onAnimationEnd(Animation animation) {
//				// TODO Auto-generated method stub
//				Intent intent = new Intent();
//				intent.setClass(getApplicationContext(), MainActivity.class);
//				startActivity(intent);
//				finish();
//			}
//		});
	}
}
