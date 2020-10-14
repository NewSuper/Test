package com.newsuper.code.gank.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel

class GankFilterViewModel (val gankFilterRepository: GankFilterRepository):ViewModel(){
    val currentFiltering = MutableLiveData<String>()
    val gankResult = map(currentFiltering){
        gankFilterRepository.gankFilter(it)
    }
    val gankList = switchMap(gankResult){
        it.pagedList
    }
    val networkState = switchMap(gankResult){
        it.networkState
    }
    val refreshState = switchMap(gankResult){
        it.refreshState
    }
    fun refresh(){
        gankResult.value?.refresh?.invoke()
    }
    fun retry(){
        gankResult.value?.retry?.invoke()
    }
    fun filter(filtering:String){
        if (currentFiltering.value == filtering)
            return
        currentFiltering.value = filtering
    }
}