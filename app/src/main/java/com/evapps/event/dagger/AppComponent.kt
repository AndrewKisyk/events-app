package com.evapps.event.dagger

import com.evapps.event.screens.log_in.views.ForgetPasswordFragment
import com.evapps.event.screens.log_in.views.LogInFragment
import com.evapps.event.screens.log_in.views.SignUpFragment
import com.evapps.event.screens.log_in.AuthActivity
import com.evapps.event.screens.main.MainFragment
import com.evapps.event.screens.main.views.EventsFragment
import com.evapps.event.screens.main.views.ProfileFragment
import com.evapps.event.screens.splash.LoadingFragment
import com.evapps.event.screens.splash.SplashFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ScreenModule::class, VMModule::class, DBModule::class])
interface AppComponent {

    //Fragments
    fun inject(fragment: ForgetPasswordFragment)

    //Fragments
    fun inject(fragment: SplashFragment)

    //Fragments
    fun inject(fragment: LogInFragment)

    //Fragments
    fun inject(fragment: SignUpFragment)

    //Fragments
    fun inject(fragment: MainFragment)

    //Fragments
    fun inject(fragment: ProfileFragment)

    //Fragments
    fun inject(fragment: EventsFragment)

    //Fragments
    fun inject(fragment: LoadingFragment)

    //Activity
    fun inject(activity: AuthActivity)
}