package com.newsuper.code.gank

import android.content.Context
import com.newsuper.code.gank.data.*
import com.newsuper.code.gank.api.GankService
import com.newsuper.code.gank.api.RetrofitClient
import com.newsuper.code.gank.util.AppExecutors

object Injection{
    fun provideGankRepository(context: Context):GankDailyRepository{
        val database = GankDatabase.getInstance(context)
        return GankDailyRepository.getInstance(GankDailyRemoteSource.getInstance(),
                GankDailyLocalSource . getInstance(AppExecutors(),database.gankDao()))
    }

    fun provideGankPagingRepository(): GankFilterRepository {
        return GankFilterRepository.getInstance()
    }
    fun provideSearchRepository():GankSearchRepository{
        return GankSearchRepository.getInstance()
    }
    fun provideGankService(): GankService {
        return RetrofitClient.INSTANCE
    }
}