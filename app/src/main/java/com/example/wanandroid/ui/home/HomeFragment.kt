package com.example.wanandroid.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.wanandroid.MyApplication
import com.example.wanandroid.ui.ArticleDetailActivity
import com.example.wanandroid.ui.home.adapter.ArticleAdapter
import com.example.wanandroid.util.network.RequestStatus
import com.example.wanandroid.util.viewModelFactory


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
        articleAdapter = ArticleAdapter(
            itemClickListener = { article ->
                val activity = requireActivity()

                val intent = Intent(activity, ArticleDetailActivity::class.java)
                intent.putExtra("link", article.link)
                activity.startActivity(intent)
            },
            menuItemClickListener = {
                Toast.makeText(this@HomeFragment.context, "已收藏", Toast.LENGTH_SHORT).show()
            }
        )

        binding.recycleView.run {
            adapter = articleAdapter
            layoutManager = LinearLayoutManager(context)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    articleAdapter.setScrollingMenu(null)
                }
            })
        }

        homeViewModel.articles.observe(viewLifecycleOwner) {
            articleAdapter.articles = it
        }

        homeViewModel.status.observe(viewLifecycleOwner){
            if (it == RequestStatus.LOADING) {
                binding.shimmerTopArticleContainer.isVisible = true
                binding.shimmerArticleContainer.isVisible = true
            }
            if (it == RequestStatus.SUCCESS) {
                binding.shimmerTopArticleContainer.isVisible = false
                binding.shimmerArticleContainer.isVisible = false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class MessageEvent