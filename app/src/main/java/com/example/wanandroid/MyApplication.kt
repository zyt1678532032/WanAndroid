package com.example.wanandroid

import android.app.Application
import com.example.myapp.MyEventBusIndex
import com.example.wanandroid.di.PexelsResourceModule
import com.example.wanandroid.di.PexelsResourceModuleImpl
import com.example.wanandroid.di.WanAndroidModule
import com.example.wanandroid.di.WanAndroidModuleImpl
import com.pluto.Pluto
import com.pluto.plugins.layoutinspector.PlutoLayoutInspectorPlugin
import org.greenrobot.eventbus.EventBus


class MyApplication : Application() {

    companion object {
        lateinit var wanAndroidModule: WanAndroidModule
        lateinit var pexelsResourceModule: PexelsResourceModule

        // 图片资源 https://www.pexels.com/api/documentation/
        const val PEXELS_TOKEN = "I4A2jQ10vvXMK6WUxRp292waX4GSjY6e2S2qqtkJfTAZWsCGRGzKbSmv"
    }

    override fun onCreate() {
        super.onCreate()
        EventBus.builder().addIndex(MyEventBusIndex()).installDefaultEventBus()
        wanAndroidModule = WanAndroidModuleImpl(this)
        pexelsResourceModule = PexelsResourceModuleImpl(this)
        Pluto.Installer(this)
            .addPlugin(PlutoLayoutInspectorPlugin())
            .install()
    }
}