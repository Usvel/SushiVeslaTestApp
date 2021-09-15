package com.example.sushiveslatestapp.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sushiveslatestapp.domain.entitys.menu.User
import com.example.sushiveslatestapp.domain.usecases.GetUserUseCase
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MenuViewModel @Inject constructor(private val menuUseCase: GetUserUseCase) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val _user: MutableLiveData<User> = MutableLiveData()
    val user: LiveData<User> = _user

    fun getCurrentData() {
        compositeDisposable.add(
            menuUseCase.getUser().subscribe({
                _user.value = it
            }, {

            })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}