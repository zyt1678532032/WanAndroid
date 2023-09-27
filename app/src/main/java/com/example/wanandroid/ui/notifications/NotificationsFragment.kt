package com.example.wanandroid.ui.notifications

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentNotificationsBinding
import com.example.wanandroid.util.ext.viewBinding

class NotificationsFragment : Fragment(R.layout.fragment_notifications) {

    private val binding by viewBinding<FragmentNotificationsBinding>()

    private val notificationsViewModel by activityViewModels<NotificationsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val textView: TextView = binding.textNotifications
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
    }

}