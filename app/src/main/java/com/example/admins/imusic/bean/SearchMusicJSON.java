package com.example.admins.imusic.bean;

import java.util.List;


public class SearchMusicJSON {
    public List album;
    public List artist;
    public List<SearchMusicJSON.Song> song;
    public class Song{
       public String artistname;//歌手名
       public String songname; //歌曲名
    }
}
