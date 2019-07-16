package com.sun.dailynote.ui;

public interface BaseCallBack<T> {
    void onPreExcute();
    void onExcuteSuccess();
    void onError(Exception ex);
    void onSuccess(T data);
    void onMess(String ess);
}

