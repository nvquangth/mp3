package com.bt.mp3.data.network

import com.bt.mp3.data.model.SongEntity
import com.bt.mp3.data.model.SongResponseEntity
import com.bt.mp3.data.model.StreamResponseEntity
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface SongApi {

    suspend fun getRecentSong(): List<SongEntity>

    @GET("/api/v2/song/getInfo")
    suspend fun getDetailSong(@Query("id") songId: String): SongResponseEntity

    @GET("/api/v2/song/getStreaming")
    suspend fun getStreamSong(@Query("id") songId: String): StreamResponseEntity

    @GET("https://api.zingmp3.vn/v1/song/core/get/detail")
    suspend fun getDetailSong2(@QueryMap map: Map<String, String>)
}
