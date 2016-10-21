package com.junerver.testokhttpxml;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Junerver on 2016/10/21.
 */
public class OkHttpHelper {

    private static OkHttpClient mClientInstance;

    private OkHttpHelper() {
        mClientInstance = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .writeTimeout(30000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

    }

    //内部静态类实现单例模式
    private static final class OkHttpHelperSingleton {
        private static final OkHttpHelper INSTANCE = new OkHttpHelper();
    }

    public static OkHttpHelper getInstance() {
        return OkHttpHelperSingleton.INSTANCE;
    }

    public void request(final Request request,Callback callback) {
        mClientInstance.newCall(request).enqueue(callback);
    }


    public void get(String url,Callback callback) {
        Request request = buildRequest(url, null, HttpMethodType.GET);
        request(request,callback);
    }


    /**
     * 构建请求对象
     *
     * @param url
     * @param params
     * @param type
     * @return
     */
    private Request buildRequest(String url, Map<String, String> params, HttpMethodType type) {
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        if (type == HttpMethodType.GET) {
            builder.get();
        }
        return builder.build();
    }



    /**
     * 这个枚举用于指明是哪一种提交方式
     */
    enum HttpMethodType {
        GET,
        POST
    }

}
