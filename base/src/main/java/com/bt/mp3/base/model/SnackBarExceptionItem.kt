package com.bt.mp3.base.model

import com.bt.mp3.entity.exception.CleanExceptionType

class SnackBarExceptionItem(
    val type: CleanExceptionType? = null,
    val message: String? = null
) : ExceptionItem
