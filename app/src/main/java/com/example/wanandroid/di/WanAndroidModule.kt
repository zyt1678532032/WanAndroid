package com.example.wanandroid.di

import android.content.Context
import com.example.wanandroid.RetrofitClient
import com.example.wanandroid.domain.WanAndroidApi
import com.example.wanandroid.domain.WanAndroidRepository
import com.example.wanandroid.domain.impl.WanAndroidRepositoryImpl

interface WanAndroidModule {
    val wanAndroidApi: WanAndroidApi
    val wanAndroidRepository: WanAndroidRepository
}

class WanAndroidModuleImpl(
    private val appContext: Context,
) : WanAndroidModule {

    override val wanAndroidApi: WanAndroidApi by lazy {
        RetrofitClient.getRetrofitClient().create(WanAndroidApi::class.java)
    }

    override val wanAndroidRepository: WanAndroidRepository by lazy {
        WanAndroidRepositoryImpl(wanAndroidApi)
    }

}