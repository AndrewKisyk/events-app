package com.evapps.event.dagger

import android.app.Application
import android.content.Context
import com.evapps.event.utils.PreferenceManager
import dagger.Module
import dagger.Provides
@Module
class ApplicationModule(private val application: Application) {

    @Provides
    fun provideApplication(): Application {
        return application
    }

    @Provides
    fun provideContext(): Context {
        return application.applicationContext
    }

    @Provides
    fun providePreferenceManager(context: Context): PreferenceManager{
        return PreferenceManager(context)
    }
}