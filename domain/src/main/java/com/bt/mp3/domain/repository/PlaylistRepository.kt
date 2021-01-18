package com.bt.mp3.domain.repository

import com.bt.mp3.entity.Song

interface PlaylistRepository {

    suspend fun getDetailPlaylist(playlistId: String): Song

    suspend fun getSuggestionPlaylist(playlistId: String): List<Song>
}
