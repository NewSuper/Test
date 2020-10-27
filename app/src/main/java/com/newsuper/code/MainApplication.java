package com.newsuper.code;

import android.app.Application;

import com.newsuper.code.swipeback.SlideFinishManager;

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SlideFinishManager.getInstance().init(this);
    }
}
