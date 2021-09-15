package com.example.sushiveslatestapp.data.repository

import com.example.sushiveslatestapp.data.cache.user.MemoryCacheUser
import com.example.sushiveslatestapp.data.remote.menu.MenuRemoteSource
import com.example.sushiveslatestapp.domain.entitys.menu.User
import com.example.sushiveslatestapp.domain.repositorys.MenuRepository
import io.reactivex.Single
import javax.inject.Inject

class MenuRepositoryImpl @Inject constructor(
    private val menuRemoteSource: MenuRemoteSource,
    private val memoryCacheUser: MemoryCacheUser
) : MenuRepository {
    override fun getUser(): Single<User> {
        return memoryCacheUser.getUser().flatMap {
            if (it.name == "") {
                menuRemoteSource.getUser().map {
                    it.apply { memoryCacheUser.setUser(it) }
                }
            } else {
                Single.just(it)
            }
        }
    }
}