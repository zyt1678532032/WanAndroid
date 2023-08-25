package com.example.wanandroid.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ArticleHolderLayoutBinding

class ArticleAdapter : RecyclerView.Adapter<ArticleHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleHolder {
        val binding =
            ArticleHolderLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleHolder(binding.root)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ArticleHolder, position: Int) {
        TODO("Not yet implemented")
    }

}

class ArticleHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {

}