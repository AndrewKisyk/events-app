package com.evapps.event.extensions

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.Navigator

object NavigationExtensions {
    fun NavController.navigateSafe(
        direction: NavDirections,
        navigatorExtras: Navigator.Extras? = null
    ) {
        val action = currentDestination?.getAction(direction.actionId) ?: return
        if (currentDestination?.id != action.destinationId) {
            if (navigatorExtras == null) navigate(direction)
            else navigate(direction, navigatorExtras)
        }
    }
}