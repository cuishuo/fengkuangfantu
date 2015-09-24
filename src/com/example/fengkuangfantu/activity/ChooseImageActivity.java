package com.example.fengkuangfantu.activity;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.fengkuangfantu.R;
import com.example.fengkuangfantu.adapter.ChooseAdapter;
import com.example.fengkuangfantu.adapter.FindAdapter;
import com.example.fengkuangfantu.service.entity.FindEntity;
import com.example.fengkuangfantu.utils.ColorUtils;
import com.example.fengkuangfantu.utils.ToastUtil;

public class ChooseImageActivity extends BaseActivity {
	
	private boolean isTurn = false;
	private boolean isTurning = false;
	private final int TOTAL_INTERVAL = 50;
	private int random = 0;
	private int number = 4;
	private int lastPostion = -1;
	private int clickCount = 0;
	private int totalCount = 0;
	private int time = 0;
	private int currentLevel = 0;
	private int progressMaxZeng = 1000 / TOTAL_INTERVAL;
	private int maxtime = 20;
	private int levelNum = 13;
	private ArrayList<String[]> imageList;
	private ArrayList<String[]> nameList;
	private ArrayList<String> chooseNameList;
	private ArrayList<FindEntity> chooseList;
	private ArrayList<Integer> numberList;
	private ChooseAdapter chooseAdapter;
	private FrameLayout coverFramlayout;
	private GridView chooseGridView;
	private ImageView defaultImageView;
	private ProgressBar chooseProgressBar;
	private RelativeLayout processRelativeLayout;
	private Runnable timerRunnable;
	private String[] imageCover;
	private String[] imagename;
	private String[] imagenameChoose;
	private String chooseName = "";
	private TextView turnImageTextView;
	private TextView turnNextTextView;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_image_view);
        chooseGridView = (GridView) findViewById(R.id.chooseGridView);
        chooseProgressBar = (ProgressBar) findViewById(R.id.chooseProgressBar);
        processRelativeLayout = (RelativeLayout) findViewById(R.id.processRelativeLayout);
        turnImageTextView = (TextView) findViewById(R.id.turnImageTextView);
        turnNextTextView = (TextView) findViewById(R.id.turnNextTextView);
        imageList = new ArrayList<String[]>();
        nameList = new ArrayList<String[]>();
        chooseNameList = new ArrayList<String>();
        chooseList = new ArrayList<FindEntity>();
        numberList = new ArrayList<Integer>();
        initViews();
        initClicks();
	}
	
	private void initViews() {
    	imageList.add(getResources().getStringArray(R.array.image_choose_1));
    	imageList.add(getResources().getStringArray(R.array.image_choose_2));
    	imageList.add(getResources().getStringArray(R.array.image_choose_3));
    	imageList.add(getResources().getStringArray(R.array.image_choose_4));
    	imageList.add(getResources().getStringArray(R.array.image_choose_5));
    	imageList.add(getResources().getStringArray(R.array.image_choose_6));
    	imageList.add(getResources().getStringArray(R.array.image_choose_7));
    	imageList.add(getResources().getStringArray(R.array.image_choose_8));
    	imageList.add(getResources().getStringArray(R.array.image_choose_9));
    	imageList.add(getResources().getStringArray(R.array.image_choose_10));
    	imageList.add(getResources().getStringArray(R.array.image_choose_11));
    	imageList.add(getResources().getStringArray(R.array.image_choose_12));
    	imageList.add(getResources().getStringArray(R.array.image_choose_13));
    	imageList.add(getResources().getStringArray(R.array.image_choose_14));
    	nameList.add(getResources().getStringArray(R.array.name_choose_1));
    	nameList.add(getResources().getStringArray(R.array.name_choose_2));
    	nameList.add(getResources().getStringArray(R.array.name_choose_3));
    	nameList.add(getResources().getStringArray(R.array.name_choose_4));
    	nameList.add(getResources().getStringArray(R.array.name_choose_5));
    	nameList.add(getResources().getStringArray(R.array.name_choose_6));
    	nameList.add(getResources().getStringArray(R.array.name_choose_7));
    	nameList.add(getResources().getStringArray(R.array.name_choose_8));
    	nameList.add(getResources().getStringArray(R.array.name_choose_9));
    	nameList.add(getResources().getStringArray(R.array.name_choose_10));
    	nameList.add(getResources().getStringArray(R.array.name_choose_11));
    	nameList.add(getResources().getStringArray(R.array.name_choose_12));
    	nameList.add(getResources().getStringArray(R.array.name_choose_13));
    	nameList.add(getResources().getStringArray(R.array.name_choose_14));
    	imagenameChoose = getResources().getStringArray(R.array.name_choose_find);
    	initLevel(getResources().getString(R.string.turn_image_text));
	}
	
	private void initClicks() {
    	turnImageTextView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				isTurn = true;
				chooseAdapter.setShow(true);
				chooseAdapter.notifyDataSetChanged();
				turnImageTextView.setTextColor(ColorUtils.getTextGrey());
				turnImageTextView.setClickable(false);
				mHandler.postDelayed(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						for (int i = 0; i < chooseList.size(); i++) {
							chooseList.get(i).setIsImageShow(true);
						}
						chooseAdapter.setShow(false);
						chooseAdapter.setIsFirst(false);
						chooseAdapter.notifyDataSetChanged();
						processRelativeLayout.setVisibility(View.VISIBLE);
						turnImageTextView.setVisibility(View.GONE);
						showChooseName();
						countDown();
					}
				}, 2000);
			}
		});
    	
    	chooseGridView.setOnItemClickListener(new OnItemClickListener() {

			@SuppressLint("NewApi") 
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					final int position, long id) {
				// TODO Auto-generated method stub
				if (!isTurn) {
					ToastUtil.threadShow(ChooseImageActivity.this, mHandler, R.string.turn_image_first);
					return;
				}
				if (isTurning) {
					return;
				}
				FindEntity entity = chooseList.get(position);	
				if (!entity.getIsImageShow()) {
					return;
				}
				String nameString = entity.getName();
				coverFramlayout = (FrameLayout) view.findViewById(R.id.coverFramlayout);
				defaultImageView = (ImageView) view.findViewById(R.id.defaultImageView);
				showImage(coverFramlayout, defaultImageView, false);				
				isTurning = true;
				if (!chooseName.isEmpty() && !chooseName.equals(nameString)) {
					mHandler.postDelayed(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							showDefault(coverFramlayout, defaultImageView, true);
							chooseList.get(position).setIsImageShow(true);
							isTurning = false;
						}
					}, 400);
				} else {
					totalCount ++;
					isTurning = false;
					if (totalCount == 2) {
						ToastUtil.threadShow(ChooseImageActivity.this, mHandler, R.string.turn_image_sucess);
						mHandler.removeCallbacks(timerRunnable);
						turnNextTextView.setVisibility(View.VISIBLE);
						processRelativeLayout.setVisibility(View.GONE);
						turnImageTextView.setVisibility(View.GONE);
						if (currentLevel == levelNum) {
//							showNextPartAlert();
							ToastUtil.threadShow(ChooseImageActivity.this, mHandler, R.string.choose_image_all_sucess);
						}
					}
				}		
			}
		});
    	turnNextTextView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub				
				if (currentLevel == levelNum) {
//					gotoNextPart();
					ToastUtil.threadShow(ChooseImageActivity.this, mHandler, R.string.choose_image_all_sucess);
				} else {
					currentLevel ++;
					turnNextTextView.setVisibility(View.GONE);
					initLevel(getResources().getString(R.string.turn_image_text));
				}				
			}
		});
	}
	
	private void showChooseName() {
		random = (int)(Math.random() * 6);
		chooseName = imagename[random];
		String name = getResources().getString(R.string.choose_image_title) + chooseName;
		ToastUtil.threadShow(ChooseImageActivity.this, mHandler, name);
	}
	
	private void initLevel(String text) {		
		imageCover = imageList.get(currentLevel);
		imagename = nameList.get(currentLevel);
		time = (maxtime - currentLevel) * progressMaxZeng;
    	chooseProgressBar.setMax(time);		
		turnImageTextView.setText(text);
		turnImageTextView.setVisibility(View.VISIBLE);
		processRelativeLayout.setVisibility(View.GONE);
		turnImageTextView.setTextColor(ColorUtils.getCommonGreen());
		turnImageTextView.setClickable(true);
		totalCount = 0;
		isTurn = false;
		numberList.clear();
		chooseList.clear();
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
			findEntity.setCover(imageCover[numberList.get(i)]);
			findEntity.setName(imagename[numberList.get(i)]);
			findEntity.setIsImageShow(false);
			chooseList.add(findEntity);
		}
    	chooseAdapter = new ChooseAdapter(getApplicationContext(), mHandler, chooseList, chooseGridView, true);
    	chooseGridView.setAdapter(chooseAdapter);
	}
	
	private void countDown() {
		timerRunnable = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				time--;
				if (time == 0) {
					chooseProgressBar.setProgress(time);
					ToastUtil.threadShow(ChooseImageActivity.this, mHandler, R.string.turn_image_fail);					
					initLevel(getResources().getString(R.string.turn_image_retry));
				} else {
					chooseProgressBar.setProgress(time);
					mHandler.postDelayed(this, TOTAL_INTERVAL);
				}
			}
		};
		mHandler.postDelayed(timerRunnable, TOTAL_INTERVAL);
	}

}
