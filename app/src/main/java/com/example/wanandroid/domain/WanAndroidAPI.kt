package com.example.wanandroid.domain

import com.example.wanandroid.domain.bean.ArticleBean
import com.example.wanandroid.domain.bean.Banner
import com.example.wanandroid.domain.bean.PopularWebSite
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET

interface WanAndroidAPI {

    @GET("article/list/0/json")
    fun getArticles(): Flow<Response<ArticleBean>>


    @GET("banner/json")
    fun getBanners(): Flow<Response<List<Banner>>>


    @GET("https://www.wanandroid.com/friend/json")
    fun getPopularWebsite(): Flow<Response<List<PopularWebSite>>>

}