package com.example.sushiveslatestapp.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sushiveslatestapp.domain.entitys.home.Services
import com.example.sushiveslatestapp.domain.entitys.home.Users
import io.reactivex.disposables.CompositeDisposable

class HomeViewModel : ViewModel(
) {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val _balance: MutableLiveData<String> = MutableLiveData()
    val balance: LiveData<String> = _balance

    private val _listUsers: MutableLiveData<List<Users>> = MutableLiveData()
    val listUsers: LiveData<List<Users>> = _listUsers

    private val _listServices: MutableLiveData<List<Services>> = MutableLiveData()
    val listServices: LiveData<List<Services>> = _listServices

    init {
        _balance.value = "20,600"
        _listUsers.value = listOf(
            Users("Dahsa", "https://pbs.twimg.com/media/ETnk8TzUcAA3Ohj.jpg"),
            Users("Flag", ""),
            Users("Flag", ""),
            Users("Alena", "")
        )

        _listServices.value = listOf(
            Services("Send Money", "https://pbs.twimg.com/media/ETnk8TzUcAA3Ohj.jpg"),
            Services("Send Money", ""),
            Services("Send Money", ""),
            Services("Send Money", ""),
            Services("Cashback Offer", ""),
            Services("Movie Tickets", ""),
            Services("Flight Tickets", ""),
            Services("More Options", "")
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}