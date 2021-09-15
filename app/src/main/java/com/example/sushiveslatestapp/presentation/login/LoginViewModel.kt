package com.example.sushiveslatestapp.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sushiveslatestapp.domain.entitys.login.DateTime
import com.example.sushiveslatestapp.domain.entitys.login.Weather
import com.example.sushiveslatestapp.domain.usecases.GetLoginUseCase
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val loginUseCase: GetLoginUseCase) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val _dateTime: MutableLiveData<DateTime> = MutableLiveData()
    val dateTime: LiveData<DateTime> = _dateTime

    private val _weather: MutableLiveData<Weather> = MutableLiveData()
    val weather: LiveData<Weather> = _weather

    fun getCurrentData() {
        compositeDisposable.add(
            loginUseCase.getDateTime().subscribe {
                _dateTime.value = it
            }
        )
        compositeDisposable.add(
            loginUseCase.getWeather().subscribe({
                _weather.value = it
            }, {

            })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}