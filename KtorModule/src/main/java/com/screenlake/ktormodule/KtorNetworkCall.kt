package com.screenlake.ktormodule

import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess

suspend fun requestKtor(
    block: HttpResponse,
    resultState: (ResultState<Any>) -> Unit,
) {
    runCatching {
        block
    }.onSuccess {
        if (it.status.isSuccess()){
            resultState(ResultState.onAppSuccess(it.body()))
        }else{
            resultState(ResultState.onAppError(AppException(Error.UNKNOWN,null)))
        }
    }.onFailure {
        resultState(ResultState.onAppError(ExceptionHandle.handleKtorException(it)))
    }
}