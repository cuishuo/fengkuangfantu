package com.xiaoding.fengkuangfantu.adapter;

import java.util.ArrayList;

import com.xiaoding.fengkuangfantu.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class RecordPartOneAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> nameList;
    private GridView gridView;
    private ViewHolder holder;
    private int record;

    public RecordPartOneAdapter(Context context, ArrayList<String> nameList, GridView gridView, int record) {
        this.context = context;
        this.nameList = nameList;
        this.gridView = gridView;
        this.record = record;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return nameList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        holder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.record_grid_item, null);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.part1TextView = (TextView) convertView.findViewById(R.id.part1TextView);
        String name = nameList.get(position);
        if (position + 1 <= record) {
            holder.part1TextView.setTextColor(context.getResources().getColor(R.color.hotel_top_bar));
        } else {
            holder.part1TextView.setTextColor(context.getResources().getColor(R.color.greytext));
        }
        holder.part1TextView.setText(name);
        return convertView;
    }

    private class ViewHolder {
        public TextView part1TextView;
    }
}
