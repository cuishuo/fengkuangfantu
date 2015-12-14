package com.example.fengkuangfantu.activity;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View.MeasureSpec;
import android.widget.RelativeLayout;

public class PreviewFrameLayout extends RelativeLayout {
	
	  public interface OnSizeChangedListener {
	        public void onSizeChanged();
	    }

	    private double mAspectRatio;

	    public PreviewFrameLayout(Context context, AttributeSet attrs) {
	        super(context, attrs);
	        setAspectRatio(4.0 / 3.0);
	    }

	    public void setAspectRatio(double ratio) {
	        if (ratio <= 0.0) throw new IllegalArgumentException();

	        if (((Activity) getContext()).getRequestedOrientation()
	                == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
	            ratio = 1 / ratio;
	        }

	        if (mAspectRatio != ratio) {
	            mAspectRatio = ratio;
	            requestLayout();
	        }
	    }


	    @Override
	    protected void onMeasure(int widthSpec, int heightSpec) {
	        int previewWidth = MeasureSpec.getSize(widthSpec);
	        int previewHeight = MeasureSpec.getSize(heightSpec);

	        int mPaddingLeft = 0;
			int mPaddingRight = 0;
			// Get the padding of the border background.
	        int hPadding = mPaddingLeft + mPaddingRight;
	        int mPaddingTop = 0;
			int mPaddingBottom = 0;
			int vPadding = mPaddingTop + mPaddingBottom;

	        // Resize the preview frame with correct aspect ratio.
	        previewWidth -= hPadding;
	        previewHeight -= vPadding;
	        if (previewWidth > previewHeight * mAspectRatio) {
	            previewWidth = (int) (previewHeight * mAspectRatio + .5);
	        } else {
	            previewHeight = (int) (previewWidth / mAspectRatio + .5);
	        }

	        // Add the padding of the border.
	        previewWidth += hPadding;
	        previewHeight += vPadding;
	        
			Log.d("KTCIT"," previewWidth " + previewWidth);
			Log.d("KTCIT"," previewHeight " + previewHeight);
	        // Ask children to follow the new preview dimension.
	        super.onMeasure(MeasureSpec.makeMeasureSpec(previewWidth, MeasureSpec.EXACTLY),
	                MeasureSpec.makeMeasureSpec(previewHeight, MeasureSpec.EXACTLY));
	    }

}
