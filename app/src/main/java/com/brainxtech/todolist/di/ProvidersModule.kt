package com.brainxtech.todolist.di

import com.brainxtech.extensions.providers.AppResourceProvider
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appProvidersModule = module {
    single<AppResourceProvider> { AppResourceProvider(androidContext()) }
}