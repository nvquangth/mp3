package com.bt.mp3.data.repositoryimpl

import com.bt.mp3.data.extension.mapToCleanException
import com.bt.mp3.data.model.SongEntityMapper
import com.bt.mp3.data.model.StreamEntityMapper
import com.bt.mp3.data.network.SongApi
import com.bt.mp3.domain.repository.SongRepository
import com.bt.mp3.entity.Song
import com.bt.mp3.entity.Stream
import com.bt.mp3.entity.exception.CleanException
import com.bt.mp3.entity.exception.CleanExceptionType
import javax.inject.Inject

class SongRepositoryImpl @Inject constructor(
    private val api: SongApi,
    private val songEntityMapper: SongEntityMapper,
    private val streamEntityMapper: StreamEntityMapper
) : SongRepository {
    override suspend fun getRecentSongs(): List<Song> = runCatching {
        api.getRecentSong().map { songEntityMapper.mapToDomain(it) }
    }.getOrElse {
        throw it.mapToCleanException()
    }

    override suspend fun getDetailSong(songId: String): Song = runCatching {
        api.getDetailSong(songId).data?.let { songEntityMapper.mapToDomain(it) } ?: throw CleanException(CleanExceptionType.DATA_NULL_OR_EMPTY)
    }.getOrElse {
        throw it.mapToCleanException()
    }

    override suspend fun getStreamSong(songId: String): Stream = runCatching {
        api.getStreamSong(songId).data?.let { streamEntityMapper.mapToDomain(it) } ?: throw CleanException(CleanExceptionType.DATA_NULL_OR_EMPTY)
    }.getOrElse {
        throw it.mapToCleanException()
    }
}
