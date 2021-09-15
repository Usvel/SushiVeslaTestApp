package com.example.sushiveslatestapp.di

import android.content.Context
import com.example.sushiveslatestapp.di.home.HomeComponent
import com.example.sushiveslatestapp.di.login.LoginComponent
import com.example.sushiveslatestapp.di.menu.MenuComponent
import dagger.BindsInstance
import dagger.Component

@Component(modules = [ViewModelFactoryModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun registerHomeComponent(): HomeComponent.Factory

    fun registerLoginComponent(): LoginComponent.Factory

    fun registerMenuComponent(): MenuComponent.Factory
}