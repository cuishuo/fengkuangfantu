package com.example.fengkuangfantu.activity;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.fengkuangfantu.R;
import com.example.fengkuangfantu.adapter.FindAdapter;
import com.example.fengkuangfantu.service.entity.FindEntity;
import com.example.fengkuangfantu.utils.DisplayNextView;
import com.example.fengkuangfantu.utils.Flip3dAnimation;
import com.example.fengkuangfantu.utils.ToastUtil;

public class FindImageActivity extends BaseActivity {
	
	private boolean isTurn = false;
	private boolean isTurning = false;
	private int random = 0;
	private int number = 4;
	private int lastPostion = -1;
	private int clickCount = 0;
	private int totalCount = 0;
	private ArrayList<FindEntity> findist;
	private ArrayList<Integer> numberList;
	private FindAdapter findAdapter;
	private FrameLayout lastCoverFramlayout;
	private FrameLayout coverFramlayout;
	private GridView findGridView;
	private ImageView lastDefaultImageView;
	private ImageView defaultImageView;
	private ProgressBar findProgressBar;
	private RelativeLayout processRelativeLayout;
	private Runnable timerRunnable;
	private String[] imageCover1;
	private String lastImageName = "";
	private TextView turnImageTextView;
	private TextView turnNextTextView;
	private final int TOTAL_INTERVAL = 100;
	private final int RETRY_INTERVAL = 100;
	private int time = RETRY_INTERVAL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_image_view);
        findGridView = (GridView) findViewById(R.id.findGridView);
        findProgressBar = (ProgressBar) findViewById(R.id.findProgressBar);
        processRelativeLayout = (RelativeLayout) findViewById(R.id.processRelativeLayout);
        turnImageTextView = (TextView) findViewById(R.id.turnImageTextView);
        turnNextTextView = (TextView) findViewById(R.id.turnNextTextView);
        findist = new ArrayList<FindEntity>();
        numberList = new ArrayList<Integer>();
        initViews();
        initClicks();
    }
    
    private void initViews() {
    	imageCover1 = getResources().getStringArray(R.array.image_1);
    	while (numberList.size() < number * 3) {
    		random = (int)(Math.random() * 6);
    		if (numberList.contains(random)) {
    			int count = 0;
    			for (int i = 0; i < numberList.size(); i++) {
					if (numberList.get(i) == random) {
						count ++;
					}
				}
    			if (count == 1) {
    				numberList.add(random);
				}
			} else {
				numberList.add(random);
			}
		}   	
    	for (int i = 0; i < numberList.size(); i++) {
			FindEntity findEntity = new FindEntity();
			findEntity.setCover(imageCover1[numberList.get(i)]);
			findEntity.setIsImageShow(false);
			findist.add(findEntity);
		}
    	findAdapter = new FindAdapter(getApplicationContext(), mHandler, findist, findGridView, true);
    	findGridView.setAdapter(findAdapter);
	}

    private void initClicks() {
    	turnImageTextView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				isTurn = true;
				findAdapter.setShow(true);
				findAdapter.notifyDataSetChanged();
				turnImageTextView.setTextColor(Color.parseColor("#828282"));
				turnImageTextView.setClickable(false);
				mHandler.postDelayed(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						for (int i = 0; i < findist.size(); i++) {
							findist.get(i).setIsImageShow(true);
						}
						findAdapter.setShow(false);
						findAdapter.setIsFirst(false);
						findAdapter.notifyDataSetChanged();
						processRelativeLayout.setVisibility(View.VISIBLE);
						turnImageTextView.setVisibility(View.GONE);
						countDown();
					}
				}, 2000);
			}
		});
    	
    	findGridView.setOnItemClickListener(new OnItemClickListener() {

			@SuppressLint("NewApi") 
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if (!isTurn) {
					ToastUtil.threadShow(FindImageActivity.this, mHandler, R.string.turn_image_first);
					return;
				}
				if (isTurning) {
					return;
				}
				FindEntity entity = findist.get(position);	
				if (!entity.getIsImageShow()) {
					return;
				}
				String nameString = entity.getCover();
				coverFramlayout = (FrameLayout) view.findViewById(R.id.coverFramlayout);
				defaultImageView = (ImageView) view.findViewById(R.id.defaultImageView);
				showImage(coverFramlayout, defaultImageView, false);
				clickCount ++;
				if (clickCount == 1) {
					lastPostion = position;
					lastImageName = nameString;
					lastCoverFramlayout = coverFramlayout;
					lastDefaultImageView = defaultImageView;
					entity.setIsImageShow(false);					
				} else if (clickCount == 2) {
					isTurning = true;
					if (!lastImageName.isEmpty() && !lastImageName.equals(nameString)) {
						mHandler.postDelayed(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								showDefault(lastCoverFramlayout, lastDefaultImageView, true);
								showDefault(coverFramlayout, defaultImageView, true);
								findist.get(lastPostion).setIsImageShow(true);
								isTurning = false;
							}
						}, 400);						
					} else {
						totalCount += 2;
						entity.setIsImageShow(false);
						isTurning = false;
						if (totalCount == findist.size()) {
							ToastUtil.threadShow(FindImageActivity.this, mHandler, R.string.turn_image_sucess);
							mHandler.removeCallbacks(timerRunnable);
							turnNextTextView.setVisibility(View.VISIBLE);
							processRelativeLayout.setVisibility(View.GONE);
						}
					}
					clickCount = 0;
				}		
			}
		});
	}
    
    private void showImage(FrameLayout coverFramlayout, ImageView defaultImageView, boolean isImageShow) {
		coverFramlayout.setVisibility(View.INVISIBLE);
		defaultImageView.setVisibility(View.INVISIBLE);
		coverFramlayout.setAnimationCacheEnabled(true);
		coverFramlayout.setDrawingCacheEnabled(true);
		applyRotation(coverFramlayout, defaultImageView, 0, 90, isImageShow);
		coverFramlayout.setAnimationCacheEnabled(false);
		coverFramlayout.setDrawingCacheEnabled(false);
	}
	
	private void showDefault(FrameLayout coverFramlayout, ImageView defaultImageView, boolean isImageShow) {
		defaultImageView.setVisibility(View.INVISIBLE);
		coverFramlayout.setVisibility(View.INVISIBLE);
		coverFramlayout.setAnimationCacheEnabled(true);
		coverFramlayout.setDrawingCacheEnabled(true);
		applyRotation(coverFramlayout, defaultImageView, 0, -90, isImageShow);
		coverFramlayout.setAnimationCacheEnabled(false);
		coverFramlayout.setDrawingCacheEnabled(false);
	}
	
	private void applyRotation(FrameLayout coverFramlayout, ImageView defaultImageView, float start, float end, boolean isImageShow) {
		final float centerX = 240 / 2.0f;
		final float centerY = 240 / 2.0f;
		final Flip3dAnimation rotation = new Flip3dAnimation(start, end,
				centerX, centerY);
		rotation.setDuration(200);
		rotation.setFillAfter(true);
		rotation.setInterpolator(new AccelerateInterpolator());
		rotation.setAnimationListener(new DisplayNextView(!isImageShow, defaultImageView, coverFramlayout));

		if (!isImageShow) {
			defaultImageView.startAnimation(rotation);
		} else {
			coverFramlayout.startAnimation(rotation);
		}
	}
	
	private void countDown() {
		timerRunnable = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				time--;
				if (time == 0) {
					findProgressBar.setProgress(time);
					ToastUtil.threadShow(FindImageActivity.this, mHandler, R.string.turn_image_fail);
					time = RETRY_INTERVAL;
					turnImageTextView.setText(getResources().getString(R.string.turn_image_retry));
					turnImageTextView.setVisibility(View.VISIBLE);
					processRelativeLayout.setVisibility(View.GONE);
					turnImageTextView.setTextColor(Color.parseColor("#3ad2a2"));
					turnImageTextView.setClickable(true);
					totalCount = 0;
					isTurn = false;
					for (int i = 0; i < findist.size(); i++) {
						findist.get(i).setIsImageShow(false);
					}
					findAdapter = new FindAdapter(getApplicationContext(), mHandler, findist, findGridView, true);
			    	findGridView.setAdapter(findAdapter);
				} else {
					findProgressBar.setProgress(time);
					mHandler.postDelayed(this, TOTAL_INTERVAL);
				}
			}
		};
		mHandler.postDelayed(timerRunnable, TOTAL_INTERVAL);
	}

}
