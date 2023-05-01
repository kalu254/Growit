package com.kalu.growit.core.util

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
    val error: Throwable? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(message: String? = null, throwable: Throwable? = null, data: T? = null) :
        Resource<T>(data, message, throwable)
}
