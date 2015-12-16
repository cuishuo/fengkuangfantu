package com.xiaoding.fengkuangfantu.utils;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

/*
 * 显示View的动画
 */

public class DisplayNextView implements AnimationListener {
	private boolean mCurrentView;
	View view1;
	View view2;

	public DisplayNextView(boolean currentView, View image1,
			View image2) {
		mCurrentView = currentView;
		this.view1 = image1;
		this.view2 = image2;
	}

	@Override
	public void onAnimationStart(Animation animation) {
		
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		view2.post(new SwapViews(mCurrentView, view1, view2));
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		
	}
	
}
