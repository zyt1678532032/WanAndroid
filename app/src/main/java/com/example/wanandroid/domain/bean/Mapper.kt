package com.example.wanandroid.domain.bean


fun List<OriginArticle>.toArticle(
    topArticles: List<OriginArticle>,
    pexels: List<PexelPhoto>
): List<Article> {
    val articles = mutableListOf<Article>()
    val normalArticles = this

    for (j in topArticles.indices) {
        articles += Article(
            title = topArticles[j].title,
            author = topArticles[j].author.ifEmpty { "匿名作者" },
            imageUrl = pexels[j].src?.original!!,
            link = topArticles[j].link,
            isTop = true
        )
    }

    var indexForNormalArticle = topArticles.size
    for (i in normalArticles.indices) {
        articles += Article(
            title = normalArticles[i].title,
            author = normalArticles[i].author.ifEmpty { "匿名作者" },
            imageUrl = pexels[indexForNormalArticle++].src?.original!!,
            link = normalArticles[i].link,
            isTop = false
        )
    }
    return articles
}

