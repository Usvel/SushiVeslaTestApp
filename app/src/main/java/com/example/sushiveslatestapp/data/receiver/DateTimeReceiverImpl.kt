package com.example.sushiveslatestapp.data.receiver

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.example.sushiveslatestapp.domain.entitys.login.DateTime
import io.reactivex.Observable
import java.text.SimpleDateFormat
import java.util.*

class DateTimeReceiverImpl(private val context: Context, private val intentFilter: IntentFilter) :
    DateTimeReceiver {

    @SuppressLint("SimpleDateFormat")
    private fun getDateTimeNow(): DateTime {
        val cal: Calendar = Calendar.getInstance()
        val dateCal = cal.time
        val formatTime = SimpleDateFormat("hh:mm aa")
        formatTime.timeZone = cal.timeZone
        val time = formatTime.format(dateCal)

        val formatDate = SimpleDateFormat("MMM.dd.yyyy|EEEE")
        formatDate.timeZone = cal.timeZone
        val date = formatDate.format(cal.time)
        return DateTime(date, time)
    }

    override fun getDateTime(): Observable<DateTime> {
        return Observable.create { emitter ->
            emitter.onNext(getDateTimeNow())
            val receiver = object : BroadcastReceiver() {
                override fun onReceive(context: Context, intent: Intent) {
                    if (intent.action == Intent.ACTION_TIME_TICK) {
                        emitter.onNext(getDateTimeNow())
                    }
                }
            }
            context.registerReceiver(receiver, intentFilter)
            emitter.setCancellable { context.unregisterReceiver(receiver) }
        }
    }
}