package com.example.wanandroid.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ArticleHolderLayoutBinding
import com.example.wanandroid.domain.bean.Article

class ArticleAdapter : RecyclerView.Adapter<ArticleHolder>() {

    var data: List<Article> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleHolder {
        val binding =
            ArticleHolderLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ArticleHolder, position: Int) {
        holder.titleView.text = data[position].title
        holder.authorView.text = data[position].author
        holder.rootView.setOnClickListener {
            // TODO: 拉起Activity
        }
    }

}

class ArticleHolder(binding: ArticleHolderLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
    val rootView = binding.root
    val titleView = binding.title
    val authorView = binding.author
}