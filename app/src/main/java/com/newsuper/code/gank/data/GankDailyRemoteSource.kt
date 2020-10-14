package com.newsuper.code.gank.data

import com.newsuper.code.gank.Injection
import com.newsuper.code.gank.api.GankService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class GankDailyRemoteSource private constructor(
        private val gankService: GankService = Injection.provideGankService()
) : GankDailySource {
    override fun gankDaily(callback: GankDailySource.LoadGankCallback) {
        gankService.gankDaily().enqueue(object : Callback<GankDailyResult>{
            override fun onFailure(call: Call<GankDailyResult>, t: Throwable) {
callback.onDataNotAvailable()
            }

            override fun onResponse(call: Call<GankDailyResult>, response: Response<GankDailyResult>) {
                if (response.isSuccessful){
                    val gankDailyResult = response.body()
                    if (gankDailyResult == null || gankDailyResult.error){
                        callback.onDataNotAvailable()
                    }else{
                        callback.onGankLoaded(gankDailyResult.results)
                    }
                }else{
                    callback.onDataNotAvailable()
                }
            }
        })
    }

    override fun deleteGankDaily() {

    }

    override fun saveGankDaily(gankDailyData: GankDailyData) {

    }
    companion object{
        private var INSTANCE :GankDailyRemoteSource?= null
        @JvmStatic
        fun getInstance():GankDailyRemoteSource{
            return INSTANCE ?: GankDailyRemoteSource().apply {
                INSTANCE = this
            }
        }
    }
}