package com.example.sushiveslatestapp.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sushiveslatestapp.domain.entitys.menu.User
import com.example.sushiveslatestapp.domain.usecases.GetUserUseCase
import com.example.sushiveslatestapp.presentation.NetworkRequestState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MenuViewModel @Inject constructor(private val menuUseCase: GetUserUseCase) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val _networkState: MutableLiveData<NetworkRequestState> = MutableLiveData()
    val networkState: LiveData<NetworkRequestState> = _networkState

    private val _toggleVisibility: MutableLiveData<Boolean> = MutableLiveData()
    val toggleVisibility: LiveData<Boolean> = _toggleVisibility

    private val _user: MutableLiveData<User> = MutableLiveData()
    val user: LiveData<User> = _user

    init {
        _toggleVisibility.value = false
    }

    fun getCurrentData() {
        if (_networkState.value != NetworkRequestState.LOADING) {
            _networkState.value = NetworkRequestState.LOADING
            _toggleVisibility.value = true
            compositeDisposable.add(
                menuUseCase.getUser().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe({
                        _user.value = it
                        _networkState.value = NetworkRequestState.SUCCESS
                    }, {
                        _networkState.value = NetworkRequestState.ERROR
                    })
            )
        }
    }

    fun deleteData() {
        _toggleVisibility.value = false
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}