package com.newsuper.code.xuanfu


import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Log



class ApplicationLifecycle : Application.ActivityLifecycleCallbacks {

    private var started: Int = 0

    override fun onActivityPaused(activity: Activity?) {
    }

    override fun onActivityResumed(activity: Activity?) {
    }

    override fun onActivityStarted(activity: Activity?) {
        started++
        if (started == 1) {
            Log.e("TAG", "应用在前台了！！！")
            if (SPUtil.getIntDefault(WebViewActivity.ARTICLE_ID, -1) > 0) {
                activity?.startService(Intent(activity, WindowShowService::class.java))
            }
        }
    }

    override fun onActivityDestroyed(activity: Activity?) {
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
    }

    override fun onActivityStopped(activity: Activity?) {
        started--
        if (started == 0) {
            Log.e("TAG", "应用在后台了！！！")
            activity?.stopService(Intent(activity, WindowShowService::class.java))
        }
    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
    }
}
