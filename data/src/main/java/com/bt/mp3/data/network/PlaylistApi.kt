package com.bt.mp3.data.network

import com.bt.mp3.data.model.PlaylistEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaylistApi {

    @GET
    suspend fun getPlaylist(@Query("type") type: Int?): List<PlaylistEntity>
}
