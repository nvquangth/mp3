package com.bt.mp3.ui.feed

import androidx.hilt.lifecycle.ViewModelInject
import com.bt.mp3.annotation.DefaultDispatcher
import com.bt.mp3.base.ui.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher

class FeedViewModel @ViewModelInject constructor(
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : BaseViewModel()
