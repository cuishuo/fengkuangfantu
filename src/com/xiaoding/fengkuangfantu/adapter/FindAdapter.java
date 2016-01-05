package com.xiaoding.fengkuangfantu.adapter;

import java.util.ArrayList;

import com.xiaoding.fengkuangfantu.R;
import com.xiaoding.fengkuangfantu.service.entity.FindEntity;
import com.xiaoding.fengkuangfantu.utils.DisplayNextView;
import com.xiaoding.fengkuangfantu.utils.Flip3dAnimation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FindAdapter extends BaseAdapter {

    private boolean isFirst = true;
    private boolean isShow = false;
    private int clickPosition = -1;
    private Animation animation;
    private Context context;
    private GridView gridView;
    private Handler handler;
    private ArrayList<FindEntity> findist;
    private ViewHolder holder;

    public FindAdapter(Context context, Handler handler,
            ArrayList<FindEntity> findist, GridView gridView, boolean isFirst) {
        this.context = context;
        this.handler = handler;
        this.findist = findist;
        this.gridView = gridView;
        this.isFirst = isFirst;
    }

    public void setDataSource(ArrayList<FindEntity> dataList) {
        this.findist = dataList;
    }

    public ArrayList<FindEntity> getDataSource() {
        return findist;
    }

    public void setIsFirst(boolean isFirst) {
        this.isFirst = isFirst;
    }

    public void setClickPosition(int clickPosition) {
        this.clickPosition = clickPosition;
    }

    public void setShow(boolean isShow) {
        this.isShow = isShow;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return findist.size();
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

    @SuppressLint("NewApi")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        holder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.find_grid_item, null);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.coverImageView = (ImageView) convertView.findViewById(R.id.coverImageView);
        holder.defaultImageView = (ImageView) convertView.findViewById(R.id.defaultImageView);
        holder.coverFramlayout = (FrameLayout) convertView.findViewById(R.id.coverFramlayout);
        holder.contentRelativeLayout = (RelativeLayout) convertView.findViewById(R.id.contentRelativeLayout);
        final FindEntity entity = findist.get(position);
        String imageUrl = entity.getCover();
        String imageDefaultUrl = entity.getDefaultCover();
        ApplicationInfo appInfo = context.getApplicationInfo();
        int resIDDefault = context.getResources().getIdentifier(imageDefaultUrl, "drawable", appInfo.packageName);
        holder.defaultImageView.setImageResource(resIDDefault);
        int resID = context.getResources().getIdentifier(imageUrl, "drawable", appInfo.packageName);
        holder.coverImageView.setImageResource(resID);
        boolean isImageShow = entity.getIsImageShow();
        if (isShow) {
            if (position == parent.getChildCount()) {
                if (isFirst) {
                    showImage(isImageShow);
                }
            }
        } else {
            if (isImageShow) {
                if (position == parent.getChildCount()) {
                    showDefault(isImageShow);
                }
            }
        }
        return convertView;
    }

    private void showImage(boolean isImageShow) {
        holder.coverFramlayout.setVisibility(View.INVISIBLE);
        holder.defaultImageView.setVisibility(View.INVISIBLE);
        holder.coverFramlayout.setAnimationCacheEnabled(true);
        holder.coverFramlayout.setDrawingCacheEnabled(true);
        applyRotation(0, 90, isImageShow);
        holder.coverFramlayout.setAnimationCacheEnabled(false);
        holder.coverFramlayout.setDrawingCacheEnabled(false);
    }

    private void showDefault(boolean isImageShow) {
        holder.defaultImageView.setVisibility(View.INVISIBLE);
        holder.coverFramlayout.setVisibility(View.INVISIBLE);
        holder.coverFramlayout.setAnimationCacheEnabled(true);
        holder.coverFramlayout.setDrawingCacheEnabled(true);
        applyRotation(0, -90, isImageShow);
        holder.coverFramlayout.setAnimationCacheEnabled(false);
        holder.coverFramlayout.setDrawingCacheEnabled(false);
    }

    private void applyRotation(float start, float end, boolean isImageShow) {
        final float centerX = 240 / 2.0f;
        final float centerY = 240 / 2.0f;
        final Flip3dAnimation rotation = new Flip3dAnimation(start, end, centerX, centerY);
        rotation.setDuration(300);
        rotation.setFillAfter(true);
        rotation.setInterpolator(new AccelerateInterpolator());
        rotation.setAnimationListener(new DisplayNextView(!isImageShow,
                holder.defaultImageView, holder.coverFramlayout));

        if (!isImageShow) {
            holder.defaultImageView.startAnimation(rotation);
        } else {
            holder.coverFramlayout.startAnimation(rotation);
        }
    }

    private class ViewHolder {
        public ImageView coverImageView;
        public ImageView defaultImageView;
        public FrameLayout coverFramlayout;
        public RelativeLayout contentRelativeLayout;
        public RelativeLayout coverRelativeLayout;
    }

}
