package com.newsuper.code.bollon;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.text.format.Formatter;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.newsuper.code.R;

public class BollonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bollon);
        Config.Builder builder = new Config.Builder(BollonActivity.this);
        Config config = builder.pullSensitivity(2.0f).lineLength(64).isOnlyDestop(false).flyDuration(3000).balloonCount(6).create();
        BalloonPerformer.getInstance().init(BollonActivity.this, config);
    }

    public void start(View view) {
        BalloonPerformer.getInstance().show(BollonActivity.this, new BalloonGroup.OnBalloonFlyedListener() {
            @Override
            public void onBalloonFlyed() {
                releaseMemory(BollonActivity.this);
            }
        });
    }

    public void stop(View view) {
        BalloonPerformer.getInstance().gone(BollonActivity.this);
    }

    /**
     * 释放内存 <功能简述>
     */
    public static void releaseMemory(final Context context) {
        ReleasePhoneMemoryTask releasePhoneMemoryTask = new ReleasePhoneMemoryTask(
                context) {

            @Override
            protected void onPostExecute(Long result) {
                String s;
                if (result <= 0) {
                    s = "当前已是最佳状态！";
                } else {
                    s = "已经为您清理" + "<font color='#4898eb'>"
                            + Formatter.formatShortFileSize(context, result)
                            + "</font>" + "内存！";
                }
                ReleaseToast.showToast(context, Html.fromHtml(s));
            }
        };
        releasePhoneMemoryTask.execute();
    }

}
