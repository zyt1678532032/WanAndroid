package com.example.wanandroid

import android.app.Application
import android.content.pm.PackageManager
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

// 图片资源 https://www.pexels.com/api/documentation/
val Application.pexelsToken: String
    get() {
        // 初始化 API_KEY(实际内容在local.properties文件中存储)
        val applicationInfo =
            packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA)
        return applicationInfo.metaData.getString("API_KEY_PEXELS").toString()
    }