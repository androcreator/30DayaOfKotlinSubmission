package com.kotlin.project.todo.extension

import java.text.SimpleDateFormat
import java.util.*

const val dateFormat = "MMMM d,  yyyy"

fun Long.getDateTimeFromTimeStamp(): String? {
    val dateFormat = SimpleDateFormat(dateFormat)
    dateFormat.setTimeZone(TimeZone.getDefault())
    val dateTime = Date(this)
    return dateFormat.format(dateTime)
}