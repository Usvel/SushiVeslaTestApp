package com.example.sushiveslatestapp.data.receiver

import com.example.sushiveslatestapp.domain.entitys.login.DateTime
import io.reactivex.Observable

interface DateTimeReceiver {
    fun getDateTime(): Observable<DateTime>
}