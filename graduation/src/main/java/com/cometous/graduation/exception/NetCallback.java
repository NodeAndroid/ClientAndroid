package com.cometous.graduation.exception;

/**
 * Created by lenovo on 2015/4/15.
 */

/**
 * 网络回调
 */
public interface NetCallback  {
    void onException(Exception e);
    void makeToast();
}
