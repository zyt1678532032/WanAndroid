package com.example.wanandroid

import com.example.wanandroid.util.TrustAllManager
import com.example.wanandroid.util.createSSLSocketFactory
import com.example.wanandroid.util.network.calladapter.FlowCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://www.wanandroid.com/"
    private var retrofit: Retrofit? = null

    private val client = OkHttpClient.Builder()
        .sslSocketFactory(createSSLSocketFactory()!!, TrustAllManager())
        .build()

    @Synchronized
    fun getRetrofitClient(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .client(client)
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

    private val client = OkHttpClient.Builder()
        .sslSocketFactory(createSSLSocketFactory()!!, TrustAllManager())
        .addInterceptor {
            val request = it.request().newBuilder().header(
                "Authorization", MyApplication.PEXELS_TOKEN
            ).build()
            it.proceed(request)
        }.build()

    @Synchronized
    fun getRetrofitClient(): Retrofit {
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