package com.example.wanandroid.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wanandroid.domain.PexelsResourceRepository
import com.example.wanandroid.domain.WanAndroidRepository
import com.example.wanandroid.domain.bean.Article
import com.example.wanandroid.domain.bean.PexelPhoto
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class HomeViewModel(
    private val wanAndroidRepository: WanAndroidRepository,
    private val pexelsResourceRepository: PexelsResourceRepository
) : ViewModel() {

    private val _articles: MutableLiveData<List<Article>> = MutableLiveData()
    val articles: LiveData<List<Article>> = _articles

    private val _pexelPhotos: MutableLiveData<List<PexelPhoto>> = MutableLiveData()
    val pexelPhotos: LiveData<List<PexelPhoto>> = _pexelPhotos

    init {
        getArticles()
    }

    private fun getArticles() {
        viewModelScope.launch {
            val originArticlesFlow = wanAndroidRepository.getOriginArticles()
            val perPage = wanAndroidRepository.getNumOfOriginArticles().first()
            val pexelsFlow = pexelsResourceRepository.getPexelPhotos("android", perPage)

            originArticlesFlow.combine(pexelsFlow) { originArticles, pexels ->
                val articles = mutableListOf<Article>()
                for (i in originArticles.indices) {
                    articles += Article(
                        title = originArticles[i].title,
                        author = originArticles[i].author,
                        imageUrl = pexels[i].src?.original!!
                    )
                }
                articles
            }.collectLatest {
                _articles.postValue(it)
            }
        }
    }

    private fun getPexelPhotos(
        query: String,
        perPage: Int
    ) {
        viewModelScope.launch {
            pexelsResourceRepository.getPexelPhotos(
                query = query,
                perPage = perPage
            ).collectLatest {
                _pexelPhotos.postValue(it)
            }
        }
    }
}