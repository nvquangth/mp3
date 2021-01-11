package com.bt.mp3.data.network

import com.bt.mp3.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitBuilder @Inject constructor() {

    companion object HttpClient {
        const val CONNECTION_TIMEOUT = 10L
        const val READ_TIMEOUT = 10L
        const val WRITE_TIMEOUT = 10L
    }

    private var connectionTimeout = CONNECTION_TIMEOUT
    private var readTimeout = READ_TIMEOUT
    private var writeTimeout = WRITE_TIMEOUT
    private var okHttpClientBuilder: OkHttpClient.Builder? = null
    private var interceptors = mutableListOf<Interceptor>()
    private var logEnable: Boolean = BuildConfig.DEBUG
    private var isSupportAuthorization = false
    private var baseUrl = BuildConfig.BASE_URL

    /**
     * Customize timeout
     *
     * @param connectionTimeout timeout for connection OkHttp client
     * @param readTimeout timeout for read data
     * @param writeTimeout timeout for write data
     */
    fun setTimeout(
        connectionTimeout: Long = CONNECTION_TIMEOUT,
        readTimeout: Long = READ_TIMEOUT,
        writeTimeout: Long = WRITE_TIMEOUT
    ): RetrofitBuilder {
        this.connectionTimeout = connectionTimeout
        this.readTimeout = readTimeout
        this.writeTimeout = writeTimeout
        return this
    }

    /**
     * Customize OkHttp client builder
     *
     * @param okHttpClientBuilder
     */
    fun setOkHttpClientBuilder(okHttpClientBuilder: OkHttpClient.Builder): RetrofitBuilder {
        this.okHttpClientBuilder = okHttpClientBuilder
        return this
    }

    /**
     * Add custom interceptor for OkHttp client
     *
     * @param interceptor one or more interceptor
     */
    fun addInterceptors(vararg interceptor: Interceptor): RetrofitBuilder {
        interceptors.addAll(interceptor)
        return this
    }

    /**
     * Enable or disable logging information: header, body...
     *
     * @param enable
     */
    fun loggingEnable(enable: Boolean): RetrofitBuilder {
        logEnable = enable
        return this
    }

    /**
     * Enable or disable supporting authorization
     *
     * @param enable
     */
    fun supportAuthorization(enable: Boolean): RetrofitBuilder {
        isSupportAuthorization = enable
        return this
    }

    /**
     * Customize url
     *
     * @param url
     */
    fun setBaseUrl(url: String): RetrofitBuilder {
        baseUrl = url
        return this
    }

    /**
     * Make a retrofit
     */
    fun build(): Retrofit {
        val clientBuilder = (okHttpClientBuilder ?: OkHttpClient.Builder()).apply {
            connectTimeout(connectionTimeout, TimeUnit.SECONDS)
            readTimeout(readTimeout, TimeUnit.SECONDS)
            writeTimeout(writeTimeout, TimeUnit.SECONDS)

            if (logEnable) {
                addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                )
            }

            interceptors.forEach {
                addInterceptor(it)
            }
        }

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(clientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
