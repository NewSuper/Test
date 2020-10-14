package com.newsuper.code.gank.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GankDailyViewModel(private val gankDailyRepository: GankDailyRepository) : ViewModel() {
    private val _refreshing = MutableLiveData<Boolean>()
    val refreshing: LiveData<Boolean>
        get() = _refreshing
    private val _gankItemList = MutableLiveData<MutableList<GankItem>>()
    val gankItemList: LiveData<MutableList<GankItem>> get() = _gankItemList
    val _netWorkError = MutableLiveData<Boolean>()
    val netWorkError: LiveData<Boolean> get() = _netWorkError
    fun start() {
        _refreshing.value = true
        gankDailyRepository.gankDaily(dataSourceCallback)
    }

    val dataSourceCallback = object : GankDailySource.LoadGankCallback {
        override fun onGankLoaded(gankDailyData: GankDailyData) {
            _refreshing.value = false
            _netWorkError.value = false
            _gankItemList.value = gankDailyData.toGankUIItem()
        }

        override fun onDataNotAvailable() {
            _refreshing.value = false
            _netWorkError.value = true
        }

    }
}