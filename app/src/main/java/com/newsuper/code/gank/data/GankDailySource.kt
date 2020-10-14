package com.newsuper.code.gank.data

interface GankDailySource {
    interface LoadGankCallback {
        fun onGankLoaded(gankDailyData: GankDailyData)
        fun onDataNotAvailable()
    }

    fun gankDaily(callback: LoadGankCallback)
    fun deleteGankDaily()
    fun saveGankDaily(gankDailyData: GankDailyData)
}