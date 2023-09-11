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
            val perPage = wanAndroidRepository.getNumOfOriginArticles().first()
            val topArticles = wanAndroidRepository.getTopOriginArticles().first()

            combine(
                wanAndroidRepository.getOriginArticles(),
                pexelsResourceRepository.getPexelPhotos("android", perPage)
            ) { originArticles, pexels ->
                val articles = mutableListOf<Article>()
                for (i in originArticles.indices) {
                    if (i == 0) {
                        articles += Article(
                            title = topArticles.first().title,
                            author = topArticles.first().author.ifEmpty { "匿名作者" },
                            imageUrl = pexels[i].src?.original!!,
                            link = topArticles.first().link,
                            isTop = true
                        )
                        continue
                    }
                    articles += Article(
                        title = originArticles[i].title,
                        author = originArticles[i].author.ifEmpty { "匿名作者" },
                        imageUrl = pexels[i].src?.original!!,
                        link = originArticles[i].link,
                        isTop = false
                    )
                }
                articles
            }.collectLatest {
                _articles.postValue(it)
            }
        }
    }

}