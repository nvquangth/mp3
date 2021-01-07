package com.bt.base.model

/**
 * A generic class that validate a value.
 * @param T
 */
sealed class Validation<out T>(open val data: T? = null, open val error: Throwable? = null) {

    data class Valid<T>(override val data: T) : Validation<T>(data)

    data class Invalid<T>(override val data: T, override val error: Throwable) : Validation<T>(data, error)
}
