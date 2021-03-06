package com.example.agoraandroidgsoc20.data.network

import android.content.Context
import android.net.ConnectivityManager
import com.example.agoraandroidgsoc20.utils.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

@Suppress("DEPRECATION")
class NetworkConnectionInterceptor
    @Inject
        constructor(
    context: Context
) : Interceptor {

    private val applicationContext = context.applicationContext
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isConnected())
            throw NoInternetException("Please check your network connection")
        return chain.proceed(chain.request())
    }

    private fun isConnected(): Boolean {
        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.activeNetworkInfo.also {
            return it != null && it.isConnected
        }
    }
}