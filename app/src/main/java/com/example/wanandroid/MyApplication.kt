package com.example.wanandroid

import android.app.Application
import com.example.myapp.MyEventBusIndex
import com.example.wanandroid.di.AppModule
import com.example.wanandroid.di.AppModuleImpl
import org.greenrobot.eventbus.EventBus


class MyApplication : Application() {

    companion object {
        lateinit var appModule: AppModule
        // 图片资源 https://www.pexels.com/api/documentation/
        const val PEXELS_TOKEN = "I4A2jQ10vvXMK6WUxRp292waX4GSjY6e2S2qqtkJfTAZWsCGRGzKbSmv"
    }

    override fun onCreate() {
        super.onCreate()
        EventBus.builder().addIndex(MyEventBusIndex()).installDefaultEventBus()
        appModule = AppModuleImpl(this)
    }
}