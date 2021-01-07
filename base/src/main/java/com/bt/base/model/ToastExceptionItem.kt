package com.bt.base.model

import com.bt.mp3.entity.exception.CleanExceptionType

class ToastExceptionItem(
    val type: CleanExceptionType? = null,
    val message: String? = null
) : ExceptionItem
