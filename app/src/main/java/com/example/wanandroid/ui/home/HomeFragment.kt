package com.example.wanandroid.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.wanandroid.MyApplication
import com.example.wanandroid.ui.home.adapter.ArticleAdapter
import com.example.wanandroid.util.viewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val homeViewModel by activityViewModels<HomeViewModel> {
        viewModelFactory {
            HomeViewModel(
                MyApplication.wanAndroidModule.wanAndroidRepository,
                MyApplication.pexelsResourceModule.pexelsResourceRepository
            )
        }
    }

    private lateinit var articleAdapter: ArticleAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        articleAdapter = ArticleAdapter()
        binding.recycleView.adapter = articleAdapter
        binding.recycleView.layoutManager = LinearLayoutManager(context)

        lifecycleScope.launch {
            homeViewModel.getArticles().collectLatest {
                articleAdapter.data = it
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class MessageEvent