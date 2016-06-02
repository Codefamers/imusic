package com.example.admins.imusic.bean;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.SeekBar;

import com.example.admins.imusic.MyApplication;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;



/**
 * Created by admins on 2016/5/31.
 */
public class MusicPlayer implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnPreparedListener {
    public MediaPlayer mediaPlayer;//音乐播放器
    private SeekBar skbProgress;//进度条
    private Timer mTimer=new Timer();//定时器
    private Uri videoUrl;//音乐Url链接
    private boolean pause;//播放状态
    private int playPosition;//播放位置
    private static final String TAG = "MusicPlayer";
    public MusicPlayer(Uri videoUrl, SeekBar skbProgress){
        this.skbProgress=skbProgress;

        this.videoUrl=videoUrl;
        try {
            mediaPlayer=new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);//设置播放音频类型
            mediaPlayer.setOnBufferingUpdateListener(this);//设置缓存更新监听
            mediaPlayer.setOnPreparedListener(this);//设置准备监听
        }catch (Exception e){
            e.printStackTrace();
        }
        mTimer.schedule(mTimerTask,0,1000);
    }
    Handler handleProgress=new Handler(){
        public void handleMessage(Message msg) {
            int position = mediaPlayer.getCurrentPosition();
            int duration = mediaPlayer.getDuration();
            if (duration > 0) {
                long pos = skbProgress.getMax() * position / duration;
                skbProgress.setProgress((int) pos);
            }
        }
    };
    TimerTask mTimerTask=new TimerTask() {
        @Override
        public void run() {
            //Log.d(TAG, "run: "+skbProgress);
            if (mediaPlayer==null)
                return;
            if (mediaPlayer.isPlaying()&&skbProgress.isPressed()==false){
                handleProgress.sendEmptyMessage(0);
            }
        }
    };
    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        Log.d(TAG, "onBufferingUpdate: onCompletion");
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
        Log.d(TAG, "onPrepared: ");
    }
    //当来电话时，如果正在播放音乐，暂停音乐并记录其播放位置
    public void callIsComing(){
        if (mediaPlayer.isPlaying()){
            playPosition=mediaPlayer.getCurrentPosition();//获得当前播放位置
            mediaPlayer.stop();
        }
    }
    //播放
    public void play(){
        playNet(0);
    }
    //重播
    public void replay(){
        if (mediaPlayer.isPlaying()){
            mediaPlayer.seekTo(0);
        }else {
            playNet(0);
        }
    
    }
    //暂停
    public boolean pause(){
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            pause=true;
        }else {
            if(pause){
                mediaPlayer.start();//继续播放
                pause=false;
            }
        }
        return pause;
    }
    //停止
    public void stop(){
        if (mediaPlayer!=null&&mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
    }
   
    
    private void playNet(int position) {
        mediaPlayer.reset();//初始化各项参数
        /**
         * 通过MediaPlayer.setDataSource()
         * 的方法,将URL或文件路径以字符串的方式传入.使用setDataSource ()方法时,要注意以下三点:
         * 1.构建完成的MediaPlayer 必须实现Null 对像的检查.
         * 2.必须实现接收IllegalArgumentException 与IOException
         * 等异常,在很多情况下,你所用的文件当下并不存在. 3.若使用URL 来播放在线媒体文件,该文件应该要能支持pragressive
         * 下载.
         */
        try {
            mediaPlayer.setDataSource(MyApplication.getContext(),videoUrl);
            mediaPlayer.prepare();//进行缓冲
            mediaPlayer.setOnPreparedListener(new MyPreparedListener(
                    playPosition));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final class MyPreparedListener implements android.media.MediaPlayer.OnPreparedListener {
        private int playPosition;

        public MyPreparedListener(int playPosition) {
            this.playPosition = playPosition;
        }
        @Override
        public void onPrepared(MediaPlayer mp) {
            mediaPlayer.start();//开始播放
            if (playPosition>0){
                mediaPlayer.seekTo(playPosition);
            }
        }
    }
}
