package com.bt.mp3.domain.extension

import com.bt.mp3.entity.exception.CleanException
import com.bt.mp3.entity.exception.CleanExceptionType

fun Throwable.mapToCleanException(): CleanException = when (this) {
    is CleanException -> this

    else -> throw CleanException(CleanExceptionType.UNKNOWN, message)
}
