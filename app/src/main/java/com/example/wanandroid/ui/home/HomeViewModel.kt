package com.example.wanandroid.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wanandroid.domain.bean.Article
import com.example.wanandroid.domain.RetrofitClient
import com.example.wanandroid.domain.WanAndroidAPI
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _articles: MutableLiveData<List<Article>> = MutableLiveData()
    val articles: LiveData<List<Article>> = _articles

    private val api = RetrofitClient.getRetrofitClient().create(WanAndroidAPI::class.java)

    init {
        getArticles()
    }

    private fun getArticles() {

        viewModelScope.launch {
            api.getArticles().collectLatest {
                val response = it.data
                _articles.postValue(response?.articles)
            }
        }
    }
}