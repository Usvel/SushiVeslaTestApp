package com.example.sushiveslatestapp.data.repository

import com.example.sushiveslatestapp.data.cache.home.MemoryCacheHome
import com.example.sushiveslatestapp.data.remote.home.HomeRemoteSource
import com.example.sushiveslatestapp.domain.entitys.home.HomeData
import com.example.sushiveslatestapp.domain.repositorys.HomeRepository
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val memoryCacheHome: MemoryCacheHome,
    private val homeDataRemoteSource: HomeRemoteSource,
) :
    HomeRepository {
    companion object {
        private const val DELAY_TIME: Long = 2
    }

    override fun getHomeData(): Single<HomeData> {
        return memoryCacheHome.getHomeData().flatMap {
            if (it.listServices.isEmpty()) {
                val balance =
                    homeDataRemoteSource.getBalance().delay(DELAY_TIME, TimeUnit.SECONDS)
                val users = homeDataRemoteSource.getListUsers()
                val services = homeDataRemoteSource.geListServices()
                Single.zip(balance, users, services, { b, u, s ->
                    HomeData(b, u, s).apply {
                        memoryCacheHome.setHomeData(this)
                    }
                })
            } else {
                Single.just(it)
            }
        }
    }
}