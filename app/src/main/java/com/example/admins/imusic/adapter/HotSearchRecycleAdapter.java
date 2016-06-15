package com.example.admins.imusic.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.admins.imusic.MyApplication;
import com.example.admins.imusic.R;
import com.example.admins.imusic.activity.PlayMusicActivity;
import com.example.admins.imusic.bean.MusicInfo;
import com.example.admins.imusic.utils.Constant;
import com.example.admins.imusic.utils.ImageAsyncTask;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class HotSearchRecycleAdapter extends RecyclerView.Adapter<HotSearchRecycleAdapter.ContentHolder> {
    private static final String TAG = "HotSearchRecycleAdapter";
    private final Context mContext;
    private HotSearchRecycleAdapter.ContentHolder contentHolder;//视图缓存holder
    private ArrayList<MusicInfo> musicInfoList;//数据集合
    private View itemView;//需要加载的itemView

    public HotSearchRecycleAdapter(List<MusicInfo> musicInfoList) {
        Log.d(TAG, "onCreateViewHolder: " + "2222");
        this.musicInfoList = (ArrayList<MusicInfo>) musicInfoList;
        mContext = MyApplication.getContext();
        itemView = LayoutInflater.from(mContext).inflate(R.layout.item_hot_music_search, null, false);//获取recyclerview中的itemview


    }

    //增加一条数据
    public void addItem() {
    }



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
                mContext).inflate(R.layout.item_hot_music_search, parent,
                false));
    }

    //给itemview填充数据
    @Override
    public void onBindViewHolder(HotSearchRecycleAdapter.ContentHolder holder, final int position) {
        final MusicInfo musicInfo = musicInfoList.get(position);
        holder.songName.setText(musicInfo.songname);
        holder.singer.setText(musicInfo.singername);
        new ImageAsyncTask(holder.imgAlbum).execute(musicInfo.albumpic_small);
        // .setImageBitmap(getBitMap(musicInfo.albumpic_small));
        holder.imgAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MyApplication.getContext(), PlayMusicActivity.class);
                in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                in.putExtra(Constant.MUSIC_POSITION, String.valueOf(position));
                in.putExtra("URL", musicInfo.url);
                MyApplication.getContext().startActivity(in);
            }
        });

    }

    //将url转化为bitmap
    private Bitmap getBitMap(String imageUrl) {
        //首先将字符串转化为流对象，然后用bitmap工厂模式创建bitmap对象
        URL url = null;
        Bitmap bitmap = null;
        InputStream input = null;
        try {
            url = new URL(imageUrl);
            input = url.openStream();
            bitmap = BitmapFactory.decodeStream(input);
            input.close();
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //数据itemview的数量
    @Override
    public int getItemCount() {
        return musicInfoList.size();
        //return musicInfoList.size();
    }

    public class ContentHolder extends RecyclerView.ViewHolder {
        private ImageView imgAlbum;//专辑图片
        private TextView songName, singer;//歌曲名，歌手名

        //private ;, final HotSearchRecycleAdapter adapter
        // private int position;
        public ContentHolder(View itemView) {
            super(itemView);
            //实例化itemview中的组件进行缓存
            imgAlbum = (ImageView) itemView.findViewById(R.id.img_song);
            songName = (TextView) itemView.findViewById(R.id.txt_song_title);
            singer = (TextView) itemView.findViewById(R.id.txt_singer);

        }
    }
}
