package com.evapps.event.screens.log_in.view_models

import com.evapps.domain.repositories.LoginRepository
import com.evapps.event.navigation.RouteDestination
import com.evapps.event.screens.log_in.states.ForgetPasswordState
import com.evapps.event.screens.log_in.view_models.AuthViewModel

class ForgetPasswordViewModel (private val loginRepository: LoginRepository): AuthViewModel<ForgetPasswordState>() {
    override val initialState =
        ForgetPasswordState()
    fun goToSignUp() {
        navigateTo(RouteDestination.Login.SignUp)
    }
    fun goToLogIn() {
        navigateTo(RouteDestination.Login.LogIn)
    }
}