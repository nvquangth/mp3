package com.bt.mp3.data.network

import com.bt.mp3.data.model.SongEntity

interface SongApi {

    suspend fun getRecentSong(): List<SongEntity>
}
