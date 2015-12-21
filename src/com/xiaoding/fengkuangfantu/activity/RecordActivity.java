package com.xiaoding.fengkuangfantu.activity;

import java.util.ArrayList;
import java.util.Arrays;

import com.xiaoding.fengkuangfantu.R;
import com.xiaoding.fengkuangfantu.adapter.RecordPartOneAdapter;
import com.xiaoding.fengkuangfantu.utils.CrazyPreference;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.TextView;

public class RecordActivity extends Activity{

    private GridView part1GridView;
    private TextView part2ValueTextView;
    private ArrayList<String> nameList;
    private String[] guanname;
    private RecordPartOneAdapter mRecordPartOneAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        part1GridView = (GridView) findViewById(R.id.part1GridView);
        part2ValueTextView = (TextView) findViewById(R.id.part2ValueTextView);
        initViews();
    }

    private void initViews() {
        // TODO Auto-generated method stub
        int part1value = new CrazyPreference(this).getValue(CrazyPreference.RECORD_PART1);
        guanname = getResources().getStringArray(R.array.record_guan_name);
        nameList = new ArrayList<String>(Arrays.asList(guanname));
        mRecordPartOneAdapter = new RecordPartOneAdapter(getApplicationContext(), nameList, part1GridView, part1value);
        part1GridView.setAdapter(mRecordPartOneAdapter);
        
        int part2value = new CrazyPreference(this).getValue(CrazyPreference.RECORD_PART2);
        part2ValueTextView.setText(""+part2value);
    }

}
