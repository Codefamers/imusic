package com.example.admins.imusic.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;


import com.example.admins.imusic.R;
import com.example.admins.imusic.adapter.MainUIFragmentPagerAdapter;
import com.example.admins.imusic.fragment.MusicCaseFragment;
import com.example.admins.imusic.fragment.MyMusicFragment;
import com.example.admins.imusic.fragment.SearchMusicFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    private MyMusicFragment myMusicFragment;
    private MusicCaseFragment musicCaseFragment;
    private SearchMusicFragment searchMusicFragment;
    private static final String TAG = "MainActivity";
    private MainUIFragmentPagerAdapter frgPagerAdapter;
    private List<Fragment> fragmentList;
    @ViewInject(R.id.viewpager)
    ViewPager viewPager;
    @ViewInject(R.id.sliding_tabs)
    TabLayout tabLayout;
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    @ViewInject(R.id.drawer)
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        x.view().inject(this);
        initView();

    }

    private void initView() {
        toolbar.setNavigationIcon(R.drawable.ic_reorder);
        toolbar.inflateMenu(R.menu.toolbar_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
               // Toast.makeText(MainActivity.this, "点击侧边栏", Toast.LENGTH_SHORT).show();
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_search:
                        Intent in=new Intent(MainActivity.this,SearchMusicActivity.class);
                        startActivity(in);
                        //Toast.makeText(MainActivity.this, "点击搜索框", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });




        fragmentList=new ArrayList<>();
        myMusicFragment=new MyMusicFragment();
        musicCaseFragment=new MusicCaseFragment();
        searchMusicFragment=new SearchMusicFragment();
        fragmentList.add(myMusicFragment);
        fragmentList.add(musicCaseFragment);
        fragmentList.add(searchMusicFragment);
        frgPagerAdapter=new MainUIFragmentPagerAdapter(getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(frgPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }
    @Event(value={R.id.layout_music_player,R.id.image_button_next_song,R.id.image_button_pause_song},type =View.OnClickListener.class)
    private void onClickPlayLayout(View v){
        switch (v.getId()){
            case R.id.layout_music_player:
                Toast.makeText(MainActivity.this, "点击播放布局", Toast.LENGTH_SHORT).show();
                Intent in=new Intent(MainActivity.this,PlayMusicActivity.class);
                startActivity(in);
                break;
            case R.id.image_button_next_song:
                Toast.makeText(MainActivity.this, "点击下一首", Toast.LENGTH_SHORT).show();
                break;
            case R.id.image_button_pause_song:
                Toast.makeText(MainActivity.this, "点击播放按钮", Toast.LENGTH_SHORT).show();
                break;

        }

    }
}
