package com.example.wanandroid

import android.app.Application
import com.example.myapp.MyEventBusIndex
import com.example.wanandroid.di.AppModule
import com.example.wanandroid.di.AppModuleImpl
import org.greenrobot.eventbus.EventBus


class MyApplication : Application() {

    companion object {
        lateinit var appModule: AppModule
    }

    override fun onCreate() {
        super.onCreate()
        EventBus.builder().addIndex(MyEventBusIndex()).installDefaultEventBus()
        appModule = AppModuleImpl(this)
    }
}