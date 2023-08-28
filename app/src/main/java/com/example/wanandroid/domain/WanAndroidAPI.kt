package com.example.wanandroid.domain

import com.example.wanandroid.domain.bean.ArticleBean
import com.example.wanandroid.domain.bean.Banner
import com.example.wanandroid.domain.bean.PopularWebSite
import com.example.wanandroid.util.network.BaseResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface WanAndroidAPI {

    @GET("article/list/0/json")
    fun getArticles(): Flow<BaseResponse<ArticleBean>>

    @GET("banner/json")
    fun getBanners(): Flow<BaseResponse<List<Banner>>>

    @GET("friend/json")
    fun getPopularWebsite(): Flow<BaseResponse<List<PopularWebSite>>>

}