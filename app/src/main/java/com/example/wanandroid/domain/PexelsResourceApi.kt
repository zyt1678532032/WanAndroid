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
        @Query("orientation") orientation: String,
        @Query("size") size: String,
        @Query("color") color: String,
        @Query("locale") locale: String,
        @Query("page") page: Int = 1, //  Default: 1
        @Query("per_page") perPage: Int = 15,  // Default: 15 Max: 80
    ): Flow<Response<PexelBaseResponse>>

}