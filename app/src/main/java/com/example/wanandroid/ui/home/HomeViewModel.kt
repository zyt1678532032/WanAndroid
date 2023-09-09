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
            val articleFLow = wanAndroidRepository.getArticles()
            val pexelsFlow = pexelsResourceRepository.getPexelPhotos("", 10)
            articleFLow.combine(pexelsFlow) { articles, pexels ->

            }.collectLatest {

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