package com.xiaoding.fengkuangfantu.activity;


import com.xiaoding.fengkuangfantu.R;
import com.xiaoding.fengkuangfantu.utils.ToastUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class SettingsActivity extends PreferenceActivity implements OnPreferenceClickListener {

    private SwitchPreference mSoundPreference;
    private Preference mfeedbacePreference;
    private Preference mevaluatePreference;
    private Preference mupdateVersionPreference;
    private Preference maboutUsPreference;
    private TextView titleTextView;
    private ImageView returnImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        titleTextView = (TextView) findViewById(R.id.titleTextView);
        titleTextView.setText(getResources().getString(R.string.settings));
        returnImageView = (ImageView) findViewById(R.id.iv_back);
        addPreferencesFromResource(R.xml.settings);
        returnImageView.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
        initPreferences();
    }

    private void initPreferences() {
        mSoundPreference = (SwitchPreference) findPreference("soundsettings");
        mfeedbacePreference = (Preference) findPreference("feedback");
        mevaluatePreference = (Preference) findPreference("evaluate");
        mupdateVersionPreference = (Preference) findPreference("update_version");
        maboutUsPreference = (Preference) findPreference("about_us");
        mfeedbacePreference.setOnPreferenceClickListener(this);
        mevaluatePreference.setOnPreferenceClickListener(this);
        mupdateVersionPreference.setOnPreferenceClickListener(this);
        maboutUsPreference.setOnPreferenceClickListener(this);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        // TODO Auto-generated method stub
        if (preference == mfeedbacePreference) {
            Intent intent = new Intent(getApplication(), SettingsFeedbackActivity.class);
            startActivity(intent);
        }
        if (preference == mevaluatePreference) {
            
        }
        if (preference == mupdateVersionPreference) {
            ToastUtil.show(this, getResources().getString(R.string.settings_update_version_newest));
        }
        if (preference == maboutUsPreference) {
            
        }
        return false;
    }

}
