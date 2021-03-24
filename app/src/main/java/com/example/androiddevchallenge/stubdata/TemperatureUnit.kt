package com.example.androiddevchallenge.stubdata

enum class TemperatureUnit(val unit:String) {
    CELSIUS("°C"),
    FAHRENHEIT("°F")
}

fun Int.toFahrenheit(): Int =  ((this * 1.8f) + 32).toInt()
