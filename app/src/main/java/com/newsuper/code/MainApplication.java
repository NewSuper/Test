package com.newsuper.code;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.newsuper.code.bollon.BalloonPerformer;
import com.newsuper.code.downloader.DefaultDownloadHistoryDBHelper;
import com.newsuper.code.downloader.Jarvis;
import com.newsuper.code.swipeback.SlideFinishManager;

public class MainApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SlideFinishManager.getInstance().init(this);

        Jarvis.init(new DefaultDownloadHistoryDBHelper(this));
        BalloonPerformer.getInstance().delegateOnConfigurationChanged(this);
    }
}
