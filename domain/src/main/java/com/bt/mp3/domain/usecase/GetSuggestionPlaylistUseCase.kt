package com.bt.mp3.domain.usecase

import com.bt.mp3.domain.extension.mapToCleanException
import com.bt.mp3.domain.repository.PlaylistRepository
import com.bt.mp3.entity.Song
import com.bt.mp3.entity.exception.CleanException
import com.bt.mp3.entity.exception.CleanExceptionType
import javax.inject.Inject

open class GetSuggestionPlaylistUseCase @Inject constructor(
    private val repository: PlaylistRepository
) : UseCase<GetSuggestionPlaylistUseCase.Param, List<Song>>() {

    data class Param(
        val playlistId: String
    ) : UseCase.Param()

    override suspend fun execute(param: Param?): List<Song> = runCatching {
        param?.let {
            repository.getSuggestionPlaylist(it.playlistId)
        } ?: throw CleanException(CleanExceptionType.PARAM_NULL)
    }.getOrElse {
        throw it.mapToCleanException()
    }
}
