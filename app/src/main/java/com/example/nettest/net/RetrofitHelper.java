package com.example.nettest.net;

import com.example.nettest.bean.WxBean;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2019/3/12 0012.
 */

public class RetrofitHelper {
    private ApiService service;

    public RetrofitHelper() {
        service = RetrofitServiceManager.getInstance().create(ApiService.class);
    }


    //剥离数据
    public Observable<List<WxBean>> getWxList() {
        return service.getWxList()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new PayLoad<List<WxBean>>());
    }


}
