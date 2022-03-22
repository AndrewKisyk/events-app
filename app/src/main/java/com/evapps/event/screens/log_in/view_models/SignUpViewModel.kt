package com.evapps.event.screens.log_in.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.evapps.data.local.Result
import com.evapps.domain.repositories.LoginRepository
import com.evapps.event.R
import com.evapps.event.models.LoggedInUserView
import com.evapps.event.models.LoginResult
import com.evapps.event.navigation.RouteDestination
import com.evapps.event.screens.log_in.states.SignUpState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SignUpViewModel(private val loginRepository: LoginRepository): AuthViewModel<SignUpState>()  {
    override val initialState =
        SignUpState()
    private val _signupForm = MutableStateFlow(SignUpState())
    val signUpFormState: StateFlow<SignUpState> = _signupForm

    private val _signupResult  = MutableStateFlow(LoginResult())
    val loginResult: StateFlow<LoginResult> = _signupResult   // it isn't any difference between signUp and login result

    fun signup(username: String, email: String, password: String) {
        // can be launched in a separate asynchronous job
        viewModelScope.launch {
            val result = loginRepository.signup(username, email, password)
            if (result is Result.Success) {
                _signupResult.value = LoginResult(success = LoggedInUserView(displayName = result.data.displayName))
            } else {
                _signupResult.value = LoginResult(error = R.string.sign_failed)
            }
        }
    }

    fun signUpDataChanged(username: String, email: String, password: String, repeatPassword: String) {
        if (!isUserNameValid(username)) {
            _signupForm.value =
                SignUpState(usernameError = R.string.invalid_username)
        } else if (!isEmailValid(email)) {
            _signupForm.value =
                SignUpState(emailError = R.string.invalid_email)
        } else if (!isPasswordValid(password)) {
            _signupForm.value =
                SignUpState(passwordError = R.string.invalid_password)
        } else if (!isPasswordMatches(password, repeatPassword)) {
            _signupForm.value =
                SignUpState(
                    passwordMatchesError = R.string.password_matches
                )
        } else {
            _signupForm.value =
                SignUpState(isDataValid = true)
        }
    }
   
    fun onContinueClick() {
        //navigateTo(RouteDestination.Login.LogIn)
    }

    fun goToLogIn() {
        navigateTo(RouteDestination.Login.LogIn)
    }
}