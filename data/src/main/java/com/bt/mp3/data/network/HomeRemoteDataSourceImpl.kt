package com.bt.mp3.data.network

import com.bt.mp3.data.model.HomePageResponseEntity
import com.bt.mp3.data.network.retrofit.HomeRetrofitApi
import com.bt.mp3.data.network.volley.HomeVolleyApi
import javax.inject.Inject

class HomeRemoteDataSourceImpl @Inject constructor(
    private val homeRetrofitApi: HomeRetrofitApi,
    private val homeVolleyApi: HomeVolleyApi
): HomeRemoteDataSource {

    override suspend fun getHomePage(pageNumber: Int?): HomePageResponseEntity {
        return homeRetrofitApi.getHomePage(pageNumber)
    }
}