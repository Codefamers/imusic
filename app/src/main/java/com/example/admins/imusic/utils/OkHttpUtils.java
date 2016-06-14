package com.example.admins.imusic.utils;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import java.io.IOException;


public class OkHttpUtils {
    public static final String TAG = " OkHttpUtils";
    private static OkHttpClient client;

    private static Handler handler = new Handler();

    public static OkHttpClient getClientInstance() {
        if (client == null) {
            synchronized (OkHttpUtils.class) {
                if (client == null) {
                    client = new OkHttpClient();
                }
            }
        }
        return client;
    }
    //get请求
    public static void okHttpGet( String url,final OnDataFinish onDataFinish){
        Request  request = new Request.Builder().url(url).build();
        OkHttpUtils.getClientInstance().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                //Log.d(TAG, "onFailure: "+);
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                final String result = response.body().string();
                int code = response.code();
                Log.d(TAG, "run: "+code);
                if (200 <= code && code <= 300) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            onDataFinish.OnSuccess(result);
                        }
                    });
                } else {
                    onDataFinish.OnSuccess("false");
                }
            }
        });
    }
    //post请求
    public static void okHttpPost(final Context context, String url, RequestBody body, final OnDataFinish onDataFinish) {
         Request  request = new Request.Builder().url(url).post(body).build();
        //Log.d(TAG, "onFailure: 连接失败");

        OkHttpUtils.getClientInstance().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                //throw new IOException("Unexpected code " + response);
                Log.d(TAG, "onFailure: 连接失败");
            }

            @Override
            public void onResponse(Response response) throws IOException {
                Log.d(TAG, "onResponse: " + response.code());
                final String result = response.body().string();
                int code = response.code();
                if (200 <= code && code <= 300) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            onDataFinish.OnSuccess(result);
                        }
                    });
                } else {
                    onDataFinish.OnSuccess("false");
                }
            }
        });

    }
    /**
     * 回调接口
     */
    public interface OnDataFinish {
        void OnSuccess(String result);

    }
}
