package com.example.wanandroid.domain

import com.example.wanandroid.domain.bean.BaseResponseBean
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface WanAndroidAPI {

    @GET("article/list/0/json")
    fun getArticles(): Flow<BaseResponseBean>

}