package com.bt.mp3.base.model

import com.bt.mp3.entity.exception.CleanExceptionType

class AlertExceptionItem(
    val type: CleanExceptionType? = null,
    val message: String? = null,
    val title: String? = null,
    val positiveButton: String? = null,
    val negativeButton: String? = null
) : ExceptionItem
