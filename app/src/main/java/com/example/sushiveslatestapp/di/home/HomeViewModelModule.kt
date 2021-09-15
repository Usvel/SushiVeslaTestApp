package com.example.sushiveslatestapp.di.home

import androidx.lifecycle.ViewModel
import com.example.sushiveslatestapp.data.cache.home.MemoryCacheHome
import com.example.sushiveslatestapp.data.cache.home.MemoryCacheHomeImpl
import com.example.sushiveslatestapp.data.remote.home.HomeRemoteSource
import com.example.sushiveslatestapp.data.remote.home.HomeRemoteSourceImpl
import com.example.sushiveslatestapp.data.repository.HomeRepositoryImpl
import com.example.sushiveslatestapp.domain.repositorys.HomeRepository
import com.example.sushiveslatestapp.presentation.home.HomeViewModel
import com.example.sushiveslatestapp.di.keys.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HomeViewModelModule {

    @ViewModelKey(HomeViewModel::class)
    @IntoMap
    @Binds
    abstract fun bindsHomeViewModule(homeViewModel: HomeViewModel): ViewModel

    @Binds
    abstract fun bindsMemoryCacheHome(memoryCacheHomeImpl: MemoryCacheHomeImpl): MemoryCacheHome

    @Binds
    abstract fun bindsHomeRepository(homeRepositoryImpl: HomeRepositoryImpl): HomeRepository

    @Binds
    abstract fun bindsPageRemoteSource(homeRemoteSourceImpl: HomeRemoteSourceImpl): HomeRemoteSource
}