package com.app.demo.network.result

import com.app.demo.network.error.HttpException

sealed class NetworkResult<out T, out E> {

    data class Success<T>(val data: T, val code: Int? = null) : NetworkResult<T, Nothing>()

    data object EmptySuccess : NetworkResult<Nothing, Nothing>()

    data class Error<E>(val error: E) : NetworkResult<Nothing, E>()

    data class HttpError(val code: String, val message: String?) : NetworkResult<Nothing, Nothing>()

    inline fun <R> toResultKt(mapper: (T) -> R): Result<R?> {
        return when (this) {
            is Success -> Result.success(mapper(this.data))
            is EmptySuccess -> Result.success(null)
            is Error -> Result.failure(Exception("Error $error"))
            is HttpError -> Result.failure(HttpException.transformed(code))
        }
    }

    fun toResultKt(): Result<Unit> {
        return when (this) {
            is Success -> Result.success(Unit)
            is EmptySuccess -> Result.success(Unit)
            is Error -> Result.failure(Exception("Error $error"))
            is HttpError -> Result.failure(HttpException.transformed(code))
        }
    }
}