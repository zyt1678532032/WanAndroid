package com.example.wanandroid.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainLayoutBinding
import com.example.wanandroid.ui.home.MessageEvent
import com.example.wanandroid.util.ext.viewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding<ActivityMainLayoutBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 注册Eventbus
        EventBus.getDefault().register(this)
        // 布局检测插件
        // Pluto.open(PlutoLayoutInspectorPlugin.ID)

        val navView: BottomNavigationView = binding.navView

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController
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

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = false)
    fun customEvent(messageEvent: MessageEvent) {
        Log.i("MainActivity", "customEvent:---$messageEvent")
    }


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = false)
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