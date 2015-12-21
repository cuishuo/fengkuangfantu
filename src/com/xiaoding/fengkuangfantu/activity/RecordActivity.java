package com.xiaoding.fengkuangfantu.activity;

import java.util.ArrayList;
import java.util.Arrays;

import com.xiaoding.fengkuangfantu.R;
import com.xiaoding.fengkuangfantu.adapter.RecordPartOneAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

public class RecordActivity extends Activity{

    private GridView part1GridView;
    private ArrayList<String> nameList;
    private String[] guanname;
    private RecordPartOneAdapter mRecordPartOneAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        part1GridView = (GridView) findViewById(R.id.part1GridView);
        initViews();
    }

    private void initViews() {
        // TODO Auto-generated method stub
        guanname = getResources().getStringArray(R.array.record_guan_name);
        nameList = new ArrayList<String>(Arrays.asList(guanname));
        mRecordPartOneAdapter = new RecordPartOneAdapter(getApplicationContext(), nameList, part1GridView);
        part1GridView.setAdapter(mRecordPartOneAdapter);
    }

}
