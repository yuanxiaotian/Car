package com.xuliucar.xuli.xuliucar.mvp.ModelInterface;

/**
 * Created by skyward on 2016/11/29.
 * email：
 */

public interface CallBackListener<T> {
    void OnSuccess(T t);
    void OnFailure(Throwable e);
}
