package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date.format(pattern: String = "HH:mm:SS dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))

    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time

    time += when (units) {
        TimeUnits.SECOND -> value *  TimeUnits.SECOND.value
        TimeUnits.MINUTE -> value * TimeUnits.MINUTE.value
        TimeUnits.HOUR -> value * TimeUnits.HOUR.value
        TimeUnits.DAY -> value * TimeUnits.DAY.value
    }
    this.time = time

    return this
}

enum class TimeUnits(val value: Long){
    SECOND(1000L),
    MINUTE(60 * SECOND.value),
    HOUR(60 * MINUTE.value),
    DAY(24 * HOUR.value)
}