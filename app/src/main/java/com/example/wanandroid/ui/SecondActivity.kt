package com.example.wanandroid.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivitySecondBinding
import com.example.wanandroid.ui.home.MessageEvent
import org.greenrobot.eventbus.EventBus


class SecondActivity : AppCompatActivity() {

    private lateinit var bind: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivitySecondBinding.inflate(layoutInflater)
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