package com.example.wanandroid

import android.app.Application
import com.example.wanandroid.util.network.calladapter.FlowCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://www.wanandroid.com/"
    private var retrofit: Retrofit? = null

    @Synchronized
    fun getRetrofitClient(application: Application): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(FlowCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}

object PexelsRetrofitClient {

    private const val BASE_URL = "https://api.pexels.com/"
    private var retrofit: Retrofit? = null

    @Synchronized
    fun getRetrofitClient(application: Application): Retrofit {
        val client = OkHttpClient.Builder()
            .addInterceptor {
                val request = it.request().newBuilder().header(
                    "Authorization", application.pexelsToken
                ).build()
                it.proceed(request)
            }.build()

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(FlowCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
        return retrofit!!
    }
}