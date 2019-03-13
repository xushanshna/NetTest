package com.example.nettest.net;

import com.example.nettest.base.Constant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2019/3/12 0012.
 * 配置client，管理retrofit实例
 */

public class RetrofitServiceManager {
    private static final int DEFAULT_TIME_OUT = 3;
    private static final int DEFAULT_WRITE_TIME_OUT = 10;
    private static final int DEFAULT_READ_TIME_OUT = 10;
    private static RetrofitServiceManager INSTANCE = null;
    private Retrofit retrofit;

    public RetrofitServiceManager() {
        //配置client
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
        builder.writeTimeout(DEFAULT_WRITE_TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);
        //日志拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(loggingInterceptor);
        //公共参数拦截器
        HttpCommonInterceptor.Builder builder1 = new HttpCommonInterceptor.Builder();
        builder1.addHeaderParams("paltform", "android");
        builder.addInterceptor(builder1.build());

        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(Constant.BASE_URL_WANANDROID)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }


    public static RetrofitServiceManager getInstance() {
        if (INSTANCE == null) {
            synchronized (RetrofitServiceManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RetrofitServiceManager();
                }
            }
        }
        return INSTANCE;
    }


    public <T> T create(Class<T> service) {
        return retrofit.create(service);
    }
}
