package com.example.wanandroid.domain.bean

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Navigation(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("cid")
    val cid: Int,
    @SerializedName("name")
    val name: String
)
