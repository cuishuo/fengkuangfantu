package com.example.fengkuangfantu.utils;

import java.io.IOException;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;

public class SoundPlayer {
    
    private Context mContext;
    private MediaPlayer mediaPlayer;
    AssetManager assetManager;
    AssetFileDescriptor fileDescriptor;
    String soundName;
    public static final int GAME_MAIN = 1;
    public static final int GAME_PART1 = 2;
    public static final int GAME_PART2 = 3;
    
    public SoundPlayer(Context context, int num) {
        mContext = context;
        mediaPlayer = new MediaPlayer();
        assetManager = mContext.getAssets();
        switch (num) {
        case GAME_MAIN:
            soundName = "zhizihuakai.mp3";
            break;
        case GAME_PART1:
            soundName = "pianai.mp3";
            break;
        case GAME_PART2:
            soundName = "yinghuacao.mp3";
            break;

        default:
            break;
        }
        try {
            fileDescriptor = assetManager.openFd(soundName);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void startPlay() {
        try {
            mediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(),
            fileDescriptor.getStartOffset(),
            fileDescriptor.getLength());
            mediaPlayer.setLooping(true);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void stopPlay() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

}
