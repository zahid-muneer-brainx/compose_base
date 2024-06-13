package com.brainxtech.extensions.utils

import android.text.format.DateUtils
import com.brainxtech.extensions.utils.Constants.EMPTY_STRING
import com.brainxtech.extensions.utils.Constants.ZERO
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun getCalender(
    timeInMillis:Long?= null,
    date: Date? = Date(),
    add: Int? = ZERO,
    addType: Int = Calendar.SECOND,
    shouldResetSecond: Boolean = false,
    shouldResetToStartMonth: Boolean = false,
    shouldStartOfDay: Boolean = false,
    shouldEndOfMonth: Boolean = false
): Calendar {
    val calender = Calendar.getInstance()
    calender.time = date ?: Date()
    timeInMillis?.let {
        calender.timeInMillis = it
    }
    if (shouldResetSecond) {
        calender.set(Calendar.SECOND, 0)
    }
    if (shouldResetToStartMonth || shouldEndOfMonth) {
        calender.set(Calendar.SECOND, 0)
        calender.set(Calendar.HOUR_OF_DAY, 0)
        calender.set(Calendar.MINUTE, 0)
        calender.set(Calendar.DAY_OF_MONTH, 1)
        calender.set(Calendar.MILLISECOND, 0)
    }
    if (shouldStartOfDay) {
        calender.set(Calendar.SECOND, 0)
        calender.set(Calendar.HOUR_OF_DAY, 0)
        calender.set(Calendar.MINUTE, 0)
        calender.set(Calendar.MILLISECOND, 0)
    }

    if (shouldEndOfMonth) {
        calender.set(Calendar.DAY_OF_MONTH, calender.getActualMaximum(Calendar.DAY_OF_MONTH))

    }

    calender.add(addType, add ?: 0)
    return calender
}

fun Calendar?.addCalender(dateType: Int, value: Int):Calendar?{
    this?.add(dateType, value)
    return this
}

fun getCurrentDeviceTime(): Date {
    return getCalender().time
}

fun Long?.getHoursAndMinutesUsingCalendar(): Pair<Int, Int>? {
    if (this==null) return null
    Calendar.getInstance().apply {
        timeInMillis = this@getHoursAndMinutesUsingCalendar?: Constants.ZERO_LONG
        return Pair(get(Calendar.HOUR_OF_DAY),get(Calendar.MINUTE))
    }
}

fun Calendar.isToday():Boolean{
    return DateUtils.isToday(time.time)
}

fun Calendar.isYesterday():Boolean{
    return DateUtils.isToday(time.time + DateUtils.DAY_IN_MILLIS)
}

fun Calendar.isTomorrow(date: Date?):Boolean{
    return DateUtils.isToday(time.time - DateUtils.DAY_IN_MILLIS)
}

fun Calendar.isDateInFuture(): Boolean {
    return timeInMillis > getCalender().timeInMillis
}

fun Calendar.isDateInPast(): Boolean {
    return timeInMillis < getCalender().timeInMillis
}

fun Calendar?.formatDateIntoString(dateFormatter:String,shouldAddLocale:Boolean = true,enableTimeZone:Boolean=false,locale: String?="en",timeZone: String?=null) : String {
    val formatter = if (shouldAddLocale) SimpleDateFormat(dateFormatter, Locale(locale, EMPTY_STRING))
    else SimpleDateFormat(dateFormatter)

    if (enableTimeZone){
        formatter.timeZone =  TimeZone.getTimeZone(timeZone?:DateTimeConstants.UTC)
    }
    return try {
        formatter.format(this?.time)
    } catch (e: ParseException) {
        EMPTY_STRING
    } catch (ex:Exception){
        EMPTY_STRING
    }
}