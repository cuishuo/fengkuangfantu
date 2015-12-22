package com.xiaoding.fengkuangfantu.activity;


import com.xiaoding.fengkuangfantu.R;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.util.Log;
import android.view.Window;

public class SettingsActivity extends PreferenceActivity implements OnPreferenceClickListener {

    private SwitchPreference mSoundPreference;
    private Preference mfeedbacePreference;
    private Preference mevaluatePreference;
    private Preference mupdateVersionPreference;
    private Preference maboutUsPreference;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);             
        addPreferencesFromResource(R.xml.settings);  
        setContentView(R.layout.setting);
        initPreferences();
    }

    private void initPreferences() {
        mSoundPreference = (SwitchPreference) findPreference("soundsettings");
        mfeedbacePreference = (Preference) findPreference("feedback");
        mevaluatePreference = (Preference) findPreference("settings_evaluate");
        mupdateVersionPreference = (Preference) findPreference("settings_update_version");
        maboutUsPreference = (Preference) findPreference("about_us");
    }

    @Override
    public boolean onPreferenceClick(Preference arg0) {
        // TODO Auto-generated method stub
        return false;
    }

}
