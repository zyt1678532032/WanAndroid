package com.example.wanandroid.domain

import com.example.wanandroid.domain.bean.PexelPhoto
import kotlinx.coroutines.flow.Flow

interface PexelsResourceRepository {

    fun getPexelPhotos(query: String, perPage: Int): Flow<List<PexelPhoto>>
}