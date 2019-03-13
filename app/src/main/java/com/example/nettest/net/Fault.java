package com.example.nettest.net;

/**
 * Created by Administrator on 2019/3/12 0012.
 * 自己封装的异常信息类
 */

public class Fault extends Throwable {
    private int errorStatus;
    private String errorMessage;

    public int getErrorStatus() {
        return errorStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Fault(int errorStatus, String errorMessage) {
        this.errorStatus = errorStatus;
        this.errorMessage = errorMessage;
    }
}
