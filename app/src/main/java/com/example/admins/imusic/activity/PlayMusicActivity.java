package com.example.admins.imusic.activity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.admins.imusic.R;
import com.example.admins.imusic.bean.MusicPlayer;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.IOException;

@ContentView(R.layout.activity_play_music)
public class PlayMusicActivity extends AppCompatActivity {
    @ViewInject(R.id.view_seek_bar)
    SeekBar seekBar;


    private Uri uri;
    private String musicPath;
    private MusicPlayer musicPlayer;
    private static final String TAG = "PlayMusicActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initData();
        /*musicPath ="http://ws.stream.qqmusic.qq.com/106396897.m4a?fromtag=46" ;
        uri=Uri.parse(musicPath);
        musicPlayer=new MusicPlayer(uri, seekBar);
       // musicPlayer.
        Log.d(TAG, "onCreate: "+musicPlayer);
        Toast.makeText(PlayMusicActivity.this, "", Toast.LENGTH_SHORT).show();
*/
    }

    private void initData() {
        Intent in = getIntent();
        musicPath = in.getStringExtra("URL");
        uri = Uri.parse(musicPath);
        musicPlayer = new MusicPlayer(uri, seekBar);
    }

    @Event(value ={R.id.img_loop,R.id.img_last_song,R.id.img_next_song,R.id.img_play_list,R.id.img_play_song},type = View.OnClickListener.class)
    private void clickImageView(View v){
        switch (v.getId()){
            case R.id.img_loop:
                musicPlayer.play();
                break;
            case R.id.img_last_song:
                musicPlayer.pause();
                break;
            case R.id.img_play_song:
                musicPlayer.stop();



               // Toast.makeText(PlayMusicActivity.this, "点击循环按钮", Toast.LENGTH_SHORT).show();
                break;
            case R.id.img_next_song:
                musicPlayer.replay();
                break;
            case R.id.img_play_list:
                musicPlayer.getPlayPosition();
                Toast.makeText(PlayMusicActivity.this, "点击循环按钮", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
