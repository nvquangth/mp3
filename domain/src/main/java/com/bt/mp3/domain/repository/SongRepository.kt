package com.bt.mp3.domain.repository

import com.bt.mp3.entity.Song
import com.bt.mp3.entity.Stream

interface SongRepository {

    suspend fun getRecentSongs(): List<Song>

    suspend fun getDetailSong(songId: String): Song

    suspend fun getStreamSong(songId: String): Stream

    suspend fun getDetailSong2(songId: String)
}
