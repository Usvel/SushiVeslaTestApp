package com.example.sushiveslatestapp.domain.usecases

import com.example.sushiveslatestapp.domain.repositorys.LoginRepository
import javax.inject.Inject

class GetLoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {
    fun getDateTime() = loginRepository.getDateTime()
    fun getWeather() = loginRepository.getWeather()
}