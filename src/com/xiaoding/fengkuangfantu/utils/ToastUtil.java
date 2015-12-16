/**
 * 
 */
package com.xiaoding.fengkuangfantu.utils;

import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.widget.Toast;

/*
 * 提示字符
 */

public class ToastUtil {

	public static void show(Context context, String info) {
		Toast.makeText(context, info, Toast.LENGTH_LONG).show();
	}

	public static void show(Context context, int info) {
		Toast.makeText(context, info, Toast.LENGTH_LONG).show();
	}
	
	public static void threadShow(final Context context, Handler handler, final String info) {
		handler.post(new Runnable() {
			
			@Override
			public void run() {
				Toast.makeText(context, info, Toast.LENGTH_LONG).show();
			}
		});
	}
	
	public static void threadShow(final Context context, Handler handler, final int info) {
		handler.post(new Runnable() {
			
			@Override
			public void run() {
				Toast.makeText(context, info, Toast.LENGTH_LONG).show();
			}
		});
	}
	
	public static void centerShow(final Context context, final int info) {
		Toast toast = Toast.makeText(context, info, Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}
	
	public static void centerShow(final Context context, Handler handler, final int info) {
		handler.post(new Runnable() {
			
			@Override
			public void run() {
				Toast toast = Toast.makeText(context, info, Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
			}
		});
	}
}
