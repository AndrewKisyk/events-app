package com.evapps.data.local

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Result<out T : Any> {

    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }

    inline fun doOnSuccess(body: (data: T) -> Unit): Result<T> {
        when (this) {
            is Success<T> -> body.invoke(this.data)
        }
        return this
    }

    inline fun doOnError(body: (exception: Exception) -> Unit): Result<T> {
        when (this) {
            is Error -> body.invoke(exception)
        }
        return this
    }
}