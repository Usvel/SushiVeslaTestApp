package com.example.sushiveslatestapp.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sushiveslatestapp.domain.entitys.home.Users
import com.example.sushiveslatestapp.domain.entitys.login.DateTime
import com.example.sushiveslatestapp.domain.entitys.login.Weather
import io.reactivex.disposables.CompositeDisposable

class LoginViewModel : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val _dateTime: MutableLiveData<DateTime> = MutableLiveData()
    val dateTime: LiveData<DateTime> = _dateTime

    private val _weather: MutableLiveData<Weather> = MutableLiveData()
    val weather: LiveData<Weather> = _weather

    init {
        _dateTime.value = DateTime(time = "06:20 PM", date = "Nov.10.2020 | Wednesday")
        _weather.value = Weather(celsius = "34° C", "")
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}