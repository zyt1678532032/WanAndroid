package com.example.wanandroid.domain.impl;

import com.example.wanandroid.domain.PexelsResourceApi
import com.example.wanandroid.domain.PexelsResourceRepository
import com.example.wanandroid.domain.bean.PexelPhoto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform


class PexelsResourceRepositoryImpl(
    private val pexelsApi: PexelsResourceApi
) : PexelsResourceRepository {

    override fun getPexelPhotos(
        query: String,
        perPage: Int
    ): Flow<List<PexelPhoto>> {
        return pexelsApi.searchPhotos(
            query = query,
            perPage = perPage
        ).transform {
            emit(it.body()?.photos ?: emptyList())
        }
    }
}
