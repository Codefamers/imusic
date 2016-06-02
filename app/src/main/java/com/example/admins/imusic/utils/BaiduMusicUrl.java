package com.example.admins.imusic.utils;


public class BaiduMusicUrl {
    public static String baseUrl="http://tingapi.ting.baidu.com/v1/restserver/ting";//前缀
    public static String listUrl;//歌曲列表
    public static String searchUrl;//查询
    public static String playUrl;//播放
    public static String lrcUrl;//歌词
    public static String tipsListUrl;//推荐列表
    public static String downloadUrl;//下载列表
    public static String signerInfoUrl;//获取歌手信息列表
    public static String tipsSignerUrl;//获取歌手歌曲列表
    public static String getMusicListUrl(String type,String size,String offset){
        return listUrl=baseUrl+"?method=baidu.ting.billboard.billList&type="+type+"&size="+size+"&offset="+offset+"";
    }
    public static String getMusicSearchUrl(String query,int page,int size){
        //return listUrl=baseUrl+"?method=baidu.ting.search.catalogSug&query="+query+"";
        return  searchUrl="http://search.dongting.com/song/search?q="+query+"&page="+page+"&size="+size+"";
    }
}
