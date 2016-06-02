package com.example.admins.imusic.utils;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class YiYuanUrl {
    public static String baseUrl="https://route.showapi.com/213-1?";
    public static String appid="&showapi_appid=16767&showapi_timestamp=20160527171437&showapi_sign=87e569c1de440a768419510405650a06";

    public static String time;
    public static String aqiURL;
    public static String nameMusicUrl;
    public static String getNameMusicUrl(String musicName,int page) {
        time = getTime();
        nameMusicUrl =baseUrl+"keyword="+musicName+"&page="+page+appid;
        return nameMusicUrl;
    }

    private static String getTime() {
        DateFormat sl = new SimpleDateFormat("yyyyMMddHHmmss");
        String time = sl.format(new Date());
        return time;
    };
}
