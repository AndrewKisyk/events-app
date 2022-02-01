package com.evapps.event.screens.main.view_models

import android.util.Log
import androidx.lifecycle.ViewModel
import com.evapps.domain.repositories.LoginRepository

class ProfileViewModel(private val loginRepository: LoginRepository): ViewModel() {

    fun logout(){
       loginRepository.logout()
        Log.d("Profile", "Clicked")
    }
}