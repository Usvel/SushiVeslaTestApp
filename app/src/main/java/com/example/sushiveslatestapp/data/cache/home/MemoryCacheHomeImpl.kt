package com.example.sushiveslatestapp.data.cache.home

import com.example.sushiveslatestapp.domain.entitys.home.HomeData
import io.reactivex.Single
import javax.inject.Inject

class MemoryCacheHomeImpl @Inject constructor() : MemoryCacheHome {
    private var homeData: HomeData = HomeData(0, listOf(), listOf())

    override fun getHomeData() = Single.just(homeData)

    override fun setHomeData(homeData: HomeData) {
        this.homeData = homeData
    }
}