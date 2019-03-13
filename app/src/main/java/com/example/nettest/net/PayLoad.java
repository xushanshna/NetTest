package com.example.nettest.net;

import com.example.nettest.bean.BaseResponse;

import rx.functions.Func1;


/**
 * Created by Administrator on 2019/3/12 0012.
 * 用来剥离数据，返回响应数据的data部分
 * BaseResponse<T> 原始返回数据
 * T 剥离后的数据
 */

public class PayLoad<T> implements Func1<BaseResponse<T>, T> {
    @Override
    public T call(BaseResponse<T> tBaseResponse) {

  /*      Logger.d("错误码：%s\n错误信息：%s",
                tBaseResponse.getErrorCode(),
                tBaseResponse.getErrorMsg());*/
        //响应失败，抛出异常
        if (tBaseResponse.getErrorCode() != 0) {
            try {
                throw new Fault(tBaseResponse.getErrorCode(), tBaseResponse.getErrorMsg());
            } catch (Fault fault) {
                fault.printStackTrace();
            }

        }
        return tBaseResponse.getData();
    }
}
