package com.bt.mp3.ui.discover

import androidx.hilt.lifecycle.ViewModelInject
import com.bt.base.ui.BaseViewModel
import com.bt.mp3.annotation.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher

class DiscoverViewModel @ViewModelInject constructor(
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : BaseViewModel()
