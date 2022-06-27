package com.russell.temper.template.core

import java.text.SimpleDateFormat
import java.util.*

fun Date.getLongDisplayName(): String {
    val dateFormatter = SimpleDateFormat("EEEE, dd MMM", Locale.getDefault())
    return dateFormatter.format(this)
}

fun Date.toServerStringFormat(): String {
    val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return dateFormatter.format(this)
}

fun Date.createFromServerDateTime(time: String): Date {
//    2022-06-27T23:00:00+02:00
    val pattern = "yyyy-MM-dd'T'HH:mm:ss"
    try {
        val format = SimpleDateFormat(pattern, Locale.getDefault())
        format.parse(time)?.let {
            setTime(it.time)
        }
    } catch (e: Exception) { }

    return this
}

fun Date.toTimeOfDayString(): String {
    val pattern = "HH:mm"
    val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    return dateFormat.format(this)
}
