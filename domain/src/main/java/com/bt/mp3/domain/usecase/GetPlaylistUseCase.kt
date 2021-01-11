package com.bt.mp3.domain.usecase

import com.bt.mp3.domain.repository.PlaylistRepository
import com.bt.mp3.entity.Playlist
import javax.inject.Inject

open class GetPlaylistUseCase @Inject constructor(
    private val repository: PlaylistRepository
) : UseCase<GetPlaylistUseCase.Param, List<Playlist>>() {

    data class Param(
        val type: Int
    ) : UseCase.Param()

    override suspend fun execute(param: Param?): List<Playlist> {
        param?.let {
            return repository.getPlaylist(it.type)
        }

        throw Exception("Param not null")
    }
}
