package com.bt.mp3.data.network

import com.bt.mp3.data.model.HomePageResponseEntity

interface HomeRemoteDataSource: RemoteDataSource {

    suspend fun getHomePage(pageNumber: Int?): HomePageResponseEntity
}