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
import com.evapps.event.EventApp
import com.evapps.event.R
import com.evapps.event.databinding.SignUpFragmentBinding
import com.evapps.event.extensions.launchWhenStarted
import com.evapps.event.screens.base.BaseFragment
import com.evapps.event.screens.log_in.states.SignUpState
import com.evapps.event.screens.log_in.view_models.SignUpViewModel
import kotlinx.android.synthetic.main.log_in_fragment.email
import kotlinx.android.synthetic.main.log_in_fragment.password

import kotlinx.android.synthetic.main.sign_up_fragment.*
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class SignUpFragment : BaseFragment<SignUpState, SignUpViewModel>() {

    @Inject
    override lateinit var viewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        EventApp.appComponent.inject(fragment = this)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            inflater, R.layout.sign_up_fragment, container, false
        ) as SignUpFragmentBinding

        binding.viewModel = viewModel
        val view = binding.root

        viewModel.signUpFormState.onEach { signUpState ->
            // disable signup button unless both username / password is valid
            signup.isEnabled = signUpState.isDataValid

            if (signUpState.usernameError != null) {
                userName.error = getString(signUpState.usernameError)
            }

            if (signUpState.emailError != null) {
                email.error = getString(signUpState.emailError)
            }

            if (signUpState.passwordError != null) {
                password.error = getString(signUpState.passwordError)
            }

            if (signUpState.passwordMatchesError != null) {
                repeatPassword.error = getString(signUpState.passwordMatchesError)
            }
        }.launchWhenStarted(lifecycleScope)

        viewModel.loginResult.onEach { loginResult ->
            if (loginResult.error != null) {
                showToastError(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUiWithUser(loginResult.success)
            }
        }.launchWhenStarted(lifecycleScope)

        binding.userName.afterTextChanged {
            viewModel.signUpDataChanged(
                binding.userName.text.toString(),
                binding.email.text.toString(),
                binding.password.text.toString(),
                binding.repeatPassword.text.toString()
            )
        }

        binding.email.afterTextChanged {
            viewModel.signUpDataChanged(
                binding.userName.text.toString(),
                binding.email.text.toString(),
                binding.password.text.toString(),
                binding.repeatPassword.text.toString()
            )
        }
        binding.password.afterTextChanged {
            viewModel.signUpDataChanged(
                binding.userName.text.toString(),
                binding.email.text.toString(),
                binding.password.text.toString(),
                binding.repeatPassword.text.toString()
            )
        }

        binding.repeatPassword.apply {
            afterTextChanged {
                viewModel.signUpDataChanged(
                    binding.userName.text.toString(),
                    binding.email.text.toString(),
                    binding.password.text.toString(),
                    binding.repeatPassword.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        viewModel.signup(
                            binding.userName.text.toString(),
                            binding.email.text.toString(),
                            binding.password.text.toString()
                        )
                }
                false
            }


            binding.signup.setOnClickListener {
               // binding.loading.visibility = View.VISIBLE
                viewModel.signup(binding.userName.text.toString(), binding.email.text.toString(), password.text.toString())
            }
        }


        return view
    }

    override fun onStateChange(state: SignUpState) {

    }


}