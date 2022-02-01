package com.evapps.event.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import java.util.*

class PreferenceManager(context: Context) {

    companion object {
        private const val PREFS_FILENAME = "shared_prefs_name"
        private const val KEY_MY_STRING = "my_string"
    }

    private val sharedPrefs: SharedPreferences =
        context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)

    var appLanguage: String
        get() = sharedPrefs.getString(KEY_MY_STRING, "en") ?: ""
        set(value) = sharedPrefs.edit { putString(KEY_MY_STRING,       value) }

    fun getAppLanguage(): Locale{
        return Locale.forLanguageTag(appLanguage)
    }
}