package com.example.admins.imusic.utils;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class YiYuanUrl {
    public static String baseUrl="https://route.showapi.com/";
    public static String extraUrl;//不同url后缀
    public static String appid="showapi_appid=16767&";
    public static String sign="&showapi_sign=7c0323a05d634d11a20645cb22a69589";

    public static String time;//现在时间
    public static String hotMusicUrl;//热门歌曲信息url

    public static String aqiURL;
    public static String nameMusicUrl;
    public static String getHotMusicUrl(int topId) {
        time = getTime();
        hotMusicUrl =baseUrl+"213-4?"+appid+"showapi_timestamp="+time+"&"+"topid="+topId+sign;
        return hotMusicUrl;
    }


    private static String getTime() {
        DateFormat sl = new SimpleDateFormat("yyyyMMddHHmmss");
        String time = sl.format(new Date());
        return time;
    };
    //https://route.showapi.com/213-4?showapi_appid=16767&showapi_timestamp=20160614145306&topid=5&showapi_sign=87e569c1de440a768419510405650a06
   // https://route.showapi.com/213-4?showapi_appid=16767&showapi_timestamp=20160614140739&topid=5&showapi_sign=b8434191d2184d742a92e9169716aa3e
}
