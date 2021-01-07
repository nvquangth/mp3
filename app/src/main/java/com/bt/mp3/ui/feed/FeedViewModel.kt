package com.bt.mp3.ui.feed

import androidx.hilt.lifecycle.ViewModelInject
import com.bt.base.ui.BaseViewModel
import com.bt.mp3.annotation.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher

class FeedViewModel @ViewModelInject constructor(
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : BaseViewModel()
