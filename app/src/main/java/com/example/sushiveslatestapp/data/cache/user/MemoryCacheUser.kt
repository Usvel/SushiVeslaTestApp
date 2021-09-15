package com.example.sushiveslatestapp.data.cache.user

import com.example.sushiveslatestapp.domain.entitys.menu.User
import io.reactivex.Single

interface MemoryCacheUser {
    fun getUser(): Single<User>
    fun setUser(user: User)
}