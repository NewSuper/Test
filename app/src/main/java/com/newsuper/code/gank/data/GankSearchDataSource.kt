package com.newsuper.code.gank.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.newsuper.code.gank.Injection
import com.newsuper.code.gank.api.GankService
import com.newsuper.code.gank.util.NetworkState
import retrofit2.Call
import retrofit2.Response


class GankSearchDataSource(
        private val queryText: String,
        private val gankService: GankService = Injection.provideGankService()
) : PageKeyedDataSource<Int, Gank>() {

    private var retry: (() -> Any)? = null

    val networkState = MutableLiveData<NetworkState>()

    val initialLoad = MutableLiveData<NetworkState>()

    fun retryAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.also {
            it.invoke()
        }
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Gank>) {
        initialLoad.postValue(NetworkState.LOADING)
        networkState.postValue(NetworkState.HIDDEN)
        gankService.search(queryText, params.requestedLoadSize, 1)
                .enqueue(object : retrofit2.Callback<GankFilterResult> {
                    override fun onFailure(call: Call<GankFilterResult>, t: Throwable) {
                        retry = {
                            loadInitial(params, callback)
                        }
                        initialLoad.postValue(NetworkState.FAILED)
                    }

                    override fun onResponse(call: Call<GankFilterResult>, response: Response<GankFilterResult>) {
                        if (response.isSuccessful) {
                            retry = null
                            callback.onResult(
                                    response.body()?.results ?: emptyList(),
                                    null, 2
                            )
                            initialLoad.postValue(NetworkState.LOADED)
                        } else {
                            retry = {
                                loadInitial(params, callback)
                            }
                            initialLoad.postValue(NetworkState.FAILED)
                        }
                    }

                })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Gank>) {
        networkState.postValue(NetworkState.LOADING)
        gankService.search(queryText, params.requestedLoadSize, params.key)
                .enqueue(object : retrofit2.Callback<GankFilterResult> {

                    override fun onFailure(call: Call<GankFilterResult>, t: Throwable) {
                        retry = {
                            loadAfter(params, callback)
                        }
                        networkState.postValue(NetworkState.FAILED)
                    }

                    override fun onResponse(call: Call<GankFilterResult>, response: Response<GankFilterResult>) {
                        if (response.isSuccessful) {
                            retry = null
                            val results = response.body()?.results ?: emptyList()
                            callback.onResult(
                                    results,
                                    params.key + 1
                            )
                            networkState.postValue(NetworkState.LOADED)
                        } else {
                            retry = {
                                loadAfter(params, callback)
                            }
                            networkState.postValue(NetworkState.FAILED)
                        }
                    }

                })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Gank>) {

    }

}