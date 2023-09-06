package com.example.wanandroid.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wanandroid.domain.RetrofitClient
import com.example.wanandroid.domain.WanAndroidApi
import com.example.wanandroid.domain.WanAndroidRepository
import com.example.wanandroid.domain.bean.Article
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: WanAndroidRepository
) : ViewModel() {

    private val _articles: MutableLiveData<List<Article>> = MutableLiveData()
    val articles: LiveData<List<Article>> = _articles

    init {
        getArticles()
    }

    private fun getArticles() {
        viewModelScope.launch {
            repository.getArticles().collectLatest { articles ->
                _articles.postValue(articles)
            }
        }
    }
}