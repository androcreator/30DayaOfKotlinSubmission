package com.kotlin.project.todo.extension

import org.threeten.bp.Instant
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneId
import java.text.SimpleDateFormat
import java.util.*

const val dateFormat = "MMMM d,  yyyy"

// Usage of Extension Function function.
fun Long.getDateTimeFromTimeStamp(): String? {
    val dateFormat = SimpleDateFormat(dateFormat)
    dateFormat.setTimeZone(TimeZone.getDefault())
    val dateTime = Date(this)
    return dateFormat.format(dateTime)
}

fun Long.isToday(): Boolean {
   val date = Instant.ofEpochMilli(this).atZone(ZoneId.systemDefault())
   return date.toLocalDate().isEqual(LocalDate.now())
}

fun Long.isYesterday(): Boolean  {
    val date = Instant.ofEpochMilli(this).atZone(ZoneId.systemDefault())
    return date.toLocalDate().isEqual(LocalDate.now().minusDays(1L))
}
