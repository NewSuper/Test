package com.newsuper.code.bollon;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * 开机启动接收器
 *
 */
public class BootBroadcastReceiver extends BroadcastReceiver {
    // 重写onReceive方法
    @Override
    public void onReceive(Context context, Intent intent) {
        boolean b = ServiceUtil.isWorked(context,
                ReleaseService.class.getName());
        if (PreferenceHelper.isRunning(context) && !b) {
            ReleaseService.startService(context);
        }
    }

}
