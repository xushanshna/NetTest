package com.example.nettest.net;

import com.example.nettest.bean.BaseResponse;
import com.example.nettest.bean.WxBean;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Administrator on 2019/3/12 0012.
 */

public interface ApiService {


    //获取wanandroid公众号列表
    @GET("wxarticle/chapters/json")
    Observable<BaseResponse<List<WxBean>>> getWxList();
}
