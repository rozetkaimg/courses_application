package com.rozetka.cources.ext

import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

fun formatDateString(dateString: String): String {
    val date = LocalDate.parse(dateString)
    val formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale("ru"))
    return date.format(formatter)
}
fun parseDate(dateString: String): Date {
    val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return try {
        format.parse(dateString) ?: Date(0)
    } catch (e: ParseException) {
        e.printStackTrace()
        Date(0)
    }
}