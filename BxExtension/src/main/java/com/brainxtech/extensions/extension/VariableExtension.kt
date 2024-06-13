package com.brainxtech.extensions.extension

import com.brainxtech.extensions.utils.Constants.EMPTY_STRING
import com.brainxtech.extensions.utils.Constants.ONE_HOUR
import com.brainxtech.extensions.utils.Constants.ONE_MINUTE
import com.brainxtech.extensions.utils.Constants.SECOND
import com.brainxtech.extensions.utils.Constants.SECOND_LONG
import com.brainxtech.extensions.utils.Constants.ZERO
import java.math.BigDecimal

fun Int?.orZero(): Int = this ?: 0
fun Double?.orZero(): Double = this ?: 0.0
fun Long?.orZero(): Long = this ?: 0L
fun BigDecimal?.orZero(): BigDecimal = this ?: BigDecimal.ZERO
fun String?.orEmptyString(): String = this ?: EMPTY_STRING
fun Int?.orOne(): Int = this ?: 1
fun Double?.orOne(): Double = this ?: 1.0
fun Long?.orOne(): Long = this ?: 1L
fun BigDecimal?.orOne(): BigDecimal = this ?: BigDecimal.ONE

fun Int.greaterThan(number: Int): Boolean = this > number

fun Boolean?.orFalse(): Boolean = this ?: false

fun Long?.isNull(): Boolean = this == null
fun Int?.isNull(): Boolean = this == null
fun Double?.isNull(): Boolean = this == null
fun BigDecimal?.isNull(): Boolean = this == null
fun Boolean?.isNull(): Boolean = this == null
fun Boolean?.isNotNull(): Boolean = this != null

fun Boolean.toFloat() = if (this) 1f else 0f

fun Long.toHours()=this/60
fun Long.toMints()=this%60
fun Long.toSecond()=this/SECOND_LONG

fun hoursToSeconds(hour:Int, mint:Int):Long{
  return  if (hour== ZERO) mint.mintToSecond() else if (mint!=ZERO) hour.hourToSecond()+mint.mintToSecond() else  hour.hourToSecond()
}

fun Int.mintToSecond() = this* ONE_MINUTE
fun Int.hourToSecond() = this* ONE_HOUR
fun Long.secondsToHours() = this / ONE_HOUR
fun Long.secondsToMint() = (this / ONE_MINUTE) % SECOND
fun Long.milliToSec() = (this / ONE_MINUTE) % (SECOND* SECOND)
fun Int.pad(): String {
  return this.toString().padStart(2, '0')
}