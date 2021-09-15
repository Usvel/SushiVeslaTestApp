package com.example.sushiveslatestapp.domain.usecases

import com.example.sushiveslatestapp.domain.repositorys.MenuRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val menuRepository: MenuRepository) {
    fun getUser() = menuRepository.getUser()
}