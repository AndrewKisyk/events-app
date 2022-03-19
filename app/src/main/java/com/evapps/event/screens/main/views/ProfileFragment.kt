package com.evapps.event.screens.main.views

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.evapps.event.EventApp
import com.evapps.event.R
import com.evapps.event.databinding.LogInFragmentBinding
import com.evapps.event.databinding.ProfileFragmentBinding
import com.evapps.event.extensions.ViewExtensions.setAnimationOnClick
import com.evapps.event.screens.main.view_models.ProfileViewModel
import javax.inject.Inject

class ProfileFragment : Fragment() {

    @Inject lateinit var viewModel: ProfileViewModel
    lateinit var binding: ProfileFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        EventApp.appComponent.inject(fragment = this)

        binding = DataBindingUtil.inflate<ViewDataBinding>(
            inflater, R.layout.profile_fragment, container, false
        ) as ProfileFragmentBinding

        binding.viewModel = viewModel
        val view = binding.root
        initListeners()

        return view
    }

    private fun initListeners() {
        binding.btnLogOut.setAnimationOnClick {

        }
    }


}