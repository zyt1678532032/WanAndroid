package com.example.wanandroid.domain

import retrofit2.http.GET

interface WanAndroidAPI {

    @GET("article/list/0/json")
    suspend fun getArticles(): BaseResponseBean

}