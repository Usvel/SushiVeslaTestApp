package com.example.sushiveslatestapp.data.cache.user

import com.example.sushiveslatestapp.domain.entitys.menu.User
import io.reactivex.Single
import javax.inject.Inject

class MemoryCacheUserImpl @Inject constructor() : MemoryCacheUser {
    private var user: User = User("", "", "")

    override fun getUser() = Single.just(user)

    override fun setUser(user: User) {
        this.user = user
    }
}