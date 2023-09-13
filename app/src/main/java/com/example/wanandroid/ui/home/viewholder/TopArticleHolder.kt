package com.example.wanandroid.ui.home.viewholder;

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ViewholderTopArticleInnerItemLayoutBinding
import com.example.myapplication.databinding.ViewholderTopArticleLayoutBinding
import com.example.wanandroid.domain.bean.Article

class TopArticleHolder(
    private val binding: ViewholderTopArticleLayoutBinding,
    private var topArticleItemCallback: ((Article) -> Unit)? = null
) : RecyclerView.ViewHolder(binding.root) {

    fun bindItemData(articles: List<Article>) {
        val adapter = TopArticleItemAdapter(topArticleItemCallback)
        adapter.topArticleItems = articles
        binding.recycleView.adapter = adapter
        binding.recycleView.layoutManager = LinearLayoutManager(binding.root.context).apply {
            orientation = LinearLayoutManager.HORIZONTAL
        }
    }
}

internal class TopArticleItemHolder(
    private val binding: ViewholderTopArticleInnerItemLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {

    val rootViewOfTopItem = binding.root

    fun bindItemData(article: Article) {
        binding.articleTitle.text = article.title
        binding.articleAuthor.text = article.author
        Glide.with(rootViewOfTopItem)
            .load(article.imageUrl)
            .centerCrop() // 居中裁剪图像
            .into(binding.articleImage)
        rootViewOfTopItem.context
    }

}

internal class TopArticleItemAdapter(
    private val topArticleItemCallback: ((topArticle: Article) -> Unit)? = null
) : RecyclerView.Adapter<TopArticleItemHolder>() {

    var topArticleItems: List<Article> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopArticleItemHolder {
        val binding =
            ViewholderTopArticleInnerItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return TopArticleItemHolder(binding)
    }

    override fun getItemCount(): Int = topArticleItems.size

    override fun onBindViewHolder(holder: TopArticleItemHolder, position: Int) {
        holder.bindItemData(topArticleItems[position])
        holder.rootViewOfTopItem.setOnClickListener {
            topArticleItemCallback?.invoke(topArticleItems[position])
        }
    }

}
