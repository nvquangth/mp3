package com.bt.mp3.data.network.volley

import android.content.Context
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bt.mp3.data.BuildConfig
import com.bt.mp3.data.encryption.Encryption
import com.bt.mp3.data.model.HomePageResponseEntity
import com.google.gson.Gson
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class HomeVolleyApiImpl @Inject constructor(
    private val context: Context,
    private val encryption: Encryption
) : HomeVolleyApi {

    override suspend fun getHomePage(pageNumber: Int?): HomePageResponseEntity = suspendCoroutine {
        val path = "/api/v2/home"
        val query = "version=$apiVersion"
        val signature = encryption.getSignature(
            path = path,
            param = query
        )

        val queue = Volley.newRequestQueue(context)
        val url = "${BuildConfig.BASE_URL}$path?version=$apiVersion&ctime=${signature.time}&apiKey=$apiKey&sig=${signature.sig}&page=$pageNumber"
        val stringRequest = object : StringRequest(
            Method.GET,
            url,
            Response.Listener<String> { response ->

                it.resume(Gson().fromJson(response, HomePageResponseEntity::class.java))
            },
            Response.ErrorListener { error ->
                it.resume(HomePageResponseEntity())
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {

                return mapOf(
                    "Cookie" to "zmp3_rqid=MHwyMDMdUngMjEwLjE0My4xMTd8WeBnVsWeBHwxNjEyMjQ4MjkxODQy"
                ).toMutableMap()
            }
        }

        stringRequest.retryPolicy = DefaultRetryPolicy(30 * 1000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        queue.add(stringRequest)
    }
}
