package com.example.sushiveslatestapp.data.remote.login

import com.example.sushiveslatestapp.domain.entitys.login.Weather
import io.reactivex.Single

interface LoginRemoteSource {
    fun getWeather(): Single<Weather>
}