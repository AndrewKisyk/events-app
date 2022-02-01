package com.evapps.event.screens.log_in.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.evapps.event.EventApp
import com.evapps.event.R
import com.evapps.event.databinding.ForgetPasswordFragmentBinding

import com.evapps.event.screens.base.BaseFragment
import com.evapps.event.screens.log_in.states.ForgetPasswordState
import com.evapps.event.screens.log_in.view_models.ForgetPasswordViewModel
import javax.inject.Inject

class ForgetPasswordFragment : BaseFragment<ForgetPasswordState, ForgetPasswordViewModel>(){

    @Inject
    override lateinit var viewModel: ForgetPasswordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        EventApp.appComponent.inject(this)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            inflater, R.layout.forget_password_fragment, container, false
        ) as ForgetPasswordFragmentBinding

        binding.viewModel = viewModel
        val view = binding.root
        return view
    }



    override fun onStateChange(state: ForgetPasswordState) {

    }

}