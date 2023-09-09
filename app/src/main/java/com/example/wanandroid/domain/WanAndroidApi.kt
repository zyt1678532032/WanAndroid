package com.example.wanandroid.domain

import com.example.wanandroid.domain.bean.Article
import com.example.wanandroid.domain.bean.ArticleBean
import com.example.wanandroid.domain.bean.Banner
import com.example.wanandroid.domain.bean.PopularWebSite
import com.example.wanandroid.util.network.BaseResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface WanAndroidApi {

    // 首页文章列表
    @GET("/article/list/0/json")
    fun getArticles(): Flow<BaseResponse<ArticleBean>>

    // 置顶文章
    @GET("article/top/json")
    fun getTopArticles(): Flow<BaseResponse<List<Article>>>

    @GET("banner/json")
    fun getBanners(): Flow<BaseResponse<List<Banner>>>

    // 常用网站
    @GET("friend/json")
    fun getPopularWebsite(): Flow<BaseResponse<List<PopularWebSite>>>

    // 导航数据
    @GET("navi/json")
    fun getNavigationList(): Flow<BaseResponse<List<Banner>>>

}