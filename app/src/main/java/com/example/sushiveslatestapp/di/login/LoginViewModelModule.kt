package com.example.sushiveslatestapp.di.login

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.lifecycle.ViewModel
import com.example.sushiveslatestapp.data.receiver.DateTimeReceiver
import com.example.sushiveslatestapp.data.receiver.DateTimeReceiverImpl
import com.example.sushiveslatestapp.data.remote.login.LoginRemoteSource
import com.example.sushiveslatestapp.data.remote.login.LoginRemoteSourceImpl
import com.example.sushiveslatestapp.data.repository.LoginRepositoryImpl
import com.example.sushiveslatestapp.domain.repositorys.LoginRepository
import com.example.sushiveslatestapp.presentation.login.LoginViewModel
import com.example.sushiveslatestapp.di.keys.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.multibindings.IntoMap

@Module
abstract class LoginViewModelModule {

    @ViewModelKey(LoginViewModel::class)
    @IntoMap
    @Binds
    abstract fun bindsLoginViewModule(loginViewModel: LoginViewModel): ViewModel

    @Binds
    abstract fun bindsLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository

    @Binds
    abstract fun bindsLoginRemoteSource(loginRemoteSourceImpl: LoginRemoteSourceImpl): LoginRemoteSource

    companion object {
        @Reusable
        @Provides
        fun provideReceiver(context: Context): DateTimeReceiver {
            val intentFilter = IntentFilter(Intent.ACTION_TIME_TICK)
            return DateTimeReceiverImpl(context, intentFilter)
        }
    }
}