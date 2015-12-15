package com.example.fengkuangfantu.activity;

import java.io.IOException;
import java.util.ArrayList;

import android.R.integer;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SyncStateContract.Columns;
import android.util.Log;
import android.view.View;
import android.view.Window;
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
import com.example.fengkuangfantu.utils.ColorUtils;
import com.example.fengkuangfantu.utils.DisplayNextView;
import com.example.fengkuangfantu.utils.Flip3dAnimation;
import com.example.fengkuangfantu.utils.SoundPlay;
import com.example.fengkuangfantu.utils.ToastUtil;

public class FindImageActivity extends BaseActivity {
	
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
	private int levelNum = 11;
	private int music;
	private AlertDialog alertDialog;
	private ArrayList<FindEntity> findist;
	private ArrayList<Integer> numberList;
	private ArrayList<String[]> imageList;
	private String[] guanNameList;
	private FindAdapter findAdapter;
	private FrameLayout lastCoverFramlayout;
	private FrameLayout coverFramlayout;
	private GridView findGridView;
	private ImageView lastDefaultImageView;
	private ImageView defaultImageView;
	private MediaPlayer mediaPlayer;
	private ProgressBar findProgressBar;
	private RelativeLayout processRelativeLayout;
	private Runnable timerRunnable;
	private String[] imageCover;
	private String[] defaultImageCover;
	private String lastImageName = "";
	private SoundPool soundPool;
	private TextView turnImageTextView;
	private TextView turnNextTextView;
	private TextView guanTextView;
	private TextView timeTextView;
    private SoundPlay mSoundPlay;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_image_view);
        findGridView = (GridView) findViewById(R.id.findGridView);
        findProgressBar = (ProgressBar) findViewById(R.id.findProgressBar);
        processRelativeLayout = (RelativeLayout) findViewById(R.id.processRelativeLayout);
        turnImageTextView = (TextView) findViewById(R.id.turnImageTextView);
        turnNextTextView = (TextView) findViewById(R.id.turnNextTextView);
        guanTextView = (TextView)findViewById(R.id.guanTextView);
        timeTextView = (TextView)findViewById(R.id.timeTextView);
        findist = new ArrayList<FindEntity>();
        numberList = new ArrayList<Integer>();
        imageList = new ArrayList<String[]>();
        mediaPlayer = new MediaPlayer();
        soundPool = new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);
        music = soundPool.load(this, R.raw.anjian, 1);
        initViews();
        initClicks();
    }
    
    private void initViews() {
    	imageList.add(getResources().getStringArray(R.array.image_1));
    	imageList.add(getResources().getStringArray(R.array.image_2));
    	imageList.add(getResources().getStringArray(R.array.image_3));
    	imageList.add(getResources().getStringArray(R.array.image_4));
    	imageList.add(getResources().getStringArray(R.array.image_5));
    	imageList.add(getResources().getStringArray(R.array.image_6));
    	imageList.add(getResources().getStringArray(R.array.image_7));
    	imageList.add(getResources().getStringArray(R.array.image_8));
    	imageList.add(getResources().getStringArray(R.array.image_9));
    	imageList.add(getResources().getStringArray(R.array.image_10));
    	imageList.add(getResources().getStringArray(R.array.image_11));
    	imageList.add(getResources().getStringArray(R.array.image_12));
    	imageList.add(getResources().getStringArray(R.array.image_13));
    	imageList.add(getResources().getStringArray(R.array.image_14));
    	guanNameList = getResources().getStringArray(R.array.guan_name);
    	defaultImageCover = getResources().getStringArray(R.array.default_image);
    	initLevel(getResources().getString(R.string.turn_image_text));
	}

    private void initClicks() {
    	turnImageTextView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				isTurn = true;
				findAdapter.setShow(true);
				findAdapter.notifyDataSetChanged();
				turnImageTextView.setTextColor(ColorUtils.getTextGrey());
				turnImageTextView.setClickable(false);
				soundPool.play(music, 1, 1, 0, 0, 1);
				mHandler.postDelayed(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						soundPool.play(music, 1, 1, 0, 0, 1);
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
							turnImageTextView.setVisibility(View.GONE);
							if (currentLevel == levelNum) {
								showNextPartAlert();
							} 
						}
					}
					clickCount = 0;
				}		
			}
		});
    	turnNextTextView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub				
				if (currentLevel == levelNum) {
					gotoNextPart();
				} else {
					currentLevel ++;
					turnNextTextView.setVisibility(View.GONE);
					initLevel(getResources().getString(R.string.turn_image_text));
				}				
			}
		});
	}
    
    private void gotoNextPart() {
		Intent intent = new Intent();
		intent.setClass(getApplicationContext(), ChooseImageActivity.class);
		startActivity(intent);
		finish();
	}
    
    protected void showNextPartAlert() {
		alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.show();
		Window window = alertDialog.getWindow();
		window.setContentView(R.layout.next_part_aleart_view);
		RelativeLayout returnRelativeLayout = (RelativeLayout)window.findViewById(R.id.returnRelativeLayout);
		RelativeLayout cancelRelativeLayout = (RelativeLayout)window.findViewById(R.id.cancelRelativeLayout);
		RelativeLayout okRelativeLayout = (RelativeLayout)window.findViewById(R.id.okRelativeLayout);
		returnRelativeLayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				alertDialog.cancel();
			}
		});
		cancelRelativeLayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				alertDialog.cancel();
			}
		});
		okRelativeLayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub		
				alertDialog.cancel();
				gotoNextPart();
			}
		});
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
					initLevel(getResources().getString(R.string.turn_image_retry));
				} else {
					findProgressBar.setProgress(time);
					mHandler.postDelayed(this, TOTAL_INTERVAL);
				}
			}
		};
		mHandler.postDelayed(timerRunnable, TOTAL_INTERVAL);
	}
	
	private void initLevel(String text) {		
		imageCover = imageList.get(currentLevel);
		guanTextView.setText(guanNameList[currentLevel]);
		time = (maxtime - currentLevel) * progressMaxZeng;
		int timeText = maxtime - currentLevel;
		timeTextView.setText(timeText + "s");
    	findProgressBar.setMax(time);		
		turnImageTextView.setText(text);
		turnImageTextView.setVisibility(View.VISIBLE);
		processRelativeLayout.setVisibility(View.GONE);
		turnImageTextView.setTextColor(ColorUtils.getCommonGreen());
		turnImageTextView.setClickable(true);
		totalCount = 0;
		isTurn = false;
		numberList.clear();
		findist.clear();
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
		String defaultCover = defaultImageCover[currentLevel];
    	for (int i = 0; i < numberList.size(); i++) {
			FindEntity findEntity = new FindEntity();
			findEntity.setCover(imageCover[numberList.get(i)]);
			findEntity.setDefaultCover(defaultCover);
			findEntity.setIsImageShow(false);
			findist.add(findEntity);
		}
    	findAdapter = new FindAdapter(getApplicationContext(), mHandler, findist, findGridView, true);
    	findGridView.setAdapter(findAdapter);
	}
	
//	private void setLevel(String text) {
//		imageCover = imageList.get(currentLevel);
//		time = (maxtime - currentLevel) * progressMaxZeng;
//    	findProgressBar.setMax(time);		
//		initLevel(text); 
//	}
	
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        mSoundPlay = new SoundPlay(this, SoundPlay.GAME_PART1);
        mSoundPlay.startPlay();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        if (mSoundPlay != null) {
            mSoundPlay.stopPlay();
            mSoundPlay = null;
        }
        super.onPause();
    }
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
