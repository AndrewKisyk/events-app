package com.evapps.event.screens.splash

import com.evapps.event.models.Language
import com.evapps.event.navigation.RouteDestination
import com.evapps.event.screens.base.BaseViewModelImpl
import com.evapps.data.local.PreferenceManager

class SplashViewModel() : BaseViewModelImpl<SplashState>() {

    override val initialState = SplashState()

    fun onCredentialsSubmitted() {
        // Validate credentials
        state = state.copy(splashContinue = true)
    }

    fun onContinueClick() {
        navigateTo(RouteDestination.Login.LogInTypes)
    }

    fun toggleLanguages(preferenceManager: PreferenceManager, language: Language) {
        preferenceManager.appLanguage = language.countryCode
        language.swipeLanguages()
    }



    fun onAppExit() {
        // Track user leaving the app
    }
}