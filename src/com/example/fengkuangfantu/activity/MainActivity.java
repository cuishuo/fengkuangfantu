package com.example.fengkuangfantu.activity;

import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fengkuangfantu.R;
import com.example.fengkuangfantu.adapter.FindAdapter;
import com.example.fengkuangfantu.adapter.MainImageAdapter;
import com.example.fengkuangfantu.service.entity.FindEntity;
import com.example.fengkuangfantu.utils.DisplayNextView;
import com.example.fengkuangfantu.utils.Flip3dAnimation;
import com.example.fengkuangfantu.utils.ToastUtil;

public class MainActivity extends BaseActivity {

	 private Animation animation;
	private ArrayList<FindEntity> findist;
	private MainImageAdapter mainImageAdapter;
	private FrameLayout coverFramlayout;
	private GridView mainImageGridView;
	private ImageButton startImagebutton;
	private ImageView clickImageView;
	private ImageView defaultImageView;
	private Runnable timerRunnable;
	private TextView mainTitleTextView;
	private final int REPET_INTERVAL = 3000;
	private final int RETRY_INTERVAL = 50;
	private int time = RETRY_INTERVAL;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mainImageGridView = (GridView) findViewById(R.id.mainImageGridView);
		startImagebutton = (ImageButton) findViewById(R.id.startImagebutton);
		clickImageView = (ImageView) findViewById(R.id.clickImageView);
		mainTitleTextView = (TextView) findViewById(R.id.mainTitleTextView);
		findist = new ArrayList<FindEntity>();
		initViews();
		initClicks();
		tartClick();
	}

	private void initViews() {
		for (int i = 0; i < 9; i++) {
			FindEntity findEntity = new FindEntity();
			findEntity.setCover("main_click_image");
			findEntity.setIsImageShow(false);
			findist.add(findEntity);
		}
		mainImageAdapter = new MainImageAdapter(getApplicationContext(),
				mHandler, findist, mainImageGridView, true);
		mainImageGridView.setAdapter(mainImageAdapter);
	}

	private void initClicks() {
		startImagebutton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(),
						FindImageActivity.class);
				startActivity(intent);
			}
		});
	}

	private void tartClick() {
		timerRunnable = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				time--;
				if (time == 0) {

				} else {
					clickImageView.setVisibility(View.VISIBLE);
					animation = AnimationUtils.loadAnimation(MainActivity.this,
							R.anim.down_in);
					animation.setFillAfter(true);
					clickImageView.startAnimation(animation);
					animation.setAnimationListener(new AnimationListener() {

						@Override
						public void onAnimationStart(Animation animation) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onAnimationRepeat(Animation animation) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onAnimationEnd(Animation animation) {
							// TODO Auto-generated method stub
							coverFramlayout = (FrameLayout) mainImageGridView
									.getChildAt(4).findViewById(
											R.id.coverFramlayout);
							defaultImageView = (ImageView) mainImageGridView
									.getChildAt(4).findViewById(
											R.id.defaultImageView);
							showImage(coverFramlayout, defaultImageView, false);
							if (animation !=  null) {
								clickImageView.clearAnimation();
								animation = null;
							}
							clickImageView.setVisibility(View.GONE);
							mHandler.postDelayed(new Runnable() {

								@Override
								public void run() {
									// TODO Auto-generated method stub
									showDefault(coverFramlayout, defaultImageView, true);
								}
							}, 500);

						}
					});
					// clickImageView.startAnimation(animation);
					 mHandler.postDelayed(this, REPET_INTERVAL);
				}
			}
		};
		mHandler.postDelayed(timerRunnable, REPET_INTERVAL);
	}

}
