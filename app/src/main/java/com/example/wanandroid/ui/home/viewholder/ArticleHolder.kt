package com.example.wanandroid.ui.home.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ViewholderArticleLayoutBinding
import com.example.wanandroid.domain.bean.Article
import com.zyt.widgets.views.protocol.SlidingComponentManager

class ArticleHolder(
    private val binding: ViewholderArticleLayoutBinding,
    componentManager: SlidingComponentManager
) : RecyclerView.ViewHolder(binding.root) {

    val slidingComponent = binding.root
    val menuView = binding.menuText

    init {
        // 注入左滑组件管理器
        slidingComponent.slidingComponentManager = componentManager
    }

    fun bindItemData(article: Article) {
        binding.included.articleTitle.text = article.title
        binding.included.articleAuthor.text = article.author
        Glide.with(slidingComponent)
            .load(article.imageUrl)
            .centerCrop()
            .into(binding.included.articleImage)
    }
}