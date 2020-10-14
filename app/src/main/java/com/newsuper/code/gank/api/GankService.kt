package com.newsuper.code.gank.api

import com.newsuper.code.gank.data.GankDailyResult
import com.newsuper.code.gank.data.GankFilterResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GankService {
    @GET("today")
    fun gankDaily(): Call<GankDailyResult>

    @GET("data/{filterType}/{count}/{page}")
    fun gankFilter(
            @Path("filterType") filterType: String,
            @Path("count") count: Int,
            @Path("page") page: Int
    ): Call<GankFilterResult>

    @GET("search/query/{queryText}/category/all/count/{count}/page/{page}")
    fun search(
            @Path("queryText") queryText: String,
            @Path("count") count: Int,
            @Path("page") page: Int
    ): Call<GankFilterResult>
}