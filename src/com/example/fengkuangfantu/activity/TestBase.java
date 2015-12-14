package com.example.fengkuangfantu.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.fengkuangfantu.R;

public class TestBase extends BaseActivity{
	
private static final String TAG = "TestBase";
	
    protected boolean mAutoFlag = false;
    protected boolean mTpSleep = false;
    protected String mTestId = "";
    protected String mTestType = "auto_test";

    private Button mBtnSuccess;
    private Button mBtnFail;
    private Button mBtnExit;		
	
    protected static final int SUCCESS_BUTTON_ID = 0x6f010101;
    protected static final int FAIL_BUTTON_ID = 0x6f010102;
    protected static final int EXIT_BUTTON_ID = 0x6f010103;	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);		


		//mAutoFlag = bundle.getBoolean("autoFlag", mAutoFlag);
		mAutoFlag = getIntent().getBooleanExtra("autoFlag",false);
		//cancel home key
		//this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_HOMEKEY_DISPATCHED);
    }

    @Override
    protected void onResume() {
	super.onResume();
    }

    @Override
    protected void onStop() {
	super.onStop();		

        if (!mAutoFlag && !mTpSleep) {
            finish();
        }
    }
	
    @Override
    public void onBackPressed() {
	//super.onBackPressed();
	if (!mAutoFlag) {
            finish();
        }
    }
	
    @Override
    protected void onDestroy() {
	super.onDestroy();

    }

    protected void showTestResultButton(ViewGroup parentLinearLayout){

	WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        int screenWidth = display.getWidth();
        int screenHeight = display.getHeight();
        int btnHeight = screenHeight / 11;
		
	LinearLayout linearLayout = new LinearLayout(this);     
	linearLayout.setGravity(Gravity.BOTTOM);  
	linearLayout.setOrientation(android.widget.LinearLayout.VERTICAL);
	LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.FILL_PARENT  
                , ViewGroup.LayoutParams.FILL_PARENT);  

	LinearLayout.LayoutParams bnParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT  
                , ViewGroup.LayoutParams.WRAP_CONTENT,1);  
		
	LinearLayout firstLinearLayout = new LinearLayout(this);     
	firstLinearLayout.setOrientation(android.widget.LinearLayout.HORIZONTAL);
		
	mBtnSuccess = new Button(this);
	mBtnSuccess.setId(SUCCESS_BUTTON_ID);
	mBtnSuccess.setText(R.string.result_success);
	mBtnSuccess.setTextColor(Color.RED);
	mBtnSuccess.setHeight(btnHeight);
	mBtnSuccess.setOnClickListener(mResultButtonListener);
	firstLinearLayout.addView(mBtnSuccess, bnParams);  
        
	LinearLayout nextLinearLayout = new LinearLayout(this);     
	firstLinearLayout.setOrientation(android.widget.LinearLayout.HORIZONTAL); 

	mBtnFail = new Button(this);
	mBtnFail.setId(FAIL_BUTTON_ID);
	mBtnFail.setText(R.string.result_fail);
	mBtnFail.setTextColor(Color.BLACK);
	mBtnFail.setHeight(btnHeight);
	mBtnFail.setOnClickListener(mResultButtonListener);
	nextLinearLayout.addView(mBtnFail, bnParams); 
	
	mBtnExit = new Button(this); 
	mBtnExit.setId(EXIT_BUTTON_ID);
	mBtnExit.setText(R.string.result_exit);
	mBtnExit.setTextColor(Color.BLACK);
	mBtnExit.setHeight(btnHeight);
	mBtnExit.setOnClickListener(mResultButtonListener);
	nextLinearLayout.addView(mBtnExit, bnParams);
		
	linearLayout.addView(firstLinearLayout);
	linearLayout.addView(nextLinearLayout);
		
	parentLinearLayout.addView(linearLayout, layoutParams);
    }
	
    private Button.OnClickListener mResultButtonListener = new Button.OnClickListener(){

	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId() == SUCCESS_BUTTON_ID) {
			OnSuccess();
			writeAutoResult(true);
		} else if (v.getId() == FAIL_BUTTON_ID) {			
			OnFailed();
			writeAutoResult(false);
		} else if (v.getId() == EXIT_BUTTON_ID) {
			OnExit();
			setResult(Utils.RESULT_EXIT);	
		}
		finish();
	}		
    };
	
    protected void writeAutoResult(boolean result) {
    	if (result) {
			setResult(Utils.RESULT_OK);
		}else{
			setResult(Utils.RESULT_FAIL);	
		}
		TestBase.this.finish();
    }
	
    protected void setSuccessButtonEnable(boolean enable) {
	mBtnSuccess.setEnabled(enable);
    }
	
    protected void setFailButtonEnable(boolean enable) {
	mBtnFail.setEnabled(enable);
    }

    protected void setExitButtonEnable(boolean enable) {
	mBtnExit.setEnabled(enable);
    }

    protected void setSuccessButtonVisible(boolean visible) {
    	if (visible) {
			mBtnSuccess.setVisibility(View.VISIBLE);
    	} else {
    		mBtnSuccess.setVisibility(View.INVISIBLE);
    	}
    }
	
    protected void OnSuccess() {
	// TODO Auto-generated method stub
		
    }	

    protected void OnFailed() {
	// TODO Auto-generated method stub
		
    }
	
    protected void OnExit() {
	// TODO Auto-generated method stub
		
    }
	

    protected void showTestResultDialog(String summary) {
	String str = summary + getString(R.string.auto_dialog_message);
	
	new AlertDialog.Builder(this).setTitle(R.string.dialog_title)
		.setCancelable(false)
		.setMessage(str)
		.setPositiveButton(R.string.result_success,
			new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int which) {
						
					OnSuccess();
					setResult(Utils.RESULT_OK);
					writeAutoResult(true);
					TestBase.this.finish();								
				}
			})
		.setNegativeButton(R.string.result_fail,
			new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int which) {
					OnFailed();	
					setResult(Utils.RESULT_FAIL);
					writeAutoResult(false);	
					TestBase.this.finish();	
				}
			})
		.setNeutralButton(R.string.result_exit, 
			new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int which) {
					OnExit();
					setResult(Utils.RESULT_EXIT);	
                    TestBase.this.finish();
				}
            })
		.show();
		
   }

}
