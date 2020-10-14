package com.newsuper.code.gank.util

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.newsuper.code.gank.Injection
import com.newsuper.code.gank.data.*
import java.lang.IllegalArgumentException

class ViewModeFactory private constructor(
        private val gankDailyRepository: GankDailyRepository,
        private val gankFilterRepository: GankFilterRepository,
        private val searchRepository: GankSearchRepository
):ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            with(modelClass){
                when{
                    isAssignableFrom(GankDailyViewModel::class.java)->{
                        GankDailyViewModel(gankDailyRepository)
                    }
                    isAssignableFrom(GankFilterViewModel::class.java) -> {
                        GankFilterViewModel(gankFilterRepository)
                    }
                    isAssignableFrom(SearchViewModel::class.java) -> {
                        SearchViewModel(searchRepository)
                    }
                    else ->
                        throw IllegalArgumentException("Unknown ViewModel: ${modelClass.name}")
                }
            }as T

companion object{
    private val INSTANCE: ViewModeFactory? =null

    fun getInstance(application: Application)=
            INSTANCE
                    ?: synchronized(ViewModeFactory::class.java){
                INSTANCE
                        ?: ViewModeFactory(
                                Injection.provideGankRepository(application),
                                Injection.provideGankPagingRepository(),
                                Injection.provideSearchRepository()
                        )
            }
}











}