package com.example.wanandroid.domain.impl

import com.example.wanandroid.domain.WanAndroidApi
import com.example.wanandroid.domain.WanAndroidRepository
import com.example.wanandroid.domain.bean.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

class WanAndroidRepositoryImpl(
    private val api: WanAndroidApi
) : WanAndroidRepository {

    override fun getArticles(): Flow<List<Article>> {
        return api.getArticles().transform {
            emit(it.data?.articles ?: emptyList())
        }
    }

}