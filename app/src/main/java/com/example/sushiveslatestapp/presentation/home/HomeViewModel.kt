package com.example.sushiveslatestapp.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sushiveslatestapp.domain.entitys.home.Services
import com.example.sushiveslatestapp.domain.entitys.home.Users
import com.example.sushiveslatestapp.domain.usecases.GetHomeUserCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val homeUserCase: GetHomeUserCase
) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val _balance: MutableLiveData<Int> = MutableLiveData()
    val balance: LiveData<Int> = _balance

    private val _listUsers: MutableLiveData<List<Users>> = MutableLiveData()
    val listUsers: LiveData<List<Users>> = _listUsers

    private val _listServices: MutableLiveData<List<Services>> = MutableLiveData()
    val listServices: LiveData<List<Services>> = _listServices

    fun getCurrentData() {
        compositeDisposable.add(
            homeUserCase.getHomeData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ homeData ->
                    _balance.value = homeData.balance
                    _listServices.value = homeData.listServices
                    _listUsers.value = homeData.listUsers
                }, {

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}