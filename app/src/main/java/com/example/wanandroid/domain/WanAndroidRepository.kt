package com.example.wanandroid.domain

import com.example.wanandroid.domain.bean.Article
import kotlinx.coroutines.flow.Flow

interface WanAndroidRepository {

    fun getArticles(): Flow<List<Article>>

}
