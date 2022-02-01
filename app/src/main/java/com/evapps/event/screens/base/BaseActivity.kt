package com.evapps.event.screens.base

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.LocaleList
import androidx.appcompat.app.AppCompatActivity
import com.evapps.event.utils.ContextUtils.Companion.updateConfigurationIfSupported
import com.evapps.event.utils.PreferenceManager
import java.util.*


@Suppress("DEPRECATION")
open class BaseActivity: AppCompatActivity() {

    override fun attachBaseContext(newBase: Context) {
        // get chosen language from shread preference

       /* val localeToSwitchTo = PreferenceManager(newBase).getAppLanguage()
        val localeUpdatedContext: ContextWrapper =
            ContextUtils.updateLocale(newBase, localeToSwitchTo)
        super.attachBaseContext(localeUpdatedContext)*/
        super.attachBaseContext(newBase)
        val config = Configuration()
        applyOverrideConfiguration(config)
        PreferenceManager(this).appLanguage = getCurrentLanguage()
    }

    override fun applyOverrideConfiguration(newConfig: Configuration) {
        val localeToSwitchTo = PreferenceManager(this).getAppLanguage()
        super.applyOverrideConfiguration(updateConfigurationIfSupported(newConfig, localeToSwitchTo))
    }

    private fun getCurrentLanguage(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            LocaleList.getDefault()[0].language
        } else {
            Locale.getDefault().language
        }
    }



}