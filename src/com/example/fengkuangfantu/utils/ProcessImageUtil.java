package com.example.fengkuangfantu.utils;

import android.graphics.Bitmap;

public class ProcessImageUtil {
	
	public static Bitmap cutThumb(Bitmap bitmap, int maxWidth, int maxHeight) {
		if (maxWidth<=0) {
			maxWidth=100;
		}
		if (maxHeight<=0) {
			maxHeight=100;
		}
		Bitmap thumbBmp = null;
		if (null == bitmap) {
			return null;
		}
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		if (w < maxWidth || h < maxHeight) {
			return bitmap;
		}
		float scale = 1.0f;
		if (w * maxHeight < h * maxWidth) {
			thumbBmp = Bitmap.createBitmap(bitmap, 0, (h-w*maxHeight/maxWidth)/2, w, w*maxHeight/maxWidth);
		} else {
			thumbBmp = Bitmap.createBitmap(bitmap, (w-h*maxWidth/maxHeight)/2, 0, h*maxWidth/maxHeight, h);
		}
		return Bitmap.createScaledBitmap(thumbBmp, maxWidth, maxHeight, true);
	}

}
