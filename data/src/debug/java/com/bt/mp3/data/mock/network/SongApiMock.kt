package com.bt.mp3.data.mock.network

import com.bt.mp3.data.mock.factory.SongEntityFactory
import com.bt.mp3.data.model.SongEntity
import com.bt.mp3.data.network.SongApi

class SongApiMock : SongApi {

    override suspend fun getRecentSong(): List<SongEntity> = SongEntityFactory.createSongEntities()
}
