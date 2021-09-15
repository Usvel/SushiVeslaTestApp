package com.example.sushiveslatestapp.data.remote.home

import com.example.sushiveslatestapp.domain.entitys.home.Services
import com.example.sushiveslatestapp.domain.entitys.home.Users
import io.reactivex.Single
import javax.inject.Inject

//Тестовые данные
class HomeRemoteSourceImpl @Inject constructor() : HomeRemoteSource {
    override fun getBalance(): Single<Int> = Single.just(25120)

    override fun getListUsers(): Single<List<Users>> = Single.just(
        listOf(
            Users("Dahsa", "https://pbs.twimg.com/media/ETnk8TzUcAA3Ohj.jpg"),
            Users("Test", ""),
            Users("Test", ""),
            Users("Alena", "")
        )
    )

    override fun geListServices(): Single<List<Services>> = Single.just(
        listOf(
            Services("Send Money", "https://pbs.twimg.com/media/ETnk8TzUcAA3Ohj.jpg"),
            Services("Send Money", ""),
            Services("Send Money", ""),
            Services("Send Money", ""),
            Services("Cashback Offer", ""),
            Services("Movie Tickets", ""),
            Services("Flight Tickets", ""),
            Services("More Options", ""),
            Services("Send Money", "https://pbs.twimg.com/media/ETnk8TzUcAA3Ohj.jpg"),
            Services("Send Money", "https://pbs.twimg.com/media/ETnk8TzUcAA3Ohj.jpg"),
        ))
}