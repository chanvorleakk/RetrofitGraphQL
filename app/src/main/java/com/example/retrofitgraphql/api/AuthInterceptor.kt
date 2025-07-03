package com.example.retrofitgraphql.api

import android.content.Context
import com.example.retrofitgraphql.preference.UserSession
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = UserSession(context).getToken()
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("x-platform", "android")
            .addHeader("x-udid", "123")
        if (!token.isNullOrEmpty()) {
            requestBuilder.addHeader("Authorization", "Bearer $token")
        }

        return chain.proceed(requestBuilder.build())
    }
}

