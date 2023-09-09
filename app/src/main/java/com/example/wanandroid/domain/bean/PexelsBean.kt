package com.example.wanandroid.domain.bean

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class PexelBaseResponse(
    @SerializedName("total_results")
    val totalResults: Int? = null,
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("per_page")
    val perPage: Int? = null,
    @SerializedName("photos")
    val photos: List<PexelPhoto>? = null,
    @SerializedName("next_page")
    val nextPage: String? = null,
    @SerializedName("prev_page")
    val prevPage: String? = null
)

@Keep
data class PexelPhoto(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("width")
    val width: Int? = null,
    @SerializedName("height")
    val height: Int? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("photographer")
    val photographer: String? = null,
    @SerializedName("photographer_url")
    val photographerUrl: String? = null,
    @SerializedName("photographer_id")
    val photographerId: Int? = null,
    @SerializedName("avg_color")
    val avgColor: String? = null,
    @SerializedName("src")
    val src: List<ResourceType>? = null,
    @SerializedName("liked")
    val liked: Boolean? = null,
    @SerializedName("alt")
    val alt: String? = null
)

@Keep
data class ResourceType(
    @SerializedName("original")
    val original: String? = null,
    @SerializedName("large2x")
    val large2x: String? = null,
    @SerializedName("large")
    val large: String? = null,
    @SerializedName("medium")
    val medium: String? = null,
    @SerializedName("small")
    val small: String? = null,
    @SerializedName("portrait")
    val portrait: String? = null,
    @SerializedName("landscape")
    val landscape: String? = null,
    @SerializedName("tiny")
    val tiny: String? = null
)