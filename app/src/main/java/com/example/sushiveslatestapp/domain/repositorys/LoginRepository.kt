package com.example.sushiveslatestapp.domain.repositorys

import com.example.sushiveslatestapp.domain.entitys.login.DateTime
import com.example.sushiveslatestapp.domain.entitys.login.Weather
import io.reactivex.Observable
import io.reactivex.Single

interface LoginRepository {
    fun getDateTime(): Observable<DateTime>
    fun getWeather(): Single<Weather>
}