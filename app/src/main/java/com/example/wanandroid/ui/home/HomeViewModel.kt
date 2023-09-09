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

            combine(
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
            }.collectLatest {
                _articles.postValue(it)
            }
        }
    }

}