package com.example.sushiveslatestapp.domain.usecases

import com.example.sushiveslatestapp.domain.repositorys.HomeRepository
import javax.inject.Inject

//Чтобы отдеально присылать данные можно добавить методы (сделать Interactor)
class GetHomeUserCase @Inject constructor(private val homeRepository: HomeRepository) {
    fun getHomeData() = homeRepository.getHomeData()
}