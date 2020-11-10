package com.newsuper.code.bollon;

import android.content.Context;

public class Config {

    public Config() {
    }

    public int getBalloonCount(Context context) {
        return PreferenceHelper.balloonCount(context);
    }

    public void setBalloonCount(Context context, int balloonCount) {
        PreferenceHelper.cacheBalloonCount(context, balloonCount);
    }

    public long getFlyDuration(Context context) {
        return PreferenceHelper.flyDuration(context);
    }

    public void setFlyDuration(Context context, long flyDuration) {
        PreferenceHelper.cacheFlyDuration(context, flyDuration);
    }

    public int getLineLength(Context context) {
        return PreferenceHelper.lineLength(context);
    }

    public void setLineLength(Context context, int lineLength) {
        PreferenceHelper.cacheLineLength(context, lineLength);
    }

    public float getPullSensitivity(Context context) {
        return PreferenceHelper.pullSen(context);
    }

    public void setPullSensitivity(Context context, float pullSensitivity) {
        PreferenceHelper.cachePullSen(context, pullSensitivity);
    }

    public boolean isOnlyDestop(Context context) {
        return PreferenceHelper.isOnlyDestop(context);
    }

    public void setIsOnlyDestop(Context context, boolean isOnlyDestop) {
        PreferenceHelper.cacheOnlyDestop(context, isOnlyDestop);
    }

    public static class Builder {

        private Config mConfig = new Config();

        public Context mContext;

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder balloonCount(int balloonCount) {
            this.mConfig.setBalloonCount(mContext, balloonCount);
            return this;
        }

        public Builder flyDuration(long flyDuration) {
            this.mConfig.setFlyDuration(mContext, flyDuration);
            return this;
        }

        public Builder lineLength(int lineLength) {
            this.mConfig.setLineLength(mContext, lineLength);
            return this;
        }

        public Builder pullSensitivity(float pullSensitivity) {
            this.mConfig.setPullSensitivity(mContext, pullSensitivity);
            return this;
        }

        public Builder isOnlyDestop(boolean isOnlyDestop) {
            this.mConfig.setIsOnlyDestop(mContext, isOnlyDestop);
            return this;
        }

        public Config create() {
            return mConfig;
        }
    }
}