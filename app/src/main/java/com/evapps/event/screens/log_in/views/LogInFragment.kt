package com.evapps.event.screens.log_in.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.evapps.event.EventApp
import com.evapps.event.R
import com.evapps.event.databinding.LogInFragmentBinding
import com.evapps.event.extensions.launchWhenStarted

import com.evapps.event.screens.base.BaseFragment
import com.evapps.event.screens.log_in.states.LogInState
import com.evapps.event.screens.log_in.view_models.LogInViewModel

import kotlinx.android.synthetic.main.log_in_fragment.*
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


class LogInFragment : BaseFragment<LogInState, LogInViewModel>() {

    @Inject
    override lateinit var viewModel: LogInViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        EventApp.appComponent.inject(fragment = this)

        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            inflater, R.layout.log_in_fragment, container, false
        ) as LogInFragmentBinding

        binding.viewModel = viewModel
        val view = binding.root


        viewModel.loginResult.onEach { loginResult ->
            if (loginResult.error != null) {
                showToastError(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUiWithUser(loginResult.success)
            }
        }.launchWhenStarted(lifecycleScope)


        binding.email.afterTextChanged {
            viewModel.loginDataChanged(
                email.text.toString(),
                password.text.toString()
            )
        }

        binding.password.apply {
            afterTextChanged {
                viewModel.loginDataChanged(
                    binding.email.text.toString(),
                    binding.password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        viewModel.login(
                            binding.email.text.toString(),
                            binding.password.text.toString()
                        )
                }
                false
            }

            binding.login.setOnClickListener {
                //  binding.loading.visibility = View.VISIBLE
                viewModel.login(binding.email.text.toString(), binding.password.text.toString())
                //   Navigation.findNavController(view).navigate(R.id.main_activity)
            }
        }

        return view
    }


    /* override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         super.onViewCreated(view, savedInstanceState)
     }*/

    override fun onStateChange(state: LogInState) {
        viewModel.loginFormState.onEach { loginState ->
            // disable login button unless both username / password is valid
            login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                email.error = getString(loginState.usernameError)
            }

            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
        }.launchWhenStarted(lifecycleScope)
    }

}