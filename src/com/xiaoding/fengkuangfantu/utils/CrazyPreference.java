package com.xiaoding.fengkuangfantu.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class CrazyPreference {

    private Context mContext;
    SharedPreferences settings;
    public static final String SETTING_PREFERENCE = "setting_preference";
    public static final String SETTING_SOUND = "setting_sound";

    public CrazyPreference(Context context) {
        mContext = context;
        settings = mContext.getSharedPreferences(SETTING_SOUND, 0);
    }

    public boolean getValue(String key) {
        boolean value = settings.getBoolean(key, false); 
        return value;
    }

    public void setValue(String key, boolean value) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }
}
