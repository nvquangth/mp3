package com.bt.base.model

/**
 * A generic class that holds a value with its loading status.
 * @param T
 */
sealed class Result<out T> {
    object Loading : Result<Nothing>()

    data class Success<T>(val data: T) : Result<T>()

    data class Error(val error: Throwable) : Result<Nothing>()
}
