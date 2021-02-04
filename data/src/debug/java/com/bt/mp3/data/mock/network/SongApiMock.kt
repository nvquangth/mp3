package com.bt.mp3.data.mock.network

import com.bt.mp3.data.mock.factory.FileResourcesUtils
import com.bt.mp3.data.model.SongEntity
import com.bt.mp3.data.model.SongResponseEntity
import com.bt.mp3.data.model.StreamResponseEntity
import com.bt.mp3.data.network.SongApi
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SongApiMock : SongApi {

    override suspend fun getRecentSong(): List<SongEntity> {
        val data = FileResourcesUtils().getDataStr("songs_data_sample.json")

        return Gson().fromJson(data, object : TypeToken<List<SongEntity>>() {}.type)
    }

    override suspend fun getDetailSong(songId: String): SongResponseEntity {
        val data = FileResourcesUtils().getDataStr("detail_song_data_sample.json")

        return Gson().fromJson(data, SongResponseEntity::class.java)
    }

    override suspend fun getStreamSong(songId: String): StreamResponseEntity {
        val data = FileResourcesUtils().getDataStr("stream_song_data_sample.json")

        return Gson().fromJson(data, StreamResponseEntity::class.java)
    }

    override suspend fun getDetailSong2(map: Map<String, String>) {

    }
}
