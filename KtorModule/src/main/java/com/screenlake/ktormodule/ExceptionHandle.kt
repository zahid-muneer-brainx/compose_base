package com.screenlake.ktormodule

import android.net.ParseException
import android.util.MalformedJsonException
import com.google.gson.JsonParseException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.bodyAsText
import org.apache.http.conn.ConnectTimeoutException
import org.json.JSONException
import org.json.JSONObject

object ExceptionHandle {

    const val ERROR_NAME = "message"

    suspend fun handleKtorException(throwable: Throwable?):AppException{
        throwable?.let {
            return when (it) {
                is ResponseException ->{
                    return  convertKtorErrorBody(it)
                }
                is JsonParseException, is JSONException, is ParseException, is MalformedJsonException -> {
                    AppException(Error.PARSE_ERROR, it)
                }
                is ConnectTimeoutException -> {
                    AppException(Error.TIMEOUT_ERROR, it)
                }
                is java.net.SocketTimeoutException -> {
                    AppException(Error.TIMEOUT_ERROR, it)
                }
                is java.net.UnknownHostException -> {
                    AppException(Error.UNKNOWN_HOST, it)
                }
                else->{
                    AppException(error = it.message, errorLog = it.localizedMessage)
                }
            }
        }
        return AppException(Error.UNKNOWN,null)
    }

    private suspend fun convertKtorErrorBody(exception: ResponseException):AppException{
        exception.apply {
            if (response.bodyAsText().isNotBlank()){
                response.apply {
                    val error = JSONObject(bodyAsText()).get(ERROR_NAME) as String
                    return AppException(status.value, error = error)
                }
            }else{
                when(exception){
                    is RedirectResponseException ->{
                        response.status.apply {
                            return AppException(errCode = value,description)
                        }
                    }

                    is ClientRequestException ->{
                        response.status.apply {
                            return AppException(errCode = value,description)
                        }
                    }
                    is ServerResponseException ->{
                        response.status.apply {
                            return AppException(errCode = value,description)
                        }
                    }
                }
            }
        }
        return AppException(Error.UNKNOWN,null)
    }
}