package com.example.sushiveslatestapp.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sushiveslatestapp.domain.entitys.login.DateTime
import com.example.sushiveslatestapp.domain.entitys.login.LoginData
import com.example.sushiveslatestapp.domain.entitys.login.Weather
import com.example.sushiveslatestapp.domain.usecases.GetLoginUseCase
import com.example.sushiveslatestapp.presentation.NetworkRequestState
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val loginUseCase: GetLoginUseCase) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val _dateTime: MutableLiveData<DateTime> = MutableLiveData()
    val dateTime: LiveData<DateTime> = _dateTime

    private val _weather: MutableLiveData<Weather> = MutableLiveData()
    val weather: LiveData<Weather> = _weather

    private val _networkState: MutableLiveData<NetworkRequestState> = MutableLiveData()
    val networkState: LiveData<NetworkRequestState> = _networkState

    fun getCurrentData() {
        if (_networkState.value != NetworkRequestState.LOADING) {
            val dateTime =
                loginUseCase.getDateTime()
            val weather =
                loginUseCase.getWeather().toObservable()
            compositeDisposable.add(
                Observable.combineLatest(dateTime, weather, { dateTime, weather ->
                    LoginData(dateTime, weather)
                }).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe({
                        _weather.value = it.weather
                        _dateTime.value = it.dateTime
                        _networkState.value = NetworkRequestState.SUCCESS
                    }, {
                        _networkState.value = NetworkRequestState.ERROR
                    })
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}