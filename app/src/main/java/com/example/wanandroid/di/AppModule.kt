package com.example.wanandroid.di

import android.content.Context
import com.example.wanandroid.domain.RetrofitClient
import com.example.wanandroid.domain.WanAndroidApi
import com.example.wanandroid.domain.WanAndroidRepository
import com.example.wanandroid.domain.impl.WanAndroidRepositoryImpl

interface AppModule {
    val api: WanAndroidApi
    val repository: WanAndroidRepository
}

class AppModuleImpl(
    private val appContext: Context
) : AppModule {

    override val api: WanAndroidApi by lazy {
        RetrofitClient.getRetrofitClient().create(WanAndroidApi::class.java)
    }

    override val repository: WanAndroidRepository by lazy {
        WanAndroidRepositoryImpl(api)
    }

}