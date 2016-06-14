package com.example.admins.imusic;

import android.app.Application;
import android.content.Context;

import org.xutils.x;


public class MyApplication extends Application{
    public static Context context;

    private static MyApplication myApp;
    public static MyApplication getMyAppInstance(){
        if(myApp==null){
            synchronized (MyApplication.class){
                if (myApp==null){
                    myApp=new MyApplication();
                }
            }
        }
        return myApp;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
        x.Ext.init(this);
    }
    public static Context getContext(){
        return context;
    }
}
