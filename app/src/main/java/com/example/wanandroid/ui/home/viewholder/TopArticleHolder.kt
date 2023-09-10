package com.example.wanandroid.ui.home.viewholder;

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.TopArticleHolerLayoutBinding
import com.example.wanandroid.domain.bean.Article

class TopArticleHolder(private val binding: TopArticleHolerLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
    val rootView = binding.root

    fun bindItemData(article: Article) {
        binding.articleTitle.text = article.title
        binding.articleAuthor.text = article.author
        Glide.with(rootView)
            .load(article.imageUrl)
            .centerCrop() // 居中裁剪图像
            .into(binding.articleImage)
    }
}