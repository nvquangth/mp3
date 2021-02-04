package com.bt.mp3.ui.mymusic

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.bt.base.ui.BaseViewModel
import com.bt.mp3.annotation.DefaultDispatcher
import com.bt.mp3.domain.usecase.GetDetailSongUseCase2
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class MyMusicViewModel @ViewModelInject constructor(
    private val getDetailSongUseCase2: GetDetailSongUseCase2,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : BaseViewModel() {

    fun getDetailSong() {
        viewModelScope.launch(defaultDispatcher) {
            getDetailSongUseCase2.execute()
        }
    }
}
