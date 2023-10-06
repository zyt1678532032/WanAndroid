package com.example.wanandroid.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityArticleDetailLayoutBinding
import com.example.wanandroid.util.ext.viewBinding

class ArticleDetailActivity: AppCompatActivity() {

    private val binding by viewBinding<ActivityArticleDetailLayoutBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val link = intent.getStringExtra("link") ?: "http://www.baidu.com"
        binding.webView.loadUrl(link)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.webView.destroy()
    }
}