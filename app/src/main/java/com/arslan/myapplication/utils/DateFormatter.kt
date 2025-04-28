package com.arslan.myapplication.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

fun String.dateFormatter(): String{
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val outputFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale("ru"))

    val date = LocalDate.parse(this, inputFormatter)
    return date.format(outputFormatter)
}