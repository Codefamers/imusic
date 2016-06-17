package com.example.admins.imusic.fragment;


import android.animation.Animator;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.admins.imusic.MyApplication;
import com.example.admins.imusic.R;
import com.example.admins.imusic.view.ExpandAnimation;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@ContentView(R.layout.frgment_my_music)
public class MyMusicFragment extends Fragment {
    @ViewInject(R.id.img_item_title)
     ImageView imgClick;
     @ViewInject(R.id.ll_show)
     LinearLayout llShow;
    @ViewInject(R.id.lv_music_manager)
    ListView musicManager;
    @ViewInject(R.id.lv_album_detail_item)
    ListView albumDetail;
    private static final String TAG = "MyMusicFragment";
    private Animation rotate;
    private String[] itemName = {"本地音乐", "最近播放", "下载管理", "我的歌手"};
    private int[] itemIcon = {R.mipmap.lay_icn_acoustics, R.mipmap.lay_icn_time, R.mipmap.lay_icn_dld, R.mipmap.lay_icn_artist};
    private List<Integer> itemNum;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = x.view().inject(this, inflater, container);
        initData();
        initView(view);
        bindData();
        return view;
    }

    private void initView(View view) {
        imgClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //第一个view参数是点击后修改的对象控件，第二个view参数是需要隐藏显示的布局控件，第三个参数是动画持续时间，单位毫秒
                //ExpandAnimation animation = new ExpandAnimation(imgClick, llShow, 200);
                // llShow.startAnimation(animation);
                Log.d(TAG, "onClick: " + llShow.getVisibility());
                if (llShow.getVisibility() == View.GONE) {
                    rotate = new RotateAnimation(0, 90, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5F);
                    llShow.setVisibility(View.VISIBLE);
                } else {
                    rotate = new RotateAnimation(90, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5F);
                    llShow.setVisibility(View.GONE);
                }
                rotate.setDuration(300);
                rotate.setFillAfter(true);
                imgClick.startAnimation(rotate);
            }
        });
    }

    private void initData() {
        List<Map<String, Object>> listItems = new ArrayList<>();

        for (int i = 0; i < itemName.length; i++) {
            Map<String, Object> listItem = new HashMap<>();
            listItem.put("itemName", itemName[i]);
            listItem.put("itemIcon", itemIcon[i]);
            listItems.add(listItem);
        }
        SimpleAdapter mMangerAdapter = new SimpleAdapter(MyApplication.getContext(), listItems, R.layout.item_list_view_music_manage,
                new String[]{"itemIcon", "itemName"}, new int[]{R.id.img_item_icon, R.id.txt_item_title});
        musicManager.setAdapter(mMangerAdapter);


    }

    private void bindData() {
    }
}
