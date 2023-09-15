package com.example.wanandroid.util

import android.annotation.SuppressLint
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager

fun createSSLSocketFactory(): SSLSocketFactory? {
    var sSLSocketFactory: SSLSocketFactory? = null
    try {
        val sc = SSLContext.getInstance("TLS")
        sc.init(null, arrayOf(TrustAllManager()), SecureRandom())
        sSLSocketFactory = sc.socketFactory
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return sSLSocketFactory
}

@SuppressLint("CustomX509TrustManager")
internal class TrustAllManager : X509TrustManager {

    override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {

    }

    override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {

    }

    override fun getAcceptedIssuers(): Array<X509Certificate> {
        return arrayOf()
    }
}