package com.example.sushiveslatestapp.domain.repositorys

import com.example.sushiveslatestapp.domain.entitys.home.HomeData
import io.reactivex.Single

interface HomeRepository {
    fun getHomeData(): Single<HomeData>
}