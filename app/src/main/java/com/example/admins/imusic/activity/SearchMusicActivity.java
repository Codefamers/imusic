package com.example.admins.imusic.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.admins.imusic.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_search_music)
public class SearchMusicActivity extends AppCompatActivity {
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    @ViewInject(R.id.searchView)
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);


        // inflateMenu()：为toolbar填充对应的布局，参数为menu布局。
        // 如果该toolbar被当作了setSupportActionBar的参数，那么该方法无效
        //toolbar.inflateMenu(R.menu.toolbar_menu);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SearchMusicActivity.this, "点击返回按钮", Toast.LENGTH_SHORT).show();
            }
        });
       /* toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_search:
                        item.getActionView();
                        Toast.makeText(SearchMusicActivity.this, "点击搜索框", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });*/
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(SearchMusicActivity.this, "点击导航栏", Toast.LENGTH_SHORT).show();

            }
        });
        //ActionBar需要放在后面Navigation Icon才会生效
        setSupportActionBar(toolbar);



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_toolbar_menu,menu);
        menu.findItem(R.id.action_search).getActionView();
        return true;
    }


}
