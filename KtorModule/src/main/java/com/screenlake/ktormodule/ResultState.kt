package com.screenlake.ktormodule

sealed class ResultState<out T> {
    companion object {
        fun <T> onAppSuccess(data: T?): ResultState<T> = Success(data)
        fun <T> onAppError(error: AppException): ResultState<T> = Error(error)

    }
    data class Success<out T>(val data: T?) : ResultState<T>()
    data class Error(val error: AppException) : ResultState<Nothing>()

}