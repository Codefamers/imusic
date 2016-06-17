package com.example.admins.imusic.view;

import android.content.Context;
import android.widget.SearchView;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.admins.imusic.R;

/**
 * Created by admins on 2016/6/2.
 */
public class CustomSearchView extends SearchView{
    public CustomSearchView(Context context) {
        super(context);
        initView();
    }
    public CustomSearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        initView();
    }
    private void initView() {
        //搜索提示字体
        this.setQueryHint("搜索音乐、歌手、歌词、...");
        //默认展开
        this.setIconifiedByDefault(false);
        //获取搜索框Id
        int searchPlateId=this.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        //获取输入框对象
        EditText searchPlate= (EditText) this.findViewById(searchPlateId);
        searchPlate.setBackground(null);
       // searchPlate.setBackgroundResource(android.R.color.darker_gray);
        int search_mag_icon_id=this.getContext().getResources().getIdentifier("android:id/search_mag_icon", null, null);
        ImageView search_icon=(ImageView) this.findViewById(search_mag_icon_id);

        search_icon.setImageResource(R.mipmap.ic_action_search);
    }
}
