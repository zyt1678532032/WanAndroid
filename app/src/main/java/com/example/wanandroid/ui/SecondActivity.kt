package com.example.wanandroid.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivitySecondLayoutBinding
import com.example.wanandroid.ui.home.MessageEvent
import org.greenrobot.eventbus.EventBus


class SecondActivity : AppCompatActivity() {

    private lateinit var bind: ActivitySecondLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivitySecondLayoutBinding.inflate(layoutInflater)
        setContentView(bind.root)

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