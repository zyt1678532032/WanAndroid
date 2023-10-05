package com.example.wanandroid.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivitySecondLayoutBinding
import com.example.wanandroid.ui.home.MessageEvent
import com.example.wanandroid.util.ext.viewBinding
import org.greenrobot.eventbus.EventBus


class SecondActivity : AppCompatActivity() {

    private val bind by viewBinding<ActivitySecondLayoutBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bind.btn.setOnClickListener {
            EventBus.getDefault().post(MessageEvent())
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("SecondActivity", "onDestroy")
    }
}