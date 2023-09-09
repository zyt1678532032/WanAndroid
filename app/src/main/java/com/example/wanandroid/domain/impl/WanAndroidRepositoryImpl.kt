package com.example.wanandroid.domain.impl

import com.example.wanandroid.domain.WanAndroidApi
import com.example.wanandroid.domain.WanAndroidRepository
import com.example.wanandroid.domain.bean.OriginArticle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

class WanAndroidRepositoryImpl(
    private val wanAndroidApi: WanAndroidApi
) : WanAndroidRepository {

    override fun getOriginArticles(): Flow<List<OriginArticle>> {
        return wanAndroidApi.getArticles().transform {
            emit(it.data?.articles ?: emptyList())
        }
    }

    override fun getNumOfOriginArticles(): Flow<Int> {
        return wanAndroidApi.getArticles().transform {
            emit(it.data?.size ?: 0)
        }
    }

    override fun getTopArticles(): Flow<List<OriginArticle>> {
        return wanAndroidApi.getTopArticles().transform {
            emit(it.data ?: emptyList())
        }
    }

}