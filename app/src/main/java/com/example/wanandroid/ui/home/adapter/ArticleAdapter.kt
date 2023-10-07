package com.example.wanandroid.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication.databinding.ViewholderArticleLayoutBinding
import com.example.myapplication.databinding.ViewholderTopArticleLayoutBinding
import com.example.wanandroid.domain.bean.Article
import com.example.wanandroid.ui.home.view.protocol.SlidingComponent
import com.example.wanandroid.ui.home.view.protocol.SlidingComponentManager
import com.example.wanandroid.ui.home.viewholder.ArticleHolder
import com.example.wanandroid.ui.home.viewholder.TopArticleHolder

class ArticleAdapter(
    private val itemClickListener: (Article) -> Unit,
    private val menuItemClickListener: () -> Unit
) : RecyclerView.Adapter<ViewHolder>(), SlidingComponentManager {

    private var mSlidingComponent: SlidingComponent? = null

    var articles: List<Article> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    companion object {
        const val TYPE_NORMAL_ARTICLE = 0
        const val TYPE_TOP_ARTICLE = 1 // 置顶文章
    }

    override fun getItemCount(): Int = articles.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            TYPE_TOP_ARTICLE -> {
                val binding = ViewholderTopArticleLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                TopArticleHolder(binding = binding, topArticleItemCallback = itemClickListener)
            }

            else -> {
                val binding = ViewholderArticleLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ArticleHolder(binding = binding, componentManager = this@ArticleAdapter)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_TOP_ARTICLE -> {
                (holder as TopArticleHolder).run {
                    val topArticles = articles.filter { it.isTop }
                    bindItemData(topArticles)
                }
            }

            TYPE_NORMAL_ARTICLE -> {
                (holder as ArticleHolder).run {
                    bindItemData(articles[position])
                    slidingComponent.setContentClickListener {
                        itemClickListener.invoke(articles[position])
                    }
                    menuView.setOnClickListener {
                        menuItemClickListener.invoke()
                        mSlidingComponent?.close() // 组件恢复原位
                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) { // 第一行为置顶文章
            return TYPE_TOP_ARTICLE
        }
        return TYPE_NORMAL_ARTICLE
    }

    override fun closeOpenedComponent() {
        if (mSlidingComponent != null && mSlidingComponent?.isOpened == true) {
            mSlidingComponent?.close()
            mSlidingComponent = null
        }
    }

    override fun setOpenedComponent(slidingComponent: SlidingComponent) {
        mSlidingComponent = slidingComponent
    }

}