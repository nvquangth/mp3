package com.bt.mp3.data.mock.network

import android.content.Context
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bt.mp3.data.model.HomePageResponseEntity
import com.bt.mp3.data.network.retrofit.HomeRetrofitApi
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class HomeApiMock(
    @ApplicationContext private val context: Context
) : HomeRetrofitApi {

//    override suspend fun getHomePage(pageNumber: Int?, version: String, ctime: String, apiKey: String, sig: String): HomePageResponseEntity {
//        val data = when (pageNumber) {
//            1 -> FileResourcesUtils().getDataStr("home_page_data_sample.json")
//            2 -> FileResourcesUtils().getDataStr("home_page_data_sample_2.json")
//            3 -> FileResourcesUtils().getDataStr("home_page_data_sample_3.json")
//            else -> FileResourcesUtils().getDataStr("home_page_data_sample_4.json")
//        }
//
//        return Gson().fromJson(data, HomePageResponseEntity::class.java)
//    }

    override suspend fun getHomePage(pageNumber: Int?): HomePageResponseEntity = suspendCoroutine {

//        val path = "/api/v2/home"
//        val query = "version=${SignatureInterceptor.API_VERSION}"
//        val signature = encryption.getSignature(
//            path = path,
//            param = query
//        )

        val version = "1.0.19"
        val ctime = "1612175523"
        val apiKey = "kI44ARvPwaqL7v0KuDSM0rGORtdY1nnw"
        val sig = "a85cab53e169b75ffef25c9c03e57300837092eab9eb5fdacc7fb698f202c4fce6ab4ec84ddba046dd26f6e5c570813b9f7751a1aca4090f43e3ee22bb689dd2"

        val queue = Volley.newRequestQueue(context)
        val url = "https://zingmp3.vn/api/v2/home?page=$pageNumber&version=$version&ctime=$ctime&apiKey=$apiKey&sig=$sig"
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
