package com.example.firstandroidapplication.dto

fun howLongAgo(seconds: Int) : String{

    val minutes: Int = seconds / 60
    if (seconds / 60 == 0){
        return "менее минуты назад"
    }
    else if (minutes < 60){
        return "$minutes ${getSuffixForMinutes(minutes)} назад"
    }

    val hours: Int = minutes / 60
    return when {
        hours == 1 -> "час назад"
        hours < 24 -> "$hours ${getSuffixForHours(
            hours
        )} назад"
        hours == 24 -> "один день назад"
        hours < 24 * 30 -> "несколько дней назад"
        hours == 365 * 24 -> "год назад"
        hours < 365 * 24 -> "несколько месяцев назад"
        else -> "более года назад"
    }
}

private fun getSuffixForMinutes(minutes: Int) : String {

    return when (minutes){
        1, 21, 31, 41, 51 -> "минуту"
        2, 3, 4, 22, 23, 24, 32, 33, 34, 42, 43, 44, 52, 53, 54 -> "минуты"
        else -> "минут"
    }}

private fun getSuffixForHours(hours: Int): String {
    return when (hours) {
        21 -> "час"
        2, 3, 4, 22, 23 -> "часа"
        else -> "часов"
    }
}
