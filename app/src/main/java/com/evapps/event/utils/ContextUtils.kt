package com.evapps.event.utils

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.LocaleList
import java.util.*

@Suppress("DEPRECATION")
class ContextUtils(base: Context) : ContextWrapper(base) {

    companion object {
        open fun updateConfigurationIfSupported(config: Configuration, locale: Locale): Configuration? {
            // Configuration.getLocales is added after 24 and Configuration.locale is deprecated in 24
            if (Build.VERSION.SDK_INT >= 24) {
                if (!config.locales.isEmpty) {
                    return config
                }
            } else {
                if (config.locale != null) {
                    return config
                }
            }

            if (locale != null) {
                // Configuration.setLocale is added after 17 and Configuration.locale is deprecated
                // after 24
                if (Build.VERSION.SDK_INT >= 17) {
                    config.setLocale(locale)
                } else {
                    config.locale = locale
                }
            }
            return config
        }

    }

}