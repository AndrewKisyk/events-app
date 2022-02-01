package com.evapps.event.screens.log_in.view_models

import android.text.TextUtils
import android.util.Patterns
import com.evapps.event.screens.base.BaseViewModelImpl
import com.evapps.event.screens.base.BaseViewState

abstract class AuthViewModel<T:BaseViewState>: BaseViewModelImpl<T>() {
    // A placeholder username validation check
    fun isUserNameValid(username: String): Boolean {
        return !TextUtils.isEmpty(username)
    }

    // A placeholder password validation check
    fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

    // A placeholder passwordMatches validation check
    fun isPasswordMatches(password: String, secondPassword: String): Boolean{
        return password.equals(secondPassword)
    }

    // A placeholder email validation check
    fun isEmailValid(username: String): Boolean {
        return  Patterns.EMAIL_ADDRESS.matcher(username).matches()
    }
}