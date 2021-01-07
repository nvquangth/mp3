package com.bt.mp3.entity.exception

open class CleanException(
    open val type: CleanExceptionType? = null,
    override val message: String? = null
) : Throwable(message)
