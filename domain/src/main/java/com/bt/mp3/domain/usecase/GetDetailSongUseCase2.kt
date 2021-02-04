package com.bt.mp3.domain.usecase

import com.bt.mp3.domain.repository.SongRepository
import javax.inject.Inject

class GetDetailSongUseCase2 @Inject constructor(
    private val repository: SongRepository
): UseCase<UseCase.Param, Unit>() {

    override suspend fun execute(param: Param?) {
        repository.getDetailSong2("")
    }
}