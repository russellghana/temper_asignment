package com.russell.temper.template.core

import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Custom implementation of Kotlin's [Result] class to return Failure in failure cases.
 */
sealed class TemperResult<out R> {
    data class Success<out T>(val data: T) : TemperResult<T>()
    data class Error(val exception: Exception) : TemperResult<Nothing>()
}

inline fun <T> TemperResult<T>.onSuccess(func: (T) -> Unit) = apply {
    if (this is TemperResult.Success) {
        func(data)
    }
}

inline fun <T> TemperResult<T>.onFailure(func: (Failure) -> Unit) = apply {
    if (this is TemperResult.Error) {
        when (exception) {
            is Failure -> func(exception)
            is SocketTimeoutException -> func(Failure.Timeout)
            is UnknownHostException -> func(Failure.NoInternet)
            else -> func(Failure.Unknown)
        }
    }
}
