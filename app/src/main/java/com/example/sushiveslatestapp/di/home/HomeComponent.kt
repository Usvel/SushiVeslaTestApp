package com.example.sushiveslatestapp.di.home

import com.example.sushiveslatestapp.presentation.home.HomeFragment
import com.example.sushiveslatestapp.di.scope.FragmentScope
import dagger.Subcomponent

@Subcomponent(modules = [HomeViewModelModule::class])
@FragmentScope
interface HomeComponent {

    fun inject(homeFragment: HomeFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): HomeComponent
    }
}