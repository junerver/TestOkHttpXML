package com.junerver.testokhttpxml;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeatherService extends Service {

    private static final String WEATHER_XML_API = "http://wthrcdn.etouch.cn/WeatherApi?city=%E5%8C%97%E4%BA%AC";
    private String weatherXML;

    public WeatherService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpHelper.getInstance()
                        .get(WEATHER_XML_API, new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                weatherXML = response.body().string();
                            }
                        });

            }
        }).start();

        return super.onStartCommand(intent, flags, startId);
    }
}
