package com.newsuper.code.xuanfu;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import static com.newsuper.code.xuanfu.WebViewActivity.BROAD_CAST_NAME;


public class WindowShowService extends Service implements WindowUtil.OnPermissionListener {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        WindowUtil.getInstance().showPermissionWindow(this, this);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        WindowUtil.getInstance().dismissWindow();
    }

    @Override
    public void showPermissionDialog() {
        Intent intent = new Intent(BROAD_CAST_NAME);
        sendBroadcast(intent);
    }
}