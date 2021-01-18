package com.bt.mp3.domain.usecase

import com.bt.mp3.domain.extension.mapToCleanException
import com.bt.mp3.domain.repository.SongRepository
import com.bt.mp3.entity.Stream
import com.bt.mp3.entity.exception.CleanException
import com.bt.mp3.entity.exception.CleanExceptionType
import javax.inject.Inject

open class GetStreamSongUseCase @Inject constructor(
    private val repository: SongRepository
) : UseCase<GetStreamSongUseCase.Param, Stream>() {

    data class Param(
        val songId: String
    ) : UseCase.Param()

    override suspend fun execute(param: Param?): Stream = runCatching {
        param?.let {
            repository.getStreamSong(it.songId)
        } ?: throw CleanException(CleanExceptionType.PARAM_NULL)
    }.getOrElse {
        throw it.mapToCleanException()
    }
}
