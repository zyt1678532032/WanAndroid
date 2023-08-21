package com.example.wanandroid.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wanandroid.domain.Article
import com.example.wanandroid.domain.RetrofitClient
import com.example.wanandroid.domain.WanAndroidAPI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
    // var articles: Flow<List<Article>> = flowOf()

    init {
        // getArticles()
    }

    fun getArticles(): Flow<List<Article>> {
        val retrofit = RetrofitClient.getRetrofitClient()
        val api = retrofit.create(WanAndroidAPI::class.java)
        return api.getArticles()
    }
}