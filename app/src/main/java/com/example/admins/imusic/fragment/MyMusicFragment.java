package com.example.admins.imusic.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.admins.imusic.R;
import com.example.admins.imusic.view.ExpandAnimation;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.frgment_my_music)
public class MyMusicFragment extends Fragment {
    /* @ViewInject(R.id.img_click)
     ImageView imgClick;
     @ViewInject(R.id.ll_show)
     LinearLayout llShow;*/
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        // View view=inflater.inflate(R.layout.frgment_my_music,container,false);
        View view = x.view().inject(this, inflater, container);

        /*imgClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //第一个view参数是点击后修改的对象控件，第二个view参数是需要隐藏显示的布局控件，第三个参数是动画持续时间，单位毫秒
                ExpandAnimation animation = new ExpandAnimation(imgClick, llShow, 500);
                llShow.startAnimation(animation);
            }
        });*/
        return view;
    }
}
