package com.example.wanandroid.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wanandroid.domain.PexelsResourceRepository
import com.example.wanandroid.domain.WanAndroidRepository
import com.example.wanandroid.domain.bean.Article
import com.example.wanandroid.util.network.RequestStatus
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel(
    private val wanAndroidRepository: WanAndroidRepository,
    private val pexelsResourceRepository: PexelsResourceRepository
) : ViewModel() {

    private val _articles: MutableLiveData<List<Article>> = MutableLiveData()
    val articles: LiveData<List<Article>> = _articles

    private val _status: MutableLiveData<RequestStatus> = MutableLiveData()
    val status: LiveData<RequestStatus> = _status

    init {
        getArticles()
    }

    private fun getArticles() {
        viewModelScope.launch {
            val normalArticlesSize = wanAndroidRepository.getOriginArticles().first().size
            val topArticlesSize = wanAndroidRepository.getTopOriginArticles().first().size

            combine(
                wanAndroidRepository.getOriginArticles(),
                wanAndroidRepository.getTopOriginArticles(),
                pexelsResourceRepository.getPexelPhotos(
                    "android",
                    normalArticlesSize + topArticlesSize
                )
            ) { normalArticles, topArticles, pexels ->

                val articles = mutableListOf<Article>()
                var indexForNormalArticle = topArticles.size
                for (i in normalArticles.indices) {
                    if (i == 0) {
                        for (j in topArticles.indices) {
                            articles += Article(
                                title = topArticles[j].title,
                                author = topArticles[j].author.ifEmpty { "匿名作者" },
                                imageUrl = pexels[j].src?.original!!,
                                link = topArticles[j].link,
                                isTop = true
                            )
                        }
                        continue
                    }
                    articles += Article(
                        title = normalArticles[i].title,
                        author = normalArticles[i].author.ifEmpty { "匿名作者" },
                        imageUrl = pexels[indexForNormalArticle++].src?.original!!,
                        link = normalArticles[i].link,
                        isTop = false
                    )
                }
                _status.postValue(RequestStatus.SUCCESS)
                articles
            }.onStart {
                _status.postValue(RequestStatus.LOADING)
            }.collectLatest {
                _articles.postValue(it)
            }
        }
    }

}