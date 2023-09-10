package com.example.wanandroid.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication.databinding.ViewholderArticleLayoutBinding
import com.example.myapplication.databinding.ViewholderTopArticleLayoutBinding
import com.example.wanandroid.domain.bean.Article
import com.example.wanandroid.ui.home.viewholder.ArticleHolder
import com.example.wanandroid.ui.home.viewholder.TopArticleHolder

class ArticleAdapter : RecyclerView.Adapter<ViewHolder>() {

    var data: List<Article> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    companion object {
        const val TYPE_NORMAL_ARTICLE = 0
        const val TYPE_TOP_ARTICLE = 1 // 置顶文章
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            TYPE_NORMAL_ARTICLE -> {
                val binding = ViewholderArticleLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ArticleHolder(binding)
            }

            else -> {
                val binding = ViewholderTopArticleLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                TopArticleHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_TOP_ARTICLE -> {
                (holder as TopArticleHolder).run {
                    bindItemData(data[position])
                    rootView.setOnClickListener {
                        // TODO: 拉起Activity
                    }
                }
            }

            TYPE_NORMAL_ARTICLE -> {
                (holder as ArticleHolder).run {
                    bindItemData(data[position])
                    rootView.setOnClickListener {
                        // TODO: 拉起Activity
                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (data[position].isTop) {
            return TYPE_TOP_ARTICLE
        }
        return TYPE_NORMAL_ARTICLE
    }

}