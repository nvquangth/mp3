package com.bt.mp3.ui.mymusic.playlist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.bt.base.extension.mapToCleanException
import com.bt.base.model.Result
import com.bt.base.ui.BaseViewModel
import com.bt.mp3.annotation.DefaultDispatcher
import com.bt.mp3.domain.usecase.GetSuggestionPlaylistUseCase
import com.bt.mp3.model.SongItem
import com.bt.mp3.model.SongItemMapper
import kotlinx.coroutines.CoroutineDispatcher

class PlaylistViewModel @ViewModelInject constructor(
    private val getSuggestionPlaylistUseCase: GetSuggestionPlaylistUseCase,
    private val playlistItemMapper: SongItemMapper,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : BaseViewModel() {

    private val _playlistId = MutableLiveData<String>()
    val playlistId: LiveData<String>
        get() = _playlistId

    val playlistsResult: LiveData<Result<List<SongItem>>> = _playlistId.switchMap {
        liveData(defaultDispatcher) {
            runCatching {
                emit(Result.Loading)
                getSuggestionPlaylistUseCase.execute(GetSuggestionPlaylistUseCase.Param(playlistId = it)).map {
                    playlistItemMapper.mapToPresentation(it)
                }.run {
                    emit(Result.Success(this))
                }
            }.getOrElse {
                setExceptionAsync(it.mapToCleanException())
            }
        }
    }

    fun setPlaylistId(id: String?) {
        _playlistId.value = id
    }
}
