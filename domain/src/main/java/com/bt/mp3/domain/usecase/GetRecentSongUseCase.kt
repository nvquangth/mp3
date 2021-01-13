package com.bt.mp3.domain.usecase

import com.bt.mp3.domain.extension.mapToCleanException
import com.bt.mp3.domain.repository.SongRepository
import com.bt.mp3.entity.Song
import javax.inject.Inject

open class GetRecentSongUseCase @Inject constructor(
    private val repository: SongRepository
) : UseCase<UseCase.Param, List<Song>>() {

    override suspend fun execute(param: Param?): List<Song> = runCatching {
        repository.getRecentSongs()
    }.getOrElse {
        throw it.mapToCleanException()
    }
}
