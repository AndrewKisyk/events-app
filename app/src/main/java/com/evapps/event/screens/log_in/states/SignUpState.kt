package com.evapps.event.screens.log_in.states

import com.evapps.event.screens.base.BaseViewState

data class SignUpState (val usernameError: Int? = null,
                        val emailError: Int? = null,
                        val passwordError: Int? = null,
                        val passwordMatchesError: Int? = null,
                        val isDataValid: Boolean = false) : BaseViewState