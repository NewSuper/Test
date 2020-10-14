package com.newsuper.code.gank.util

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED,
    HIDDEN
}

data class NetworkState private constructor(val status: Status){
    companion object{
        val LOADED = NetworkState(Status.SUCCESS)
        val LOADING = NetworkState(Status.RUNNING)
        val HIDDEN = NetworkState(Status.HIDDEN)
        val FAILED = NetworkState(Status.FAILED)
    }
}