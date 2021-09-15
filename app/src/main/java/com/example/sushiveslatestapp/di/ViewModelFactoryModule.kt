package com.example.sushiveslatestapp.di

import androidx.lifecycle.ViewModelProvider
import com.example.sushiveslatestapp.presentation.factory.DaggerViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {

    @Binds
    fun bindsViewModelFactory(daggerViewModelFactory: DaggerViewModelFactory): ViewModelProvider.Factory
}