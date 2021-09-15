package com.example.sushiveslatestapp.data.remote.home

import com.example.sushiveslatestapp.domain.entitys.home.Services
import com.example.sushiveslatestapp.domain.entitys.home.Users
import io.reactivex.Single

interface HomeRemoteSource {
    fun getBalance(): Single<Int>
    fun getListUsers(): Single<List<Users>>
    fun geListServices(): Single<List<Services>>
}