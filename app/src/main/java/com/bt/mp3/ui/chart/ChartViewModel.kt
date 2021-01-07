package com.bt.mp3.ui.chart

import androidx.hilt.lifecycle.ViewModelInject
import com.bt.mp3.annotation.DefaultDispatcher
import com.bt.mp3.base.ui.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher

class ChartViewModel @ViewModelInject constructor(
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : BaseViewModel()
