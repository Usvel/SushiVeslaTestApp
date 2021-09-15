package com.example.sushiveslatestapp.domain.repositorys

import com.example.sushiveslatestapp.domain.entitys.menu.User
import io.reactivex.Single

interface MenuRepository {
    fun getUser(): Single<User>
}