package com.bt.mp3.domain.repository

import com.bt.mp3.entity.Playlist

interface PlaylistRepository {

    suspend fun getPlaylist(type: Int?): List<Playlist>
}
