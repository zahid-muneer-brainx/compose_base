package com.brainxtech.extensions.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.brainxtech.extensions.utils.Constants.ONE
import com.brainxtech.extensions.utils.Constants.ZERO
import java.util.Calendar

fun Context.startAlarm(calendar: Calendar,alarmId:Int, intent: Intent) {
    (getSystemService(Context.ALARM_SERVICE) as AlarmManager).apply {
        with(PendingIntent.getBroadcast(this@startAlarm, alarmId, intent,  PendingIntent.FLAG_IMMUTABLE)){
            calendar.apply {
                if (before(getCalender())){
                    addCalender(Calendar.DATE, ONE)
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    setAlarmClock(AlarmManager.AlarmClockInfo(timeInMillis, this@with), this@with)
                else
                    set(AlarmManager.RTC_WAKEUP, timeInMillis, this@with)
            }
        }
    }
}

fun Context.cancelAlarm(alarmId:Int, intent: Intent) {
    (getSystemService(Context.ALARM_SERVICE) as AlarmManager).cancel(PendingIntent.getBroadcast(this, alarmId, intent,  PendingIntent.FLAG_IMMUTABLE))
}

fun getCalendarForAlarm(hourOfDay:Int= ZERO, minute:Int= ZERO, second:Int= ZERO, shouldAddDay:Boolean=false,days:Int= ONE): Calendar {
    return getCalender().apply {
        set(Calendar.HOUR_OF_DAY, hourOfDay)
        set(Calendar.MINUTE, minute)
        set(Calendar.SECOND, second)
        if(shouldAddDay){
            addCalender(Calendar.DATE, days)
        }
    }
}

fun Context.updateAlarm(alarmId: Int, intent: Intent, newCalendar: Calendar) {
    cancelAlarm(alarmId, intent)
    startAlarm(newCalendar, alarmId, intent)
}

fun Context.isAlarmSet(alarmId: Int, intent: Intent): Boolean {
    return PendingIntent.getBroadcast(
        this,
        alarmId,
        intent,
        PendingIntent.FLAG_NO_CREATE or PendingIntent.FLAG_IMMUTABLE
    ) != null
}