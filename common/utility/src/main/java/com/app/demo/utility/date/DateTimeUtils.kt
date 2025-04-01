package com.app.demo.utility.date

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

object DateTimeUtils {
    private const val API_DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    private const val FRIENDLY_DATE_PATTERN = "dd.MM.yyyy HH:mm"

    fun convertApiDateToPrettyDate(
        dateApi: String,
    ): String {
        val apiDateFormat = SimpleDateFormat(API_DATE_PATTERN, Locale.getDefault()).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }

        val prettyDateFormat = SimpleDateFormat(FRIENDLY_DATE_PATTERN, Locale.getDefault()).apply {
            timeZone = TimeZone.getDefault()
        }

        val date = apiDateFormat.parse(dateApi) ?: return dateApi
        return prettyDateFormat.format(date)
    }
}
