package com.evapps.event.navigation

import androidx.annotation.IdRes
import com.evapps.event.R

sealed class RouteSection(@IdRes val graph: Int) {
    object Login : RouteSection(R.id.nav_graph)
   // object Settings : RouteSection(R.id.settingsNavGraph)
}

sealed class RouteDestination(@IdRes val destination: Int) {

    object Back : RouteDestination(-1)

    sealed class Login(@IdRes destination: Int) : RouteDestination(destination) {
        object Splash : Login(R.id.splashFragment)
        object LogInTypes : Login(R.id.logInTypesFragment)
        object LogIn : Login(R.id.logInFragment)
        object SignUp : Login(R.id.signUpFragment)
        object ForgetPassword: Login(R.id.forgetPassFragment)
    }

    sealed class Main(@IdRes destination: Int) : RouteDestination(destination) {
       // object MainActivity: Main(R.id.main_activity)
        //object Profile : Settings(R.id.profileFragment)
        // object Notifications : Settings(R.id.notificationsFragment)
    }


    sealed class Settings(@IdRes destination: Int) : RouteDestination(destination) {
        //object Profile : Settings(R.id.profileFragment)
       // object Notifications : Settings(R.id.notificationsFragment)
    }
}