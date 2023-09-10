package com.example.wanandroid.domain

import com.example.wanandroid.domain.bean.OriginArticle
import kotlinx.coroutines.flow.Flow

interface WanAndroidRepository {

    fun getOriginArticles(): Flow<List<OriginArticle>>

    fun getNumOfOriginArticles(): Flow<Int>

    fun getTopOriginArticles(): Flow<List<OriginArticle>>

}
