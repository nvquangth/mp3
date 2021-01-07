package com.bt.base.model

/**
 * A generic class that holds a value with its loading status.
 * @param T
 */
sealed class Result<out T>(open val data: T? = null, open val error: Throwable? = null) {
    object Loading : Result<Nothing>()

    data class Success<T>(override val data: T) : Result<T>(data)

    data class Error(override val error: Throwable) : Result<Nothing>(error = error)
}
