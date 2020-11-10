package com.newsuper.code.bollon;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * 应用安装卸载的广播
 */
public class AppInstallReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // 更新安装包列表
        PackageInfoStorage.updateHomeList(context);
    }
}
