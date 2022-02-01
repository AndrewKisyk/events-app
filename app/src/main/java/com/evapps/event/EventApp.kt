package com.evapps.event

import android.app.Application
import com.evapps.event.dagger.AppComponent
import com.evapps.event.dagger.ApplicationModule
import com.evapps.event.dagger.DaggerAppComponent


class EventApp: Application(){

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}