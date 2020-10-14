package com.newsuper.code.gank.data

class GankDailyRepository private constructor(
        private val gankDailyRemoteSource: GankDailyRemoteSource,
        private val gankDailyLocalSource: GankDailyLocalSource
) : GankDailySource {
    private var firstLoad = false
    override fun gankDaily(callback: GankDailySource.LoadGankCallback) {
        if (firstLoad) {
            getLocalGankDaily(callback)
            firstLoad = false
        }
        getRemoteGankDaily(callback)
    }

    private fun getLocalGankDaily(callback: GankDailySource.LoadGankCallback) {
        gankDailyLocalSource.gankDaily(object : GankDailySource.LoadGankCallback {
            override fun onGankLoaded(gankDailyData: GankDailyData) {
                callback.onGankLoaded(gankDailyData)
            }

            override fun onDataNotAvailable() {
                callback.onDataNotAvailable()
            }
        })

    }

    private fun getRemoteGankDaily(callback: GankDailySource.LoadGankCallback) {
        gankDailyLocalSource.gankDaily(object : GankDailySource.LoadGankCallback {
            override fun onDataNotAvailable() {
                callback.onDataNotAvailable()
            }

            override fun onGankLoaded(gankDailyData: GankDailyData) {
                saveGankDaily(gankDailyData)
                callback.onGankLoaded(gankDailyData)
            }
        })

    }

    override fun deleteGankDaily() {
        gankDailyLocalSource.deleteGankDaily()
    }

    override fun saveGankDaily(gankDailyData: GankDailyData) {
        gankDailyLocalSource.saveGankDaily(gankDailyData)
    }

    companion object {
        private var INSTANCE: GankDailyRepository? = null

        @JvmStatic
        fun getInstance(
                gankDataRemoteSource: GankDailyRemoteSource,
                gankDataLocalSource: GankDailyLocalSource
        ): GankDailyRepository {
            return INSTANCE
                    ?: GankDailyRepository(gankDataRemoteSource, gankDataLocalSource).apply {
                        INSTANCE = this
                    }
        }
    }

}