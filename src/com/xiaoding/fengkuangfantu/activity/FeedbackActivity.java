package com.xiaoding.fengkuangfantu.activity;

import com.xiaoding.fengkuangfantu.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class FeedbackActivity extends Activity {

    private TextView titleTextView;
    private ImageView returnImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_feedback);
        titleTextView = (TextView) findViewById(R.id.titleTextView);
        titleTextView.setText(getResources().getString(R.string.settings_feedback));
        returnImageView = (ImageView) findViewById(R.id.iv_back);
        returnImageView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }
}
