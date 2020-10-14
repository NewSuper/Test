package com.newsuper.code.gank.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.newsuper.code.gank.Injection
import com.newsuper.code.gank.api.GankService


class GankSearchDataSourceFactory(
        private val queryText: String,
        private val gankService: GankService = Injection.provideGankService()
) : DataSource.Factory<Int, Gank>() {

    val sourceLiveData = MutableLiveData<GankSearchDataSource>()

    override fun create(): DataSource<Int, Gank> {
        val source = GankSearchDataSource(queryText, gankService)
        sourceLiveData.postValue(source)
        return source
    }
}