package com.evapps.event.screens.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.evapps.event.EventApp
import com.evapps.event.R
import dagger.Provides
import javax.inject.Inject

class MainFragment: Fragment(R.layout.fragment_main) {

    @Inject
    lateinit var mainViewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        EventApp.appComponent.inject(fragment = this)
    }

}