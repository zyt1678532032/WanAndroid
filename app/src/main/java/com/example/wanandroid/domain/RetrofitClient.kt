package com.example.wanandroid.domain

import com.example.wanandroid.util.network.calladapter.FlowCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://www.wanandroid.com/"
    private var retrofit: Retrofit? = null

    @Synchronized
    fun getRetrofitClient(): Retrofit {
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

object PexelsRetrofitClient{

    private const val BASE_URL = "https://api.pexels.com/"
    private var retrofit: Retrofit? = null

    @Synchronized
    fun getRetrofitClient(): Retrofit {
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