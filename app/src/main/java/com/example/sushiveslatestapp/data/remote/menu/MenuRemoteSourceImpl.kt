package com.example.sushiveslatestapp.data.remote.menu

import com.example.sushiveslatestapp.domain.entitys.menu.User
import io.reactivex.Single
import javax.inject.Inject

class MenuRemoteSourceImpl @Inject constructor() : MenuRemoteSource {
    override fun getUser(): Single<User> =
        Single.just(User("Carol", "", "Seattle,Washington"))
}