package com.example.wanandroid.ui.home

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider

class TestDialogFragment(@LayoutRes resId: Int) : DialogFragment(resId) {

    private val homeViewModel by lazy {
        // ViewModelProvider(requireParentFragment())[HomeViewModel::class.java]
        ViewModelProvider(requireActivity())[HomeViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.text.observe(viewLifecycleOwner){

        }

    }

}