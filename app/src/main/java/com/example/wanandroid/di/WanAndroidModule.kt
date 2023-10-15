package com.example.wanandroid.di

import com.example.wanandroid.MyApplication
import com.example.wanandroid.RetrofitClient
import com.example.wanandroid.domain.WanAndroidApi
import com.example.wanandroid.domain.WanAndroidRepository
import com.example.wanandroid.domain.impl.WanAndroidRepositoryImpl

interface WanAndroidModule {
    val wanAndroidApi: WanAndroidApi
    val wanAndroidRepository: WanAndroidRepository
}

class WanAndroidModuleImpl(
    private val application: MyApplication,
) : WanAndroidModule {

    override val wanAndroidApi: WanAndroidApi by lazy {
        RetrofitClient.getRetrofitClient(application).create(WanAndroidApi::class.java)
    }

    override val wanAndroidRepository: WanAndroidRepository by lazy {
        WanAndroidRepositoryImpl(wanAndroidApi)
    }

}