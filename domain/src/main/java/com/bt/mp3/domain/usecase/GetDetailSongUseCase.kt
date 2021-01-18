package com.bt.mp3.domain.usecase

import com.bt.mp3.domain.extension.mapToCleanException
import com.bt.mp3.domain.repository.SongRepository
import com.bt.mp3.entity.Song
import com.bt.mp3.entity.exception.CleanException
import com.bt.mp3.entity.exception.CleanExceptionType
import javax.inject.Inject

class GetDetailSongUseCase @Inject constructor(
    private val repository: SongRepository
) : UseCase<GetDetailSongUseCase.Param, Song>() {

    data class Param(
        val songId: String
    ) : UseCase.Param()

    override suspend fun execute(param: Param?): Song = runCatching {
        param?.let {
            repository.getDetailSong(it.songId)
        } ?: throw CleanException(CleanExceptionType.PARAM_NULL)
    }.getOrElse {
        throw it.mapToCleanException()
    }
}
