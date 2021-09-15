package com.example.sushiveslatestapp.data.remote.menu

import com.example.sushiveslatestapp.domain.entitys.menu.User
import io.reactivex.Single

interface MenuRemoteSource {
    fun getUser(): Single<User>
}