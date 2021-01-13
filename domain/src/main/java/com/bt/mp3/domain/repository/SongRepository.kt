package com.bt.mp3.domain.repository

import com.bt.mp3.entity.Song

interface SongRepository {

    suspend fun getRecentSongs(): List<Song>
}
