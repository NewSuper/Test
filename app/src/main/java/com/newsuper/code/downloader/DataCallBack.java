package com.newsuper.code.downloader;

public interface DataCallBack<T> {
    void onData(T data);
}