package com.evapps.event.other

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.evapps.domain.repositories.LoginRepository
import com.evapps.event.screens.log_in.view_models.ForgetPasswordViewModel
import com.evapps.event.screens.log_in.view_models.LogInViewModel
import com.evapps.event.screens.log_in.view_models.SignUpViewModel
import com.evapps.event.screens.log_in.view_models.LogInTypesViewModel
import com.evapps.event.screens.splash.SplashViewModel
import javax.inject.Inject

class SharedViewModelFactory : ViewModelProvider.Factory {
    @Inject lateinit var loginRepository: LoginRepository
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when(modelClass) {
            LogInTypesViewModel::class.java ->  LogInTypesViewModel() as T
            SplashViewModel::class.java ->  SplashViewModel() as T
            SignUpViewModel::class.java ->   SignUpViewModel(
                loginRepository
            ) as T
            LogInViewModel::class.java ->  LogInViewModel(
                loginRepository
            ) as T
            ForgetPasswordViewModel::class.java ->  ForgetPasswordViewModel(
                loginRepository
            ) as T
            else -> throw IllegalAccessException(modelClass.name)
        }
    }
}