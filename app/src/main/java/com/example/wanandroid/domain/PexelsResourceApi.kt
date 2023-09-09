package com.example.wanandroid.domain

import com.example.wanandroid.domain.bean.PexelBaseResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PexelsResourceApi {

    @GET("/v1/search")
    fun searchPhotos(
        @Query("query") query: String,
        @Query("page") page: Int = 1, //  Default: 1
        @Query("per_page") perPage: Int = 15,  // 每页返回的图片数量
        @Query("orientation") orientation: String? = null,
        @Query("size") size: String? = null,
        @Query("color") color: String? = null,
        @Query("locale") locale: String? = null,
    ): Flow<Response<PexelBaseResponse>>

}