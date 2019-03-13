package com.example.nettest.net;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2019/3/12 0012.
 * 公共参数拦截器，拦截请求，为每个请求添加相同的公共参数
 */

public class HttpCommonInterceptor implements Interceptor {
    private Map<String, String> headerParamMap = new HashMap<>();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        //新的请求
        Request.Builder builder = request.newBuilder();
        builder.method(request.method(), request.body());
        //添加公共参数到header中
        if (headerParamMap.size() > 0) {
            for (Map.Entry<String, String> param : headerParamMap.entrySet()) {
                builder.header(param.getKey(), param.getValue());
            }
        }
        return chain.proceed(builder.build());
    }


    static class Builder {
        HttpCommonInterceptor commonInterceptor;

        public Builder() {
            commonInterceptor = new HttpCommonInterceptor();
        }

        public Builder addHeaderParams(String key, String value) {
            commonInterceptor.headerParamMap.put(key, value);
            return this;
        }

        public Builder addHeaderParams(String key, int value) {
            return addHeaderParams(key, String.valueOf(value));
        }

        public Builder addHeaderParams(String key, float value) {
            return addHeaderParams(key, String.valueOf(value));
        }

        public Builder addHeaderParams(String key, long value) {
            return addHeaderParams(key, String.valueOf(value));
        }

        public Builder addHeaderParams(String key, double value) {
            return addHeaderParams(key, String.valueOf(value));
        }

        public HttpCommonInterceptor build() {
            return commonInterceptor;
        }
    }
}
