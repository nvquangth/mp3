package com.bt.mp3.data.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HeaderInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .addHeader("X-Server", "ZingMp3-api-v25")
            .addHeader("Content-Encoding", "gzip")
            .build()
        return chain.proceed(request)
    }
}