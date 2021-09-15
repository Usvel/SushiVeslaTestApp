package com.example.sushiveslatestapp

import android.app.Application
import com.example.sushiveslatestapp.di.AppComponent
import com.example.sushiveslatestapp.di.DaggerAppComponent

class App : Application() {

    private var appComponent: AppComponent? = null

    fun getAppComponent(): AppComponent {
        return appComponent ?: DaggerAppComponent.factory().create(applicationContext).also {
            appComponent = it
        }
    }
}