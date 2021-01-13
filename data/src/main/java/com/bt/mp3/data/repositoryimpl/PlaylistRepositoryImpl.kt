package com.bt.mp3.data.repositoryimpl

import com.bt.mp3.data.extension.mapToCleanException
import com.bt.mp3.data.model.PlaylistEntityMapper
import com.bt.mp3.data.network.PlaylistApi
import com.bt.mp3.domain.repository.PlaylistRepository
import com.bt.mp3.entity.Playlist
import javax.inject.Inject

class PlaylistRepositoryImpl @Inject constructor(
    private val api: PlaylistApi,
    private val playlistEntityMapper: PlaylistEntityMapper
) : PlaylistRepository {
    override suspend fun getPlaylist(type: Int?): List<Playlist> = runCatching {
        api.getPlaylist(type).map { playlistEntityMapper.mapToDomain(it) }
    }.getOrElse {
        throw it.mapToCleanException()
    }
}
