package com.example.admins.imusic.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admins.imusic.MyApplication;
import com.example.admins.imusic.R;
import com.example.admins.imusic.bean.SearchMusicJSON;
import com.example.admins.imusic.utils.BaiduMusicUrl;
import com.example.admins.imusic.utils.OkHttpUtils;
import com.example.admins.imusic.view.CustomSearchView;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by admins on 2016/5/27.
 */
@ContentView(R.layout.search_music)
public class SearchMusicFragment extends Fragment{
    private static final String TAG = "SearchMusicFragment";
    @ViewInject(R.id.searchView)
    CustomSearchView searchMusic;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.search_music,container,false);
        x.view().inject(this,view);
        Log.d(TAG, "onCreateView: "+searchMusic);
        searchMusic.setOnQueryTextListener(new CustomSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
               // Log.d(TAG, "onQueryTextSubmit: "+query);
                //new OkHttpUtils().okHttpGet("http://search.dongting.com/song/search?q=Bila&page=1&size=20");
                // BaiduMusicUrl.getMusicSearchUrl(query,1,20));
                //new OkHttpUtils().okHttpPost(MyApplication.getContext(),);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String url="http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.search.catalogSug&query=";
                new OkHttpUtils().okHttpGet(url + newText, new OkHttpUtils.OnDataFinish() {
                    @Override
                    public void OnSuccess(String result) {
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            Gson gson=new Gson();
                            SearchMusicJSON searchMusicJSon=gson.fromJson(jsonObject.toString(),SearchMusicJSON.class);
                            Log.d(TAG, "OnSuccess: "+searchMusicJSon.song.size());
                            ArrayList<SearchMusicJSON.Song> songInfoList= (ArrayList<SearchMusicJSON.Song>) searchMusicJSon.song;

                            SearchMusicJSON.Song songInfo= gson.fromJson(searchMusicJSon.song.get(0).toString(), SearchMusicJSON.Song.class);
                            Log.d(TAG, "OnSuccess: song"+songInfo.artistname+"\n"+songInfo.songname);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });
                Log.d(TAG, "onQueryTextChange: "+newText);
                return false;
            }
        });
        return view;
    }
}
