package com.example.sushiveslatestapp.domain.entitys.home

data class HomeData(
    val balance: Int,
    val listUsers: List<Users>,
    val listServices: List<Services>
)