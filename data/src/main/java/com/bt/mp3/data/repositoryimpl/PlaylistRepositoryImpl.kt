package com.bt.mp3.data.repositoryimpl

import com.bt.mp3.data.extension.mapToCleanException
import com.bt.mp3.data.model.SongEntityMapper
import com.bt.mp3.data.network.PlaylistApi
import com.bt.mp3.domain.repository.PlaylistRepository
import com.bt.mp3.entity.Song
import com.bt.mp3.entity.exception.CleanException
import com.bt.mp3.entity.exception.CleanExceptionType
import javax.inject.Inject

class PlaylistRepositoryImpl @Inject constructor(
    private val api: PlaylistApi,
    private val playlistEntityMapper: SongEntityMapper
) : PlaylistRepository {
    override suspend fun getDetailPlaylist(playlistId: String): Song = runCatching {
        api.getDetailPlaylist(playlistId).data?.let { playlistEntityMapper.mapToDomain(it) } ?: throw CleanException(CleanExceptionType.DATA_NULL_OR_EMPTY)
    }.getOrElse {
        throw it.mapToCleanException()
    }

    override suspend fun getSuggestionPlaylist(playlistId: String): List<Song> = runCatching {
        api.getSuggestionPlaylist(playlistId).data?.map { playlistEntityMapper.mapToDomain(it) } ?: throw CleanException(CleanExceptionType.DATA_NULL_OR_EMPTY)
    }.getOrElse {
        throw it.mapToCleanException()
    }
}
