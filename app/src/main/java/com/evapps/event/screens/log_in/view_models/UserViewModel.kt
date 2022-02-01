package com.evapps.event.screens.log_in.view_models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.evapps.data.models.LoggedInUser
import com.evapps.domain.repositories.LoginRepository

class UserViewModel(private val loginRepository: LoginRepository):ViewModel() {
    private val TAG = "UserViewModel"
    var user = loginRepository.getCurrentUser() as MediatorLiveData<LoggedInUser>
    var userIsLoggin: Boolean = loginRepository.isLoggedIn
    fun logout(){
        loginRepository.logout()
    }

}