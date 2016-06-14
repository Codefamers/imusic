package com.example.admins.imusic.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.example.admins.imusic.R;
import com.example.admins.imusic.adapter.SearchUIPagerAdapter;
import com.example.admins.imusic.utils.OkHttpUtils;
import com.example.admins.imusic.utils.YiYuanUrl;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_search_music)
public class SearchMusicActivity extends AppCompatActivity {
    private static final String TAG = "SearchMusicActivity";
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    @ViewInject(R.id.viewpager)
    ViewPager viewPager;
    @ViewInject(R.id.tablayout)
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        Log.d(TAG, "热门url: "+YiYuanUrl.getHotMusicUrl(5));
        initData();
        initView();
    }

    private void initData() {

    }


    private void initView() {
        /*
        * 初始化导航栏
        *
        * */

        // inflateMenu()：为toolbar填充对应的布局，参数为menu布局。
        // 如果该toolbar被当作了setSupportActionBar的参数，那么该方法无效
        //toolbar.inflateMenu(R.menu.toolbar_menu);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SearchMusicActivity.this, "点击返回按钮", Toast.LENGTH_SHORT).show();
            }
        });
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(SearchMusicActivity.this, "点击导航栏", Toast.LENGTH_SHORT).show();
            }
        });
        //ActionBar需要放在后面Navigation Icon才会生效
        setSupportActionBar(toolbar);


        /*
        * 初始viewpager与tablayout
        * */
        List<View> searchViewList=new ArrayList<>();

        //热门搜索页面
        View hotMusicView= LayoutInflater.from(this).inflate(R.layout.hot_search,null);
        View view2= LayoutInflater.from(this).inflate(R.layout.my_music,null);
        searchViewList.add(view2);
        searchViewList.add(hotMusicView);


        viewPager.setAdapter(new SearchUIPagerAdapter(searchViewList));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_toolbar_menu,menu);
        menu.findItem(R.id.action_search).getActionView();
        return true;
    }


}
