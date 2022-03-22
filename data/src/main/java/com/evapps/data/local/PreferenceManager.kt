package com.evapps.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import java.util.*

class PreferenceManager(context: Context) {

    companion object {
        private const val PREFS_FILENAME = "dreamDevEvent"
        private const val KEY_APP_LANGUAGE = "app_language"
        private const val KEY_USER_ID = "user_id"
    }

    private val sharedPrefs: SharedPreferences =
        context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)

    var appLanguage: String
        get() = sharedPrefs.getString(KEY_APP_LANGUAGE, "en") ?: ""
        set(value) = sharedPrefs.edit { putString(KEY_APP_LANGUAGE,       value) }

    var userId: String
        get() = sharedPrefs.getString(KEY_USER_ID, "") ?: ""
        set(value) = sharedPrefs.edit { putString(KEY_USER_ID,       value) }

    fun getAppLanguage(): Locale{
        return Locale.forLanguageTag(appLanguage)
    }
}