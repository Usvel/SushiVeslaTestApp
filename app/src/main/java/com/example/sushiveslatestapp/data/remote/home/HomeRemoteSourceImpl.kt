package com.example.sushiveslatestapp.data.remote.home

import com.example.sushiveslatestapp.domain.entitys.home.Services
import com.example.sushiveslatestapp.domain.entitys.home.Users
import io.reactivex.Single
import javax.inject.Inject

//Тестовые данные
class HomeRemoteSourceImpl @Inject constructor() : HomeRemoteSource {
    override fun getBalance(): Single<Int> = Single.just(20600)

    override fun getListUsers(): Single<List<Users>> = Single.just(
        listOf(
            Users("Mike", ""),
            Users("Joshpeh", ""),
            Users("Dasha", "https://pbs.twimg.com/media/ETnk8TzUcAA3Ohj.jpg")
        )
    )

    override fun geListServices(): Single<List<Services>> = Single.just(
        listOf(
            Services("Send Money", ""),
            Services("Receive Money", ""),
            Services("Mobile Prepaid", ""),
            Services("Electricity", ""),
            Services("Cashback Offer", ""),
            Services("Movie Tickets", ""),
            Services("Flight Tickets", ""),
            Services("More Options", "")
        ))
}