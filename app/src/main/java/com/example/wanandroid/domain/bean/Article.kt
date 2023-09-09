package com.example.wanandroid.domain.bean

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ArticleBean(
    @SerializedName("curPage")
    val curPage: Int,
    @SerializedName("datas")
    val articles: List<OriginArticle>,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("over")
    val over: Boolean,
    @SerializedName("pageCount")
    val pageCount: Int,
    @SerializedName("size")
    val size: Int,
    @SerializedName("total")
    val total: Int
)

// 文章
@Keep
data class OriginArticle(
    @SerializedName("adminAdd")
    val adminAdd: Boolean,
    @SerializedName("apkLink")
    val apkLink: String? = null,
    @SerializedName("audit")
    val audit: Int,
    @SerializedName("author")
    val author: String,
    @SerializedName("canEdit")
    val canEdit: Boolean,
    @SerializedName("chapterId")
    val chapterId: String,
    @SerializedName("chapterName")
    val chapterName: String,
    @SerializedName("collect")
    val collect: Boolean,
    @SerializedName("courseId")
    val courseId: Int,
    @SerializedName("desc")
    val desc: String,
    @SerializedName("descMd")
    val descMd: String,
    @SerializedName("fresh")
    val fresh: Boolean,
    @SerializedName("host")
    val host: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("isAdminAdd")
    val isAdminAdd: Boolean,
    @SerializedName("link")
    val link: String,
    @SerializedName("niceShareDate")
    val niceShareDate: String,
    @SerializedName("origin")
    val origin: String,
    @SerializedName("prefix")
    val prefix: String,
    @SerializedName("projectLink")
    val projectLink: String,
    @SerializedName("publishTime")
    val publishTime: Long,
    @SerializedName("realSuperChapterId")
    val realSuperChapterId: Int,
    @SerializedName("selfVisible")
    val selfVisible: Int,
    @SerializedName("shareDate")
    val shareDate: Long,
    @SerializedName("shareUser")
    val shareUser: String,
    @SerializedName("superChapterId")
    val superChapterId: Int,
    @SerializedName("superChapterName")
    val superChapterName: String,
    @SerializedName("tags")
    val tags: List<Tag>,
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: Int,
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("visible")
    val visible: Int,
    @SerializedName("zan")
    val zan: Int
)

// 文章标签
@Keep
data class Tag(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)

data class Article(
    @SerializedName("title")
    val title: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("image_url")
    val imageUrl: String,
)
