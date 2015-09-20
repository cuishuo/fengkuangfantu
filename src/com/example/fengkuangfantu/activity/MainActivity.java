package com.example.fengkuangfantu.activity;

import java.util.ArrayList;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

import com.example.fengkuangfantu.R;
import com.example.fengkuangfantu.adapter.FindAdapter;
import com.example.fengkuangfantu.service.entity.FindEntity;


public class MainActivity extends BaseActivity {
	
	private ArrayList<FindEntity> findist;
	private ArrayList<Integer> numberList;
	private FindAdapter findAdapter;
	private GridView findGridView;
	private String[] imageCover1;
	private TextView turnImageTextView;
	private int random = 0;
	private int number = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findGridView = (GridView) findViewById(R.id.findGridView);
        turnImageTextView = (TextView) findViewById(R.id.turnImageTextView);
        findist = new ArrayList<FindEntity>();
        numberList = new ArrayList<Integer>();
        initViews();
        initClicks();
    }
    
    private void initViews() {
    	imageCover1 = getResources().getStringArray(R.array.image_1);
    	while (numberList.size() < number * 2) {
    		random = (int)(Math.random() * 4);
    		if (numberList.contains(random)) {
    			int count = 0;
    			for (int i = 0; i < numberList.size(); i++) {
					if (numberList.get(i) == random) {
						count ++;
					}
				}
    			if (count == 1) {
    				numberList.add(random);
				}
			} else {
				numberList.add(random);
			}
		}   	
    	for (int i = 0; i < numberList.size(); i++) {
			FindEntity findEntity = new FindEntity();
			findEntity.setCover(imageCover1[numberList.get(i)]);
			findEntity.setIsImageShow(false);
			findist.add(findEntity);
		}
    	findAdapter = new FindAdapter(getApplicationContext(), mHandler, findist, findGridView, true);
    	findGridView.setAdapter(findAdapter);
	}

    private void initClicks() {
    	turnImageTextView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				findAdapter.setShow(true);
				findAdapter.notifyDataSetChanged();
				turnImageTextView.setTextColor(Color.parseColor("#828282"));
				turnImageTextView.setClickable(false);
				mHandler.postDelayed(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
//						findAdapter.setShowImage(true);
						for (int i = 0; i < findist.size(); i++) {
							findist.get(i).setIsImageShow(true);
						}
						findAdapter.setShow(false);
						findAdapter.setIsFirst(false);
						findAdapter.notifyDataSetChanged();
					}
				}, 2000);
			}
		});
    	
    	findGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
//				if (turnImageTextView.get) {
//					
//				}
				findAdapter.setShow(true);
				findist.get(position).setIsImageShow(false);
				findAdapter.setClickPosition(position);
				findAdapter.notifyDataSetChanged();
			}
		});
	}
}
