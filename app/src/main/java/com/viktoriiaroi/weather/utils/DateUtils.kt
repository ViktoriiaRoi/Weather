package com.viktoriiaroi.weather.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    private val dateFormatter = SimpleDateFormat("dd MMM HH:mm", Locale.ENGLISH)

    fun timestampToDate(timestamp: Int): String =
        dateFormatter.format(Date(timestamp.toLong() * 1000))
}