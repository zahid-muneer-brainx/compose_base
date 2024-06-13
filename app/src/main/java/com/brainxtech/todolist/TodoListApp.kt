package com.brainxtech.todolist

import android.app.Application
import android.content.Context
import com.brainxtech.todolist.di.appProvidersModule
import com.brainxtech.todolist.di.coroutineDispatchersModule
import com.brainxtech.todolist.di.viewModelModule
import com.screenlake.ktormodule.di.connectivityModule
import com.screenlake.ktormodule.di.ktorHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TodoListApp : Application() {
    //region Properties
    companion object {
        lateinit var appInstance: TodoListApp
        fun getApplication(): TodoListApp = appInstance
        fun getContext(): Context = appInstance.applicationContext
    }
    //endregion

    //region LifeCycle
    override fun onCreate() {
        appInstance = this
        super.onCreate()
        startKoin {
            androidLogger()
            // Reference Android context
            androidContext(this@TodoListApp)
            modules(
                coroutineDispatchersModule,
                appProvidersModule,
                viewModelModule,
                ktorHttpClient,
                connectivityModule
            )
        }
    }
    //endregion
}