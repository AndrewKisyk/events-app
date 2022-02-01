package com.evapps.event.screens.main

import android.util.Log
import androidx.lifecycle.ViewModel
import com.evapps.domain.usecases.FetchMusicEventsUseCase

class MainViewModel(): ViewModel() {

    lateinit var fetchMusicEventsUseCase: FetchMusicEventsUseCase

    fun printuUseCase() {
        Log.e("MainViewModel", fetchMusicEventsUseCase.toString())
    }
}