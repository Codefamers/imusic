package com.example.admins.imusic.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admins.imusic.MyApplication;
import com.example.admins.imusic.R;
import com.example.admins.imusic.bean.MusicInfo;

import java.util.ArrayList;
import java.util.List;


public class HotSearchRecycleAdapter extends RecyclerView.Adapter<HotSearchRecycleAdapter.ContentHolder> {
    private static final String TAG = "HotSearchRecycleAdapter";
    private final Context mContext;
    private HotSearchRecycleAdapter.ContentHolder contentHolder;//视图缓存holder
    private List<MusicInfo> musicInfoList;//数据集合
    private List<View> musicViewList;//视图集合
    private View itemView;//需要加载的itemView

    public HotSearchRecycleAdapter(List<MusicInfo> musicInfoList) {
        Log.d(TAG, "onCreateViewHolder: " + "2222");
        this.musicInfoList = musicInfoList;
        mContext = MyApplication.getContext();
        itemView = LayoutInflater.from(mContext).inflate(R.layout.hot_music_search_item, null, false);//获取recyclerview中的itemview
        musicViewList = new ArrayList<>();
        /*if (musicInfoList!=null){
            for (int i = 0; i <3 ; i++) {
                musicViewList.add(itemView);
            }
        }*/
        for (int i = 0; i < 3; i++) {
            musicViewList.add(itemView);
        }
    }

    //增加一条数据
    public void addItem() {
    }

    ;

    //移除一条数据
    public void removeItem() {
    }

    //增加多条数据
    public void addItems() {
    }

    //移除多条数据
    public void removeItems() {
    }

    //创建itemview
    @Override
    public HotSearchRecycleAdapter.ContentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: " + "111111");
        return contentHolder = new ContentHolder(LayoutInflater.from(
                mContext).inflate(R.layout.hot_music_search_item, parent,
                false));
    }

    //给itemview填充数据
    @Override
    public void onBindViewHolder(HotSearchRecycleAdapter.ContentHolder holder, int position) {

    }

    //数据itemview的数量
    @Override
    public int getItemCount() {
        return 3;
        //return musicInfoList.size();
    }

    public class ContentHolder extends RecyclerView.ViewHolder {
        private ImageView imgAlbum;//专辑图片
        private TextView songName, singer;//歌曲名，歌手名

        public ContentHolder(View itemView) {
            super(itemView);
            //实例化itemview中的组件进行缓存
            imgAlbum = (ImageView) itemView.findViewById(R.id.img_song);
            songName = (TextView) itemView.findViewById(R.id.txt_song_title);
            singer = (TextView) itemView.findViewById(R.id.txt_singer);
        }
    }
}
