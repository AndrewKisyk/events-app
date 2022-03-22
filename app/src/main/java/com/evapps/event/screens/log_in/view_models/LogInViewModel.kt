package com.evapps.event.screens.log_in.view_models

import androidx.lifecycle.viewModelScope
import com.evapps.data.local.Result
import com.evapps.domain.repositories.LoginRepository
import com.evapps.event.R
import com.evapps.event.models.LoggedInUserView
import com.evapps.event.models.LoginResult
import com.evapps.event.navigation.RouteDestination
import com.evapps.event.screens.log_in.states.LogInState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LogInViewModel(private val loginRepository: LoginRepository): AuthViewModel<LogInState>() {
    override val initialState = LogInState()

    private val _loginForm =  MutableStateFlow(LogInState())
    val loginFormState: StateFlow<LogInState> = _loginForm

    private val _loginResult = MutableStateFlow(LoginResult())
    val loginResult: StateFlow<LoginResult> = _loginResult


    fun login(email: String, password: String) {
        viewModelScope.launch {
            val result = loginRepository.login(email, password)
            if (result is Result.Success) {
                _loginResult.value = LoginResult(
                    success = LoggedInUserView(displayName = result.data.displayName)
                )
            } else {
                _loginResult.value = LoginResult(error = R.string.login_failed)
            }
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value =
                LogInState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value =
                LogInState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value =
                LogInState(isDataValid = true)
        }
    }



    fun onContinueClick() {
        //navigateTo(RouteDestination.Login.LogIn)
    }

    fun goToSignUp() {
        navigateTo(RouteDestination.Login.SignUp)
    }
    fun goToForgetPass() {
        navigateTo(RouteDestination.Login.ForgetPassword)
    }
}