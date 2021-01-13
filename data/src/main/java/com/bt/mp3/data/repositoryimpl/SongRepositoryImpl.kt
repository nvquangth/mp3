package com.bt.mp3.data.repositoryimpl

import com.bt.mp3.data.model.SongEntityMapper
import com.bt.mp3.data.network.SongApi
import com.bt.mp3.domain.repository.SongRepository
import com.bt.mp3.entity.Song
import javax.inject.Inject

class SongRepositoryImpl @Inject constructor(
    private val api: SongApi,
    private val songEntityMapper: SongEntityMapper
) : SongRepository {
    override suspend fun getRecentSongs(): List<Song> {
        return api.getRecentSong().map { songEntityMapper.mapToDomain(it) }
    }
}
