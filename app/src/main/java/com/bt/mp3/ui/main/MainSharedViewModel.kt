package com.bt.mp3.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import com.bt.mp3.annotation.DefaultDispatcher
import com.bt.mp3.base.ui.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher

class MainSharedViewModel @ViewModelInject constructor(
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : BaseViewModel()
