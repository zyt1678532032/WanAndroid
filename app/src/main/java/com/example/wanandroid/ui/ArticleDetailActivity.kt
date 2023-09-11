package com.example.wanandroid.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityArticleDetailLayoutBinding

class ArticleDetailActivity: AppCompatActivity() {

    private lateinit var binding: ActivityArticleDetailLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleDetailLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val link = intent.getStringExtra("link") ?: "http://www.baidu.com"
        binding.webView.loadUrl(link)
    }
}