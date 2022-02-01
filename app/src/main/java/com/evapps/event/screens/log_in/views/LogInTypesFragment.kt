package com.evapps.event.screens.log_in.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.evapps.event.R
import com.evapps.event.screens.base.BaseFragment
import com.evapps.event.screens.log_in.states.LogInTypesState
import com.evapps.event.screens.log_in.view_models.LogInTypesViewModel
import kotlinx.android.synthetic.main.fragment_log_in_types.*

class LogInTypesFragment: BaseFragment<LogInTypesState, LogInTypesViewModel>() {

    override val viewModel: LogInTypesViewModel by lazyViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_log_in_types, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onCredentialsSubmitted()
        sign_up_link.setOnClickListener { viewModel.onContinueClick() }
        email_sign.setOnClickListener{ viewModel.goToSignUp() }
    }

    override fun onStateChange(state: LogInTypesState) {
        //k.isEnabled = state.typesContinue
    }
}