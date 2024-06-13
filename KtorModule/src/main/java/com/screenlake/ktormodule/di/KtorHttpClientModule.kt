package com.screenlake.ktormodule.di

import android.util.Log
import com.screenlake.ktormodule.KtorNetworkApi.Companion.headerMap
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.accept
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val ktorHttpClient = module {
    single <HttpClient>{ getKtorHttpClient() }
}
    fun getKtorHttpClient() = HttpClient(Android)
    {
        expectSuccess = true
        engine {
            connectTimeout = 15000
            socketTimeout = 15000
        }
        // Logging
        install(Logging) {
            level = LogLevel.ALL
        }
        // JSON
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                useAlternativeNames = true
                ignoreUnknownKeys = true
                encodeDefaults = false
            })
        }

        // Timeout
        install(HttpTimeout) {
            requestTimeoutMillis = 1000L * 300L
            connectTimeoutMillis = 1000L * 120L
            socketTimeoutMillis = 1000L * 120L
        }
        // Response observer
        install(ResponseObserver) {
            onResponse { response ->
                Log.d("HTTP status:", "${response.status.value}")
            }
        }
        // Apply to all requests
        defaultRequest {
            headerMap.apply {
                if (!isNullOrEmpty()){
                    forEach{
                        header(it.key,it.value)
                    }
                }
            }
            // Content Type
            accept(ContentType.Application.Json)
            contentType(ContentType.Application.Json)
        }
    }
