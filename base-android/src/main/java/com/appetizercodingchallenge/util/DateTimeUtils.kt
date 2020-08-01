package com.appetizercodingchallenge.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Date
import java.util.TimeZone
import java.util.concurrent.TimeUnit

fun formatDate(
    date: String,
    parseFormat: String = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
    outputFormat: String = "yyyy-MM-dd"
): String {
    val format = SimpleDateFormat(parseFormat, Locale.US)
    format.timeZone = TimeZone.getTimeZone("UTC")
    val dateFormat = try {
        format.parse(date)
    } catch (e: ParseException) {
        Date()
    }
    val sdf = SimpleDateFormat(outputFormat, Locale.US)
    return sdf.format(dateFormat!!)
}

fun getDuration(millis: Long): String {
    return String.format(
        "%d:%02d",
        TimeUnit.MILLISECONDS.toMinutes(millis),
        TimeUnit.MILLISECONDS.toSeconds(millis) -
                TimeUnit.MINUTES.toSeconds(
                    TimeUnit.MILLISECONDS.toMinutes(
                        millis
                    )
                )
    )
}
