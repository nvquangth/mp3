package com.bt.mp3.data.mock.network

import com.bt.mp3.data.mock.factory.FileResourcesUtils
import com.bt.mp3.data.model.SongResponseEntity
import com.bt.mp3.data.model.SuggestionPlaylistResponseEntity
import com.bt.mp3.data.network.PlaylistApi
import com.google.gson.Gson

class PlaylistApiMock : PlaylistApi {

    override suspend fun getDetailPlaylist(playlistId: String?): SongResponseEntity {
        val data = FileResourcesUtils().getDataStr("detail_playlist_data_sample.json")

        return Gson().fromJson(data, SongResponseEntity::class.java)
    }

    override suspend fun getSuggestionPlaylist(playlistId: String?): SuggestionPlaylistResponseEntity {
        val data = FileResourcesUtils().getDataStr("playlist_data_sample.json")

        return Gson().fromJson(data, SuggestionPlaylistResponseEntity::class.java)
    }
}
