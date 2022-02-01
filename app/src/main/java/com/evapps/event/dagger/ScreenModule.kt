package com.evapps.event.dagger

import com.evapps.event.screens.main.MainFragment
import com.evapps.event.screens.log_in.views.LogInTypesFragment
import com.evapps.event.screens.splash.SplashFragment
import dagger.Module
import dagger.Provides

@Module
class ScreenModule {
    @Provides
    fun provideSplashScreen(): SplashFragment {
        return SplashFragment()
    }

    @Provides
    fun provideDescriptionScreen(): LogInTypesFragment {
        return LogInTypesFragment()
    }

    @Provides
    fun provideMainScreen(): MainFragment {
        return MainFragment()
    }
}