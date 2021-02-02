package com.bt.mp3.data.network.retrofit

import com.bt.mp3.data.model.SongResponseEntity
import com.bt.mp3.data.model.SuggestionPlaylistResponseEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaylistApi {

    @GET("/api/v2/playlist/getDetail")
    suspend fun getDetailPlaylist(@Query("id") playlistId: String?): SongResponseEntity

    @GET("/api/v2/playlist/getSectionBottom")
    suspend fun getSuggestionPlaylist(@Query("id") playlistId: String?): SuggestionPlaylistResponseEntity
}
