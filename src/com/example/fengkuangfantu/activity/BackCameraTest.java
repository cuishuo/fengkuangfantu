package com.example.fengkuangfantu.activity;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.fengkuangfantu.R;

public class BackCameraTest extends TestBase implements SurfaceHolder.Callback{
	
	private Camera mCamera01;
	private static final String TAG = "KTCIT-BackCameraTest";
	private SurfaceView mSurfaceView01;
	private SurfaceHolder mSurfaceHolder01;
	private static final String ANTIBANDING_50HZ = "50hz";
	private Camera.Parameters parameters;
	private Button button_autoFocus,button_takePicture;
	private AutoFocusCallback mAutoFocusCallback = new AutoFocusCallback();
	private volatile boolean isRunning = false;
//	protected boolean mAutoFlag = false;

    private PreviewFrameLayout mPreviewFrameLayout;
	//add auto test
    private LinearLayout mLinearLayout;
	
	private final class AutoFocusCallback implements android.hardware.Camera.AutoFocusCallback {
		public void onAutoFocus(boolean success, Camera camera){
			String res = getString(R.string.auto_focus);
			if(success){
				res += getString(R.string.dialog_success);
				button_autoFocus.setText(res);
				button_takePicture.setEnabled(true);
			}else{	
				res += getString(R.string.dialog_error)+getString(R.string.reStart);
				button_autoFocus.setText(res);
		 	}
			button_autoFocus.setEnabled(true);
    		}
	};	
	private ShutterCallback shutterCallback = new ShutterCallback(){
		public void onShutter(){
		}
	};

	private PictureCallback jpegCallback = new PictureCallback(){
		public void onPictureTaken(byte[] data, Camera camera){
			try {
				Thread.sleep(500);
				mCamera01.startPreview();
				button_takePicture.setEnabled(true);
				isRunning = false;
				if (mAutoFlag)
					setSuccessButtonEnable(true);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	};

	protected void onResume() {
		super.onResume();
	}

	protected void onDestroy() {
		super.onDestroy();
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);	
		setContentView(R.layout.back_camera_test);
		if (true)
			Log.i(TAG, "Enter BackCameraTest");
		button_autoFocus = (Button)findViewById(R.id.autoFocus);	
		button_takePicture = (Button)findViewById(R.id.takePicture);	
		button_autoFocus.setVisibility(View.VISIBLE);
//		button_takePicture.setEnabled(true);
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		mSurfaceView01 = (SurfaceView) findViewById(R.id.mSurfaceView1);
		//add auto test
		if (mAutoFlag){
			mLinearLayout = (LinearLayout) this.findViewById(R.id.linearLayout_backcamera);
			showTestResultButton(mLinearLayout);
			setSuccessButtonEnable(false);
		}
		try { 
		mCamera01 = Camera.open();
		parameters = mCamera01.getParameters();
		} catch (Exception ex) {
			button_autoFocus.setEnabled(false);
			button_takePicture.setEnabled(false);
			Log.e(TAG,ex.toString());
			ex.printStackTrace();
		}
		isRunning = false;

        mPreviewFrameLayout = (PreviewFrameLayout) findViewById(R.id.frame);
		try {
			mSurfaceHolder01 = mSurfaceView01.getHolder();
			mSurfaceHolder01.addCallback(BackCameraTest.this);
			mSurfaceHolder01.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		} catch (Exception e) {
			Log.i(TAG,e.toString());
			e.printStackTrace();
		}
		mPreviewFrameLayout.setAspectRatio((double) 640/480);
		button_autoFocus.setOnClickListener(new Button.OnClickListener() {	
			public void onClick(View arg0) {
				mCamera01.startPreview();
                if(true) {
					try { 
					mCamera01.autoFocus(mAutoFocusCallback);
					button_autoFocus.setEnabled(false);
					} catch (RuntimeException ex) {
                        ex.printStackTrace();
                        Log.e(TAG, ex.toString());
					}
                }
                else {
					if (true)
						Log.e(TAG, "Camera preview is not enabled");
                }
			}
		});

		button_takePicture.setOnClickListener(new Button.OnClickListener() {	
			public void onClick(View arg0) {
					if(!isRunning){
						isRunning = true;
						button_takePicture.setEnabled(false);
						try { 
						mCamera01.takePicture(shutterCallback, null, jpegCallback);
						} catch (NullPointerException ex) {

						}
						button_takePicture.setText(R.string.re_take_picture);
					}
			}
		});
	}

	public void surfaceChanged(SurfaceHolder surfaceholder, int format, int w,
			int h) {
		if (true)
			Log.i(TAG, "Surface Changed");
			Log.i(TAG, "<w=" + w + ",h=" + h + ">");
		try{
            parameters.set("antibanding",ANTIBANDING_50HZ);
			parameters.setPictureSize(640,480);
			parameters.setPreviewSize(640,480);
			parameters.setPictureFormat(PixelFormat.JPEG);
			mCamera01.setParameters(parameters);
			mCamera01.setDisplayOrientation(90);
			mCamera01.startPreview();
		}catch(Exception e){
			Log.i(TAG,e.toString());
			e.printStackTrace();
		}
	}

	public void surfaceCreated(SurfaceHolder surfaceholder) {
		if (true)
			Log.i(TAG, "Surface Created");
		try{  
			//mCamera01 = Camera.open();
			mCamera01.setPreviewDisplay(mSurfaceHolder01);
            parameters.set("antibanding",ANTIBANDING_50HZ);
		}catch(Exception e){
			Log.i(TAG,e.toString());
			e.printStackTrace();
		}

	}

	public void surfaceDestroyed(SurfaceHolder surfaceholder) {
		if (true)
			Log.i(TAG, "Surface Destroyed");
		try {
			if (mCamera01 != null) {
				mCamera01.stopPreview();
				mCamera01.release();
				mCamera01 = null;
			}
		} catch (Exception e) {
			Log.i(TAG,e.toString());
			e.printStackTrace();
		}
	}

	protected void onPause() {
		super.onPause();
	}
	
    protected void OnSuccess() {
		try {
			if (true)
				Log.i(TAG, "back camera release");
			mCamera01.stopPreview();
			mCamera01.release();
			mCamera01 = null;
		} catch (Exception e) {
			Log.i(TAG,e.toString());
		}
    }	

	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	protected void OnFailed() {
		try {
			if (true)
				Log.i(TAG, "back camera release");
			mCamera01.stopPreview();
			mCamera01.release();
			mCamera01 = null;
		} catch (Exception e) {
			Log.i(TAG,e.toString());
		}
    }
	
    protected void OnExit() {
		try {
			if (true)
				Log.i(TAG, "back camera release");
			mCamera01.stopPreview();
			mCamera01.release();
			mCamera01 = null;
		} catch (Exception e) {
			Log.i(TAG,e.toString());
		}
    }

}
