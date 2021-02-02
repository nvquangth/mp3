package com.bt.mp3.data.interceptor

import com.bt.mp3.data.encryption.Encryption
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


class SignatureInterceptor @Inject constructor(
    private val encryption: Encryption
) : Interceptor {

    companion object {

        const val API_VERSION = "1.0.19"
        const val API_KEY = "kI44ARvPwaqL7v0KuDSM0rGORtdY1nnw"
    }

    override fun intercept(chain: Interceptor.Chain): Response {

        var request = chain.request()
        val path = request.url.pathSegments.map { "/$it" }.joinToString("") { it }
        val query = request.url.query?.replace("&", "") + "version=$API_VERSION"
        val signature = encryption.getSignature(
            path = path,
            param = query
        )

        val url = request.url.newBuilder()
            .addQueryParameter("version", API_VERSION)
            .addQueryParameter("ctime", signature.time.toString())
            .addQueryParameter("sig", signature.sig)
            .addQueryParameter("apiKey", API_KEY)
            .build()

        request = request.newBuilder().apply {
            addHeader("Cookie", "zmp3_rqid=MHwyMDMdUngMjEwLjE0My4xMTd8WeBnVsWeBHwxNjEyMjUzOTI2MDI4")
            url(url)
        }.build()

        return chain.proceed(request)
    }
}
