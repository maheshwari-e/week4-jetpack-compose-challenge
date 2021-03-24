package com.example.androiddevchallenge.stubdata

import androidx.annotation.DrawableRes
import com.example.androiddevchallenge.R

data class WeatherDetail(
    val name: String,
    @DrawableRes
    val image: Int,
    val value: String
)

val minneapolisWeatherDetails = listOf(
    WeatherDetail("Sunrise", R.drawable.ic_sunrise, "7:17 AM"),
    WeatherDetail("Sunset", R.drawable.ic_sunset, "7:23 PM"),
    WeatherDetail("Air Quality", R.drawable.ic_air_quality, "Mod 65"),
    WeatherDetail("Wind", R.drawable.ic_wind, "23 km/h"),
    WeatherDetail("Humidity", R.drawable.ic_humidity, "35%"),
    WeatherDetail("Pressure", R.drawable.ic_pressure, "1028.5 mBar"),
    WeatherDetail("Visibility", R.drawable.ic_visibility, "10 mi"),
    WeatherDetail("UV Index", R.drawable.ic_uv_index, "3"),
    WeatherDetail("Dew Point", R.drawable.ic_dew_point, "-6°"),
    WeatherDetail("Precipitation", R.drawable.ic_precipition, "0 in")
)

val fairbanksWeatherDetails = listOf(
    WeatherDetail("Sunrise", R.drawable.ic_sunrise, "7:49 AM"),
    WeatherDetail("Sunset", R.drawable.ic_sunset, "8:05 PM"),
    WeatherDetail("Air Quality", R.drawable.ic_air_quality, "Mod 59"),
    WeatherDetail("Wind", R.drawable.ic_wind, "3 km/h"),
    WeatherDetail("Humidity", R.drawable.ic_humidity, "71%"),
    WeatherDetail("Pressure", R.drawable.ic_pressure, "1018.0 mBar"),
    WeatherDetail("Visibility", R.drawable.ic_visibility, "5 mi"),
    WeatherDetail("UV Index", R.drawable.ic_uv_index, "0"),
    WeatherDetail("Dew Point", R.drawable.ic_dew_point, "-22°"),
    WeatherDetail("Precipitation", R.drawable.ic_precipition, "0 in")
)

val munichWeatherDetails = listOf(
    WeatherDetail("Sunrise", R.drawable.ic_sunrise, "6:16 AM"),
    WeatherDetail("Sunset", R.drawable.ic_sunset, "6:26 PM"),
    WeatherDetail("Air Quality", R.drawable.ic_air_quality, "Very Good 2.5"),
    WeatherDetail("Wind", R.drawable.ic_wind, "8 km/h"),
    WeatherDetail("Humidity", R.drawable.ic_humidity, "88%"),
    WeatherDetail("Pressure", R.drawable.ic_pressure, "1021.0 mBar"),
    WeatherDetail("Visibility", R.drawable.ic_visibility, "2.4 mi"),
    WeatherDetail("UV Index", R.drawable.ic_uv_index, "0"),
    WeatherDetail("Dew Point", R.drawable.ic_dew_point, "-2°"),
    WeatherDetail("Precipitation", R.drawable.ic_precipition, "0.1 in")
)

val seattleWeatherDetails = listOf(
    WeatherDetail("Sunrise", R.drawable.ic_sunrise, "7:11 AM"),
    WeatherDetail("Sunset", R.drawable.ic_sunset, "7:21 PM"),
    WeatherDetail("Air Quality", R.drawable.ic_air_quality, "Good 45"),
    WeatherDetail("Wind", R.drawable.ic_wind, "19 km/h"),
    WeatherDetail("Humidity", R.drawable.ic_humidity, "55%"),
    WeatherDetail("Pressure", R.drawable.ic_pressure, "1014.2 mBar"),
    WeatherDetail("Visibility", R.drawable.ic_visibility, "10 mi"),
    WeatherDetail("UV Index", R.drawable.ic_uv_index, "3"),
    WeatherDetail("Dew Point", R.drawable.ic_dew_point, "3°"),
    WeatherDetail("Precipitation", R.drawable.ic_precipition, "0.2 in")
)

val yarroweyahWeatherDetails = listOf(
    WeatherDetail("Sunrise", R.drawable.ic_sunrise, "7:20 AM"),
    WeatherDetail("Sunset", R.drawable.ic_sunset, "7:29 PM"),
    WeatherDetail("Air Quality", R.drawable.ic_air_quality, "Mod 132"),
    WeatherDetail("Wind", R.drawable.ic_wind, "5 km/h"),
    WeatherDetail("Humidity", R.drawable.ic_humidity, "77%"),
    WeatherDetail("Pressure", R.drawable.ic_pressure, "1025.1 mBar"),
    WeatherDetail("Visibility", R.drawable.ic_visibility, "10 mi"),
    WeatherDetail("UV Index", R.drawable.ic_uv_index, "0"),
    WeatherDetail("Dew Point", R.drawable.ic_dew_point, "11°"),
    WeatherDetail("Precipitation", R.drawable.ic_precipition, "0 in")
)
