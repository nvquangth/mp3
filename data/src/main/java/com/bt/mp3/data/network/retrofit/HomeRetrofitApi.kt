package com.bt.mp3.data.network.retrofit

import com.bt.mp3.data.model.HomePageResponseEntity
import com.bt.mp3.data.network.HomeRemoteDataSource
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeRetrofitApi: HomeRemoteDataSource {

    @GET("/api/v2/home")
    override suspend fun getHomePage( @Query("page") pageNumber: Int? ): HomePageResponseEntity
}

