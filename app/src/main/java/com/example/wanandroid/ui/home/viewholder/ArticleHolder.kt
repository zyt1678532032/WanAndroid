package com.example.wanandroid.ui.home.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ArticleHolderLayoutBinding
import com.example.wanandroid.domain.bean.Article

class ArticleHolder(private val binding: ArticleHolderLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
    val rootView = binding.root

    fun bindItemData(article: Article) {
        binding.articleTitle.text = article.title
        binding.articleAuthor.text = article.author
        Glide.with(rootView)
            .load(article.imageUrl)
            .into(binding.articleImage)
    }
}