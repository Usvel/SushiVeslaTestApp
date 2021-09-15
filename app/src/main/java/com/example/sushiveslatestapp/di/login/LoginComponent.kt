package com.example.sushiveslatestapp.di.login

import com.example.sushiveslatestapp.presentation.login.LoginFragment
import com.example.sushiveslatestapp.di.scope.FragmentScope
import dagger.Subcomponent

@Subcomponent(modules = [LoginViewModelModule::class])
@FragmentScope
interface LoginComponent {

    fun inject(loginFragment: LoginFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): LoginComponent
    }
}