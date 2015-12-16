package com.xiaoding.fengkuangfantu.activity;

import com.xiaoding.fengkuangfantu.utils.DisplayNextView;
import com.xiaoding.fengkuangfantu.utils.Flip3dAnimation;

import android.app.Activity;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class BaseActivity extends Activity {
	
	public static Handler mHandler = new Handler();
	protected final int THUMB_SIZE = 200;
	
	protected void showImage(FrameLayout coverFramlayout, ImageView defaultImageView, boolean isImageShow) {
		coverFramlayout.setVisibility(View.INVISIBLE);
		defaultImageView.setVisibility(View.INVISIBLE);
		coverFramlayout.setAnimationCacheEnabled(true);
		coverFramlayout.setDrawingCacheEnabled(true);
		applyRotation(coverFramlayout, defaultImageView, 0, 90, isImageShow);
		coverFramlayout.setAnimationCacheEnabled(false);
		coverFramlayout.setDrawingCacheEnabled(false);
	}
	
	protected void showDefault(FrameLayout coverFramlayout, ImageView defaultImageView, boolean isImageShow) {
		defaultImageView.setVisibility(View.INVISIBLE);
		coverFramlayout.setVisibility(View.INVISIBLE);
		coverFramlayout.setAnimationCacheEnabled(true);
		coverFramlayout.setDrawingCacheEnabled(true);
		applyRotation(coverFramlayout, defaultImageView, 0, -90, isImageShow);
		coverFramlayout.setAnimationCacheEnabled(false);
		coverFramlayout.setDrawingCacheEnabled(false);
	}
	
	protected void applyRotation(FrameLayout coverFramlayout, ImageView defaultImageView, float start, float end, boolean isImageShow) {
		final float centerX = defaultImageView.getWidth() / 2.0f;
		final float centerY = defaultImageView.getHeight() / 2.0f;
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

}
