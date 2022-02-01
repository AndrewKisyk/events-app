package com.evapps.event.screens.log_in.states

import com.evapps.event.screens.base.BaseViewState

data class LogInState(val usernameError: Int? = null,
                      val passwordError: Int? = null,
                      val isDataValid: Boolean = false) : BaseViewState