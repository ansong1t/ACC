package com.appetizercodingchallenge.util

import org.threeten.bp.Duration
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Date
import java.util.TimeZone

fun formatDate(
    date: String,
    parseFormat: String = "yyyy-MM-dd'T'HH:mm:ss'Z'",
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
    val duration = Duration.ofMillis(millis)
    return if (duration.toHours() > 0) String.format(
        "%d:%02d:%02d",
        duration.toHours(),
        duration.toMinutes() - (duration.toHours() * 60),
        duration.seconds - (duration.toMinutes() * 60)
    )
    else String.format(
            "%d:%02d",
            duration.toMinutes(),
            duration.seconds - (duration.toMinutes() * 60)
        )
}
