package com.bt.mp3.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import com.bt.base.extension.mapToCleanException
import com.bt.base.ui.BaseActivity
import com.bt.mp3.R
import com.bt.mp3.databinding.ActivityMainBinding
import com.bt.mp3.extension.setupWithNavControllerKeepState
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zing.crypto.Crypto
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.QueryMap
import java.security.KeyStore
import java.util.*
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager
import kotlin.collections.HashMap


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val viewModel: MainViewModel by viewModels()

    override val sharedViewModel: MainSharedViewModel by viewModels()

    override val layoutRes: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            setupBottomNav()
        }

        System.loadLibrary("zcrypto")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNav()
    }

    private fun setupBottomNav() {

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavView)
        val navGraphIds = listOf(
            R.navigation.nav_my_music,
            R.navigation.nav_discover,
            R.navigation.nav_chart,
            R.navigation.nav_radio,
            R.navigation.nav_feed
        )

        bottomNavigationView.setupWithNavControllerKeepState(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.navHostFragment,
            intent = intent
        )
    }

    suspend fun checkIP() {

        val jsonObject = JSONObject().apply {
            put("timestamp", System.currentTimeMillis())
        }

        val hashMap = HashMap<String, String>()

        hashMap["data"] = jsonObject.toString()
        hashMap["sig"] = Crypto.a(jsonObject)

        getSongApi().checkIp(hashMap)
    }

    suspend fun getDetailSong2(songId: String) = runCatching {
        val hashMap = HashMap<String, String>()
        hashMap["id"] = "ZW9DFW8O"
        val hashMap2 = HashMap<String, String>()
        C4(hashMap, hashMap2)

        hashMap2["appVersion"] = "21.01.01"
        hashMap2["deviceId"] = "A74CA80985C27E71"
        hashMap2["os"] = "Android"
        hashMap2["osVersion"] = "10"
        getSongApi().getDetailSong2(hashMap2)
        val x = 2
    }.getOrElse {
        throw it.mapToCleanException()
    }

//    fun A4(str: String, hashMap: HashMap<String, String>) {
//        hashMap["cTime"] = str
//        hashMap["appVersion"] = "21.01.01"
//        hashMap["deviceId"] = "A74CA80985C27E71"
//        hashMap["os"] = "Android"
//        hashMap["osVersion"] = "10"
//    }

    fun D4(hashMap: HashMap<String, String>, hashMap2: HashMap<String, String>, hashMap3: HashMap<String, String>) {
        val valueOf = System.currentTimeMillis().toString()

        hashMap2["cTime"] = valueOf
        hashMap2["appVersion"] = "21.01.01"
        hashMap2["deviceId"] = "A74CA80985C27E71"
        hashMap2["os"] = "android"
        hashMap2["osVersion"] = "29"

        for ((key, value) in hashMap.entries) {
            hashMap2[key] = value
        }
        hashMap3["sig"] = Crypto.b(hashMap2)
        hashMap3["cTime"] = valueOf
        for ((key, value) in hashMap.entries) {
            hashMap3[key] = value
        }
    }

    fun C4(hashMap: HashMap<String, String>, hashMap2: HashMap<String, String>) {
        D4(hashMap, HashMap(), hashMap2)
    }

    fun getSongApi(): SongApi = setupRetrofit().create(SongApi::class.java)

    private fun setupRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.zingmp3.vn/")
            .client(OkHttpClient.Builder().apply {

                val trustManagerFactory: TrustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
                trustManagerFactory.init(null as KeyStore?)
                val trustManagers: Array<TrustManager> = trustManagerFactory.getTrustManagers()
                check(!(trustManagers.size != 1 || trustManagers[0] !is X509TrustManager)) { "Unexpected default trust managers:" + Arrays.toString(trustManagers) }
                val trustManager = trustManagers[0] as X509TrustManager
                val sslContext = SSLContext.getInstance("SSL")
                sslContext.init(null, arrayOf<TrustManager>(trustManager), null)
                val sslSocketFactory: SSLSocketFactory = sslContext.socketFactory


//                val trustAllCerts: Array<TrustManager> = arrayOf<TrustManager>(
//                    object : X509TrustManager {
//                        override fun checkClientTrusted(chain: Array<X509Certificate?>?, authType: String?) {}
//                        override fun checkServerTrusted(chain: Array<X509Certificate?>?, authType: String?) {}
//                        override fun getAcceptedIssuers(): Array<X509Certificate> {
//                            return arrayOf()
//                        }
//                    }
//                )
//                val sslContext: SSLContext = SSLContext.getInstance("SSL")
//                sslContext.init(null, trustAllCerts, SecureRandom())


                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                addInterceptor(object : Interceptor {
                    override fun intercept(chain: Interceptor.Chain) = chain.run {
                        proceed(
                            request().newBuilder()
                                .addHeader("appVersion", "21.01.01")
                                .addHeader("deviceId", "A74CA80985C27E71")
                                .addHeader("os", "android")
                                .addHeader("osVersion", "29")
                                .build()
                        )
                    }

                })

                sslSocketFactory(sslSocketFactory, trustManager)
                hostnameVerifier(HostnameVerifier { _, _ -> true })

            }.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

interface SongApi {

    @GET("v1/song/core/get/detail")
    suspend fun getDetailSong2(@QueryMap map: Map<String, String>)

    @GET("isIPVietNam")
    suspend fun checkIp(@QueryMap map: Map<String, String>)
}
