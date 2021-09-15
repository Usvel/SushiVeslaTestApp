package com.example.sushiveslatestapp.di.menu

import androidx.lifecycle.ViewModel
import com.example.sushiveslatestapp.data.cache.user.MemoryCacheUser
import com.example.sushiveslatestapp.data.cache.user.MemoryCacheUserImpl
import com.example.sushiveslatestapp.data.remote.menu.MenuRemoteSource
import com.example.sushiveslatestapp.data.remote.menu.MenuRemoteSourceImpl
import com.example.sushiveslatestapp.data.repository.MenuRepositoryImpl
import com.example.sushiveslatestapp.domain.repositorys.MenuRepository
import com.example.sushiveslatestapp.presentation.main.MenuViewModel
import com.example.sushiveslatestapp.di.keys.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MenuViewModelModule {

    @ViewModelKey(MenuViewModel::class)
    @IntoMap
    @Binds
    abstract fun bindsMenuViewModule(menuViewModel: MenuViewModel): ViewModel

    @Binds
    abstract fun bindsMenuRepository(menuRepositoryImpl: MenuRepositoryImpl): MenuRepository

    @Binds
    abstract fun bindsMenuRemoteSource(menuRemoteSourceImpl: MenuRemoteSourceImpl): MenuRemoteSource

    @Binds
    abstract fun bindsMemoryCacheUser(memoryCacheUserImpl: MemoryCacheUserImpl): MemoryCacheUser
}