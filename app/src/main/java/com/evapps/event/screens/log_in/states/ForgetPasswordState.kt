package com.evapps.event.screens.log_in.states

import com.evapps.event.screens.base.BaseViewState

data class ForgetPasswordState(val emailError: Int? = null,
                               val isDataValid: Boolean = false) : BaseViewState {
}