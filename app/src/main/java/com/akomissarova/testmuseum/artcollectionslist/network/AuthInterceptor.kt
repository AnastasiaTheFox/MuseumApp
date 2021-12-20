package com.akomissarova.testmuseum.artcollectionslist.network

import com.akomissarova.testmuseum.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var req = chain.request()
        val url = req.url.newBuilder().addQueryParameter("key", BuildConfig.MUSEUM_API_KEY).build()
        req = req.newBuilder().url(url).build()
        return chain.proceed(req)
    }
}