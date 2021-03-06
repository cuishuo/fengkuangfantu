package com.xiaoding.fengkuangfantu.activity;

import java.io.IOException;
import java.util.ArrayList;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
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

import com.xiaoding.fengkuangfantu.R;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.QQShareContent;
import com.umeng.socialize.media.QZoneShareContent;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;
import com.umeng.socialize.weixin.media.CircleShareContent;
import com.umeng.socialize.weixin.media.WeiXinShareContent;
import com.xiaoding.fengkuangfantu.adapter.FindAdapter;
import com.xiaoding.fengkuangfantu.adapter.MainImageAdapter;
import com.xiaoding.fengkuangfantu.service.entity.FindEntity;
import com.xiaoding.fengkuangfantu.utils.DisplayNextView;
import com.xiaoding.fengkuangfantu.utils.Flip3dAnimation;
import com.xiaoding.fengkuangfantu.utils.ProcessImageUtil;
import com.xiaoding.fengkuangfantu.utils.SoundPlayer;
import com.xiaoding.fengkuangfantu.utils.StringUtils;
import com.xiaoding.fengkuangfantu.utils.ToastUtil;

public class MainActivity extends BaseActivity {

    private Animation animation;
    private ArrayList<FindEntity> findist;
    private MainImageAdapter mainImageAdapter;
    private FrameLayout coverFramlayout;
    // private GridView mainImageGridView;
    private ImageButton recordImagebutton;
    private ImageButton aboutImagebutton;
    private ImageButton settingImagebutton;
    private ImageView clickImageView;
    private ImageView defaultImageView;
    private Runnable timerRunnable;
    private TextView partTextView1;
    private TextView partTextView2;
    private final int REPET_INTERVAL = 3000;
    private final int RETRY_INTERVAL = 50;
    private int time = RETRY_INTERVAL;
    private String TAG = "MainActivity";
    private final UMSocialService mController = UMServiceFactory
            .getUMSocialService("com.umeng.share");
    private SoundPlayer mSoundPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // mainImageGridView = (GridView) findViewById(R.id.mainImageGridView);
        recordImagebutton = (ImageButton) findViewById(R.id.recordImagebutton);
        aboutImagebutton = (ImageButton) findViewById(R.id.aboutImagebutton);
        settingImagebutton = (ImageButton) findViewById(R.id.settingImagebutton);
        clickImageView = (ImageView) findViewById(R.id.clickImageView);
        defaultImageView = (ImageView) findViewById(R.id.defaultImageView);
        partTextView1 = (TextView) findViewById(R.id.partTextView1);
        partTextView2 = (TextView) findViewById(R.id.partTextView2);
        coverFramlayout = (FrameLayout) findViewById(R.id.mainFramlayout);
        findist = new ArrayList<FindEntity>();
        initViews();
        initClicks();
//        tartClick();
        addWXPlatform();
        addQQQZonePlatform();
    }

    private void initViews() {
        for (int i = 0; i < 9; i++) {
            FindEntity findEntity = new FindEntity();
            findEntity.setCover("main_click_image");
            findEntity.setIsImageShow(false);
            findist.add(findEntity);
        }
        // mainImageAdapter = new MainImageAdapter(getApplicationContext(),
        // mHandler, findist, mainImageGridView, true);
        // mainImageGridView.setAdapter(mainImageAdapter);
    }

    private void initClicks() {
        partTextView1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),
                        FindImageActivity.class);
                startActivity(intent);
            }
        });
        partTextView2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),
                        ChooseImageActivity.class);
                startActivity(intent);
            }
        });

        recordImagebutton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), RecordActivity.class);
                startActivity(intent);
            }
        });

        settingImagebutton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
            }
        });

        aboutImagebutton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // 设置朋友圈分享的内容
            	initShareData();
                mController.getConfig().setPlatforms(SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN,
                		SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE);
                mController.openShare(MainActivity.this, false);
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
                            // coverFramlayout = (FrameLayout) mainImageGridView
                            // .getChildAt(4).findViewById(
                            // R.id.coverFramlayout);
                            // defaultImageView = (ImageView) mainImageGridView
                            // .getChildAt(4).findViewById(
                            // R.id.defaultImageView);
                            showImage(coverFramlayout, defaultImageView, false);
                            if (animation != null) {
                                clickImageView.clearAnimation();
                                animation = null;
                            }
                            clickImageView.setVisibility(View.INVISIBLE);
                            mHandler.postDelayed(new Runnable() {

                                @Override
                                public void run() {
                                    // TODO Auto-generated method stub
                                    showDefault(coverFramlayout,
                                            defaultImageView, true);
                                }
                            }, 1500);

                        }
                    });
                    // clickImageView.startAnimation(animation);
                    mHandler.postDelayed(this, REPET_INTERVAL);
                }
            }
        };
        mHandler.postDelayed(timerRunnable, REPET_INTERVAL);
    }

    private void addWXPlatform() {
        // 注意：在微信授权的时候，必须传递appSecret
        // wx967daebe835fbeac是你在微信开发平台注册应用的AppID, 这里需要替换成你注册的AppID
        String appId = "wx3868255817265cbd";
        String appSecret = "5e1814cef04e3fe3f33a10686d200a50";
        // 添加微信平台
        UMWXHandler wxHandler = new UMWXHandler(MainActivity.this, appId,
                appSecret);
        wxHandler.addToSocialSDK();

        // 支持微信朋友圈
        UMWXHandler wxCircleHandler = new UMWXHandler(MainActivity.this, appId,
                appSecret);
        wxCircleHandler.setToCircle(true);
        wxCircleHandler.addToSocialSDK();
    }
    
    private void addQQQZonePlatform() {
        String appId = "1104970571";
        String appKey = "LZ9Iz539wfTroarQ";
        // 添加QQ支持, 并且设置QQ分享内容的target url
        UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(MainActivity.this,
                appId, appKey);
//        qqSsoHandler.setTargetUrl("http://www.umeng.com/social");
        qqSsoHandler.addToSocialSDK();

        // 添加QZone平台
        QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(MainActivity.this, appId, appKey);
        qZoneSsoHandler.addToSocialSDK();
    }
    
    private void initShareData() {
		View view = getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap b1 = view.getDrawingCache();        
        Rect frame = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        int width = getWindowManager().getDefaultDisplay().getWidth();
        int height = getWindowManager().getDefaultDisplay().getHeight();        
        Bitmap bitmap = Bitmap.createBitmap(b1, 0, statusBarHeight, width, height
        		- statusBarHeight);        		
        byte[] imageDataWX = null;
		if (bitmap != null) {
			imageDataWX = StringUtils.bmpToByteArray(bitmap, true);
		}
        view.destroyDrawingCache();   
		UMImage urlImage = new UMImage(MainActivity.this, imageDataWX);
        CircleShareContent circleMedia = new CircleShareContent();
        circleMedia.setShareMedia(urlImage);
        mController.setShareMedia(circleMedia);
        
        WeiXinShareContent weixinContent = new WeiXinShareContent();
        weixinContent.setShareMedia(urlImage);
        mController.setShareMedia(weixinContent);
        
        // 设置QQ空间分享内容
        QZoneShareContent qzone = new QZoneShareContent();
//        qzone.setShareContent("share test");
//        qzone.setTargetUrl("http://www.umeng.com");
        qzone.setTitle("疯狂翻图");
        qzone.setShareMedia(urlImage);
        // qzone.setShareMedia(uMusic);
        mController.setShareMedia(qzone);

//        video.setThumb(new UMImage(getActivity(), BitmapFactory.decodeResource(
//                getResources(), R.drawable.device)));

        QQShareContent qqShareContent = new QQShareContent();
//        qqShareContent.setShareContent("来自友盟社会化组件（SDK）让移动应用快速整合社交分享功能 -- QQ");
        qqShareContent.setTitle("疯狂翻图");
        qqShareContent.setShareMedia(urlImage);
//        qqShareContent.setTargetUrl("http://www.umeng.com/social");
        mController.setShareMedia(qqShareContent);
	}

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        SharedPreferences settingsPfs = PreferenceManager
                .getDefaultSharedPreferences(this);
        boolean soundsettings = settingsPfs.getBoolean("soundsettings", false);
        if (soundsettings) {
            mSoundPlayer = new SoundPlayer(this, SoundPlayer.GAME_MAIN);
            mSoundPlayer.startPlay();
        }
        if (mHandler != null) {
//        	mHandler.postDelayed(timerRunnable, REPET_INTERVAL);
        	tartClick();
		}
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        if (mSoundPlayer != null) {
            mSoundPlayer.stopPlay();
            mSoundPlayer = null;
        }
        if (mHandler != null) {
        	animation.cancel();
        	showDefault(coverFramlayout, defaultImageView, true);
        	mHandler.removeCallbacks(timerRunnable);        	
        	time = RETRY_INTERVAL;
		}
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

}
