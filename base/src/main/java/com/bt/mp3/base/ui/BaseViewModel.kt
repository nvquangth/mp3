package com.bt.mp3.base.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bt.mp3.base.utils.Event
import com.bt.mp3.entity.exception.CleanException

open class BaseViewModel : ViewModel() {

    private val _exceptionEvent = MutableLiveData<Event<CleanException>>()
    val exceptionEvent: LiveData<Event<CleanException>>
        get() = _exceptionEvent

    private val _showLoadingEvent = MutableLiveData<Event<Unit>>()
    val showLoadingEvent: LiveData<Event<Unit>>
        get() = _showLoadingEvent

    private val _hideLoadingEvent = MutableLiveData<Event<Unit>>()
    val hideLoadingEvent: LiveData<Event<Unit>>
        get() = _hideLoadingEvent

    fun setLoading(isLoading: Boolean) {
        if (isLoading) {
            _showLoadingEvent.value = Event(Unit)
        } else {
            _hideLoadingEvent.value = Event(Unit)
        }
    }

    fun setLoadingAsync(isLoading: Boolean) {
        if (isLoading) {
            _showLoadingEvent.postValue(Event(Unit))
        } else {
            _hideLoadingEvent.postValue(Event(Unit))
        }
    }

    fun setExceptionAsync(exception: CleanException) {
        _exceptionEvent.postValue(Event(exception))
    }
}
