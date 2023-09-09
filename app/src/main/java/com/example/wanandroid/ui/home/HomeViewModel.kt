package com.example.wanandroid.ui.home

import androidx.lifecycle.ViewModel
import com.example.wanandroid.domain.PexelsResourceRepository
import com.example.wanandroid.domain.WanAndroidRepository
import com.example.wanandroid.domain.bean.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first

class HomeViewModel(
    private val wanAndroidRepository: WanAndroidRepository,
    private val pexelsResourceRepository: PexelsResourceRepository
) : ViewModel() {

    suspend fun getArticles(): Flow<List<Article>> {
        // TODO: 这个perPage需要调整获取方法
        val perPage = wanAndroidRepository.getNumOfOriginArticles().first()

        return combine(
            wanAndroidRepository.getOriginArticles(),
            pexelsResourceRepository.getPexelPhotos("android", perPage)
        ) { originArticles, pexels ->
            val articles = mutableListOf<Article>()
            for (i in originArticles.indices) {
                articles += Article(
                    title = originArticles[i].title,
                    author = originArticles[i].author,
                    imageUrl = pexels[i].src?.original!!
                )
            }
            articles
        }
    }

}