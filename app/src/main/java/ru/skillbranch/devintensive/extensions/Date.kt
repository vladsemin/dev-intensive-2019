package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

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

fun Date.humanizeDiff(date: Date = Date()): String {
    val timeDiff: Long = abs(this.time - date.time)
    val inThePast: Boolean = this.time < date.time

    return when {
        timeDiff <= TimeUnits.SECOND.value -> "только что"
        timeDiff <= TimeUnits.SECOND.value * 45 -> getTimeForm("несколько секунд", inThePast)
        timeDiff <= TimeUnits.SECOND.value * 75 -> getTimeForm("минуту", inThePast)
        timeDiff <= TimeUnits.MINUTE.value * 45 -> getTimeForm(TimeUnits.MINUTE.plural((timeDiff / TimeUnits.MINUTE.value).toInt()), inThePast)
        timeDiff <= TimeUnits.MINUTE.value * 75 -> getTimeForm("час", inThePast)
        timeDiff <= TimeUnits.HOUR.value * 22 -> getTimeForm(TimeUnits.HOUR.plural((timeDiff / TimeUnits.HOUR.value).toInt()), inThePast)
        timeDiff <= TimeUnits.HOUR.value * 26 -> getTimeForm("день", inThePast)
        timeDiff <= TimeUnits.DAY.value * 360 -> getTimeForm(TimeUnits.DAY.plural((timeDiff / TimeUnits.DAY.value).toInt()), inThePast)
        else -> if(inThePast) "более года назад" else "более чем через год"
    }
}

fun getTimeForm(formattingString: String, isPast: Boolean): String {
    val prefix = if (isPast) "" else "через"
    val suffix = if (isPast) "назад" else ""
    return "$prefix $formattingString $suffix".trim()
}

fun getPluralString(value: Int, units: TimeUnits): String {
    return when(value){
        1 -> Plurals.ONE.getString(units)
        in 2..4 -> Plurals.FEW.getString(units)
        0, in 5..19 -> Plurals.MANY.getString(units)
        else -> getPluralString(value % 10, units)
    }
}

enum class TimeUnits(val value: Long){
    SECOND(1000L),
    MINUTE(60 * SECOND.value),
    HOUR(60 * MINUTE.value),
    DAY(24 * HOUR.value);

    fun plural(value: Int): String = "$value ${getPluralString(value, this)}"
}

enum class Plurals(private val second: String, private val minute: String, private val hour: String, private val day: String){
    ONE("секунду", "минуту", "час", "день"),
    FEW("секунды", "минуты", "часа", "дня"),
    MANY("секунд","минут", "часов", "дней");

    fun getString(unit: TimeUnits): String {
        return when(unit){
            TimeUnits.SECOND -> second
            TimeUnits.MINUTE -> minute
            TimeUnits.HOUR -> hour
            TimeUnits.DAY -> day
        }
    }
}