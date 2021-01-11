package com.bt.mp3.data.mock.network

import com.bt.mp3.data.mock.factory.PlaylistEntityFactory
import com.bt.mp3.data.model.PlaylistEntity
import com.bt.mp3.data.network.PlaylistApi

class PlaylistApiMock : PlaylistApi {
    override suspend fun getPlaylist(type: Int?): List<PlaylistEntity> = PlaylistEntityFactory.createPlaylistEntities()
}
