package com.brainxtech.extensions.providers

import android.content.Context

class AppResourceProvider(private val context: Context) : ResourceProvider {
    override fun getString(id: Int): String {
        return context.getString(id)
    }

    override fun getString(id: Int, vararg args: Any): String {
        return context.getString(id,*args)
    }

}