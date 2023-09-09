package com.example.wanandroid.di

import android.content.Context
import com.example.wanandroid.PexelsRetrofitClient
import com.example.wanandroid.domain.PexelsResourceApi
import com.example.wanandroid.domain.PexelsResourceRepository
import com.example.wanandroid.domain.impl.PexelsResourceRepositoryImpl

interface PexelsResourceModule {
    val pexelsResourceApi: PexelsResourceApi
    val pexelsResourceRepository: PexelsResourceRepository
}

class PexelsResourceModuleImpl(
    private val appContext: Context,
) : PexelsResourceModule {

    override val pexelsResourceApi: PexelsResourceApi by lazy {
        PexelsRetrofitClient.getRetrofitClient().create(PexelsResourceApi::class.java)
    }

    override val pexelsResourceRepository: PexelsResourceRepository by lazy {
        PexelsResourceRepositoryImpl(pexelsResourceApi)
    }
}