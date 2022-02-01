package com.evapps.event.screens.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.evapps.event.EventApp
import com.evapps.event.R

//this fragment is totally blank and not in usgage only as a background for loading
class LoadingFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        EventApp.appComponent.inject(fragment = this)
        return inflater.inflate(R.layout.fragment_loading, container, false)
    }


}