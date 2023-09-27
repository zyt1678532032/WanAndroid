package com.example.wanandroid.ui.dashboard

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentDashboardBinding
import com.example.wanandroid.ui.home.MessageEvent
import com.example.wanandroid.util.ext.viewBinding
import org.greenrobot.eventbus.EventBus

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private val binding by viewBinding<FragmentDashboardBinding>()
    private val dashboardViewModel by activityViewModels<DashboardViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        binding.textDashboard.setOnClickListener {
            EventBus.getDefault().post(MessageEvent())
        }
    }

}