package com.andrew.associate.hellokotlin.model

import android.view.View
import java.text.SimpleDateFormat

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun String.formatDate(fromDateFormat:String="yyyy-MM-dd", toDateFormat:String = "E, dd MMM yyyy"): String{
    val date = SimpleDateFormat(fromDateFormat).parse(this)
    val dateFormatter = SimpleDateFormat(toDateFormat)
    return dateFormatter.format(date)
}