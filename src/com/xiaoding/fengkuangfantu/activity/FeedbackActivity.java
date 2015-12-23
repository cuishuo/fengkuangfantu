package com.xiaoding.fengkuangfantu.activity;

import com.xiaoding.fengkuangfantu.R;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class FeedbackActivity extends Activity {

    private TextView titleTextView;
    private ImageView returnImageView;
    private EditText contentEditText;
    private EditText contactEditText;
    private Button mButton;
    private String contentString;
    private String contactString;

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
        contentEditText = (EditText) findViewById(R.id.feedback_content);
        contactEditText = (EditText) findViewById(R.id.feedback_contact);
        mButton = (Button) findViewById(R.id.send);
        mButton.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SendMessageToUs();
            }
        });
    }

    private void SendMessageToUs() {
        // TODO Auto-generated method stub
        contentString = contentEditText.getText().toString();
        contactString = contactEditText.getText().toString();
        if (!TextUtils.isEmpty(contentString)) {
            contentEditText.setText("");
        }
        if (!TextUtils.isEmpty(contactString)) {
            contactEditText.setText("");
        }
    }
}
