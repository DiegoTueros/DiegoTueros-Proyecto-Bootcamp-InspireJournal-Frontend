package com.nttdata.inspirationjournal2.view

import com.nttdata.inspirationjournal2.R
import java.text.SimpleDateFormat
import java.util.*

fun formatDate(date: String): String{
    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    val date: Date = format.parse(date)
    val dateFormat = SimpleDateFormat("dd/MM").format(date)
    return dateFormat
}

