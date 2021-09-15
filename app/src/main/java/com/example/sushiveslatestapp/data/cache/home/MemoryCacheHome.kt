package com.example.sushiveslatestapp.data.cache.home

import com.example.sushiveslatestapp.domain.entitys.home.HomeData
import io.reactivex.Single

interface MemoryCacheHome {

    fun getHomeData(): Single<HomeData>
    fun setHomeData(homeData: HomeData)
}