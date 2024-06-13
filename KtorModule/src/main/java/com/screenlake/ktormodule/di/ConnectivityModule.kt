package com.screenlake.ktormodule.di

import android.content.Context
import com.screenlake.ktormodule.core_connectivity.data.implementation.ConnectivityFeatureImpl
import com.screenlake.ktormodule.core_connectivity.domain.ConnectivityFeature
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val connectivityModule = module {
    single<ConnectivityFeature> { provideConnectivityImplementation(androidContext()) }
    single { provideConnectivityFeature(get()) }
}

fun provideConnectivityFeature(connectivityFeature: ConnectivityFeature) =
    connectivityFeature.checkConnectivity()

fun provideConnectivityImplementation(appContext: Context): ConnectivityFeature {
    return ConnectivityFeatureImpl(appContext)
}
