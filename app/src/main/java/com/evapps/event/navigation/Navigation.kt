package com.evapps.event.navigation

import androidx.navigation.NavOptions
import com.evapps.event.R


val defaultNavOptions: NavOptions
    get() {
        return NavOptions.Builder()
            .setEnterAnim(R.anim.enter)
            .setExitAnim(R.anim.exit)
            .setPopEnterAnim(R.anim.pop_enter)
            .setPopExitAnim(R.anim.pop_exit)
            .build()
    }
