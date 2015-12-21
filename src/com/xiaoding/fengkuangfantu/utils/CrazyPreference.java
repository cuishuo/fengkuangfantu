package com.xiaoding.fengkuangfantu.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class CrazyPreference {

    private Context mContext;
    SharedPreferences settings;
    public static final String SETTING_PREFERENCE = "crazy_preference";
    public static final String RECORD_PART1 = "record_part1";
    public static final String RECORD_PART2 = "record_part2";

    public CrazyPreference(Context context) {
        mContext = context;
        settings = mContext.getSharedPreferences(SETTING_PREFERENCE, 0);
    }

    public int getValue(String key) {
        int value = settings.getInt(key, 0);
        return value;
    }

    public void setValue(String key, int value) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        editor.commit();
    }
}
