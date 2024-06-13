package com.brainxtech.extensions.providers

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.brainxtech.extensions.pref.getPrefs
import kotlinx.coroutines.flow.*

enum class ThemePrefKeys(val key: String) {
    LIGHT_THEME("light"),
    DARK_THEME("dark"),
    SYSTEM_THEME("system")
}


class AppThemeProvider constructor(private val context: Context) : ThemeProvider {
    private val sharedPreferences = context.getPrefs()

    private val defaultThemeValue = ThemePrefKeys.SYSTEM_THEME.key

    private val preferenceKeyChangedFlow = MutableSharedFlow<String>(extraBufferCapacity = 1)

    private val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
        preferenceKeyChangedFlow.tryEmit(key?:"")
    }

    companion object {
        const val KEY_THEME = "pref_theme"
    }

    init {
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
    }

    override var theme: ThemeProvider.Theme
        get() = getThemeForStorageValue(sharedPreferences.getString(KEY_THEME, defaultThemeValue)?:"")
        set(value) = sharedPreferences.edit {
            putString(KEY_THEME, value.storageKey)
        }

    override fun observeTheme(): Flow<ThemeProvider.Theme> {
        return preferenceKeyChangedFlow
            // Emit on start so that we always send the initial value
            .onStart { emit(KEY_THEME) }
            .filter { it == KEY_THEME }
            .map { theme }
            .distinctUntilChanged()
    }

    override fun isNightMode(): Boolean {
        return theme == ThemeProvider.Theme.DARK
    }

    private val ThemeProvider.Theme.storageKey: String
        get() = when (this) {
            ThemeProvider.Theme.LIGHT -> ThemePrefKeys.LIGHT_THEME.key
            ThemeProvider.Theme.DARK -> ThemePrefKeys.DARK_THEME.key
            ThemeProvider.Theme.SYSTEM -> ThemePrefKeys.SYSTEM_THEME.key
        }

    private fun getThemeForStorageValue(value: String) = when (value) {
        ThemePrefKeys.LIGHT_THEME.key -> ThemeProvider.Theme.LIGHT
        ThemePrefKeys.DARK_THEME.key -> ThemeProvider.Theme.DARK
        else -> ThemeProvider.Theme.SYSTEM
    }

    override fun setNightMode(forceNight: Boolean) {
        theme = if (forceNight) {
            ThemeProvider.Theme.DARK
        } else {
            ThemeProvider.Theme.LIGHT
        }
    }
}