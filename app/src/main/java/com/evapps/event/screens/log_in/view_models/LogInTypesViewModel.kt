package com.evapps.event.screens.log_in.view_models

import com.evapps.event.navigation.RouteDestination
import com.evapps.event.screens.base.BaseViewModelImpl
import com.evapps.event.screens.log_in.states.LogInTypesState

class LogInTypesViewModel:  BaseViewModelImpl<LogInTypesState>()  {
    override val initialState =
        LogInTypesState()

    fun onCredentialsSubmitted() {
        // Validate credentials
        state = state.copy(typesContinue = true)

    }
    fun onContinueClick() {
        navigateTo(RouteDestination.Login.LogIn)
    }

    fun goToSignUp() {
        navigateTo(RouteDestination.Login.SignUp)
    }

}