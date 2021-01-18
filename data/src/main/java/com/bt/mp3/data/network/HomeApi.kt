package com.bt.mp3.data.network

import com.bt.mp3.data.model.HomePageResponseEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApi {

    @GET("/api/v2/home")
    suspend fun getHomePage(@Query("page") pageNumber: Int?): HomePageResponseEntity
}
