package com.bt.mp3.domain.usecase

import com.bt.mp3.domain.extension.mapToCleanException
import com.bt.mp3.domain.repository.PlaylistRepository
import com.bt.mp3.entity.Playlist
import com.bt.mp3.entity.exception.CleanException
import com.bt.mp3.entity.exception.CleanExceptionType
import javax.inject.Inject

open class GetPlaylistUseCase @Inject constructor(
    private val repository: PlaylistRepository
) : UseCase<GetPlaylistUseCase.Param, List<Playlist>>() {

    data class Param(
        val type: Int
    ) : UseCase.Param()

    override suspend fun execute(param: Param?): List<Playlist> = runCatching {
        param?.let {
            repository.getPlaylist(it.type)
        } ?: throw CleanException(CleanExceptionType.PARAM_NULL)
    }.getOrElse {
        throw it.mapToCleanException()
    }
}
