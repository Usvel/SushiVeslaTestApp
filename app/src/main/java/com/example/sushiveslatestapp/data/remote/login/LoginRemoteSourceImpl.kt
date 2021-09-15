package com.example.sushiveslatestapp.data.remote.login

import com.example.sushiveslatestapp.domain.entitys.login.Weather
import io.reactivex.Single
import javax.inject.Inject

//Тестовые данные
class LoginRemoteSourceImpl @Inject constructor() : LoginRemoteSource {
    override fun getWeather() = Single.just(Weather("34° C", ""))
}