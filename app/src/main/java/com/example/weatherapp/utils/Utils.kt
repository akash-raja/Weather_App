package com.example.weatherapp.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

fun getDay(dateToConvert: String): String {
    return try {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        val date: Date = simpleDateFormat.parse(dateToConvert)
        val dateFormat = SimpleDateFormat("EEEE")
        dateFormat.format(date)
    } catch (e: ParseException) {
        "Unable to Fetch Day"
    }
}