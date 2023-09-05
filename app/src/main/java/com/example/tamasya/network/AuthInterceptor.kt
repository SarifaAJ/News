package com.example.tamasya.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val token: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .method(original.method, original.body)
            .build()
        return chain.proceed(request)
    }
}