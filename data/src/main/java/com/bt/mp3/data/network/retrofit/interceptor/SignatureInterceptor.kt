package com.bt.mp3.data.network.retrofit.interceptor

import com.bt.mp3.data.base.SimpleResponse
import com.bt.mp3.data.encryption.Encryption
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.Response
import java.nio.charset.Charset
import javax.inject.Inject

class SignatureInterceptor @Inject constructor(
    private val encryption: Encryption
) : Interceptor {

    companion object {

        const val API_VERSION = "1.0.19"
        const val API_KEY = "kI44ARvPwaqL7v0KuDSM0rGORtdY1nnw"
        const val RETRY_MAX = 10
    }

    override fun intercept(chain: Interceptor.Chain): Response {

        var request = chain.request()
        val originUrl = request.url
        val path = request.url.pathSegments.map { "/$it" }.joinToString("") { it }
        val query = request.url.query?.replace("&", "") + "version=$API_VERSION"
        val signature = encryption.getSignature(
            path = path,
            param = query
        )

        val url = originUrl.newBuilder()
            .addQueryParameter("version", API_VERSION)
            .addQueryParameter("ctime", signature.time.toString())
            .addQueryParameter("sig", signature.sig)
            .addQueryParameter("apiKey", API_KEY)
            .build()

        request = request.newBuilder().url(url).build()

        var response = chain.proceed(request)

        var tryCount = 0

        while ((response.isSuccessful) && tryCount < RETRY_MAX) {
            tryCount++

            val source = response.body?.source()?.apply {
                request(Long.MAX_VALUE)
            }

            val buffer = source?.buffer
            val responseBodyString = buffer?.clone()?.readString(Charset.forName("UTF-8"))
            val simpleResponse = Gson().fromJson(responseBodyString, SimpleResponse::class.java)

            // cookie invalid
            if (simpleResponse.errorCode == -201) {
                if (response.headers("Set-Cookie").isNotEmpty()) {
                    val cookies: HashSet<String> = HashSet()
                    for (header in response.headers("Set-Cookie")) {
                        cookies.add(header)
                    }

                    // full cookie
                    // zmp3_rqid=MHwyMDMdUngMjEwLjE0My4xMTd8WeBnVsWeBHwxNjEyNDI0NzYzMTkx; Path=/; Domain=.zingmp3.vn; Expires=Sat, 10 Apr 2021 07:31:33 GMT;
                    val newCookie = cookies.elementAt(0).split(";")[0]

                    request = request.newBuilder().apply {
                        removeHeader("Cookie")
                        addHeader("Cookie", newCookie)
                        url(url)
                    }.build()

                    response = chain.proceed(request)
                }
            }

            // signature invalid
            if (simpleResponse.errorCode == -104) {
                val newSignature = encryption.getSignature(
                    path = path,
                    param = query
                )

                val newUrl = originUrl.newBuilder()
                    .addQueryParameter("version", API_VERSION)
                    .addQueryParameter("ctime", newSignature.time.toString())
                    .addQueryParameter("sig", newSignature.sig)
                    .addQueryParameter("apiKey", API_KEY)
                    .build()

                request = request.newBuilder().url(newUrl).build()

                response = chain.proceed(request)
            }
        }
        return response
    }
}
