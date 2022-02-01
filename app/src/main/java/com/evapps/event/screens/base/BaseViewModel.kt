package com.evapps.event.screens.base

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.evapps.event.navigation.RouteDestination
import com.evapps.event.navigation.RouteSection
import com.evapps.event.other.SingleEvent


interface BaseViewModel<VS : BaseViewState> {

    val initialState: VS
    val viewState: MutableLiveData<VS>
    val navigationEvent: MutableLiveData<SingleEvent<NavController.() -> Any>>
    var state: VS
    var stateAsync: VS


    fun navigateTo(route: RouteSection, args: Bundle? = null)
    fun navigateTo(route: RouteDestination, args: Bundle? = null, clearStack: Boolean = false, animation:Boolean = true)
    fun onBackPressed() {}
}