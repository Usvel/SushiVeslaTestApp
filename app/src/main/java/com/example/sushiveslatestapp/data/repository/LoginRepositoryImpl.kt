package com.example.sushiveslatestapp.data.repository

import com.example.sushiveslatestapp.data.receiver.DateTimeReceiver
import com.example.sushiveslatestapp.data.remote.login.LoginRemoteSource
import com.example.sushiveslatestapp.domain.entitys.login.DateTime
import com.example.sushiveslatestapp.domain.entitys.login.Weather
import com.example.sushiveslatestapp.domain.repositorys.LoginRepository
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginRemoteSource: LoginRemoteSource,
    private val dateTimeReceiver: DateTimeReceiver
) : LoginRepository {
    override fun getDateTime(): Observable<DateTime> =
        dateTimeReceiver.getDateTime()

    override fun getWeather(): Single<Weather> =
        loginRemoteSource.getWeather()
}