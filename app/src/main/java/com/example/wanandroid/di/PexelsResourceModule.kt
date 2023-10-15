package com.example.wanandroid.di

import com.example.wanandroid.MyApplication
import com.example.wanandroid.PexelsRetrofitClient
import com.example.wanandroid.domain.PexelsResourceApi
import com.example.wanandroid.domain.PexelsResourceRepository
import com.example.wanandroid.domain.impl.PexelsResourceRepositoryImpl

interface PexelsResourceModule {
    val pexelsResourceApi: PexelsResourceApi
    val pexelsResourceRepository: PexelsResourceRepository
}

class PexelsResourceModuleImpl(
    private val application: MyApplication,
) : PexelsResourceModule {

    override val pexelsResourceApi: PexelsResourceApi by lazy {
        PexelsRetrofitClient.getRetrofitClient(application).create(PexelsResourceApi::class.java)
    }

    override val pexelsResourceRepository: PexelsResourceRepository by lazy {
        PexelsResourceRepositoryImpl(pexelsResourceApi)
    }
}