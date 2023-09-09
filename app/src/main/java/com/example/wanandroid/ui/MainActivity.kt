package com.example.wanandroid.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.wanandroid.ui.home.MessageEvent
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // 注册Eventbus
        EventBus.getDefault().register(this)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onStart() {
        super.onStart()
        Log.i("onStart", "-----")
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = false)
    fun customEvent(messageEvent: MessageEvent) {
        Log.i("MainActivity", "customEvent:---$messageEvent")
    }


    @Subscribe(threadMode = ThreadMode.MAIN,sticky = false)
    fun customEvent2(messageEvent: MessageEvent) {
        Log.i("MainActivity", "customEvent2:---$messageEvent")
    }


    // override fun onStop() {
    //     super.onStop()
    //     EventBus.getDefault().unregister(this)
    //     Log.i("onStop", "-----")
    // }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
        Log.i("onStop", "-----")
    }


}