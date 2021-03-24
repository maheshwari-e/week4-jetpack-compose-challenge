package com.example.androiddevchallenge.stubdata

import androidx.compose.ui.graphics.Color
import com.example.androiddevchallenge.ui.theme.*

data class City(
    val name: String,
    val hourlyDayDetail: List<ForecastData>,
    val nextDaysDetail: List<DayDetail>,
    val weatherDetail: List<WeatherDetail>,
    val feels: Int,
    val highTemperature:Int,
    val lowTemperature:Int,
    val type: WeatherType
)

val cities = listOf(
    City(name ="Minneapolis",
        hourlyDayDetail = minneapolisHourlyDetails,
        nextDaysDetail = minneapolisNext9DaysDetails,
        weatherDetail = minneapolisWeatherDetails,
        feels = 7,
        highTemperature = 10,
        lowTemperature = -3,
        type = WeatherType.SUNNY),

    City(name = "Seattle",
        hourlyDayDetail = seattleHourlyDetails,
        nextDaysDetail = seattleNext9DaysDetails,
        weatherDetail = seattleWeatherDetails,
        feels = 9,
        highTemperature = 12,
        lowTemperature = 5,
        type = WeatherType.DRIZZLE),

    City(name = "Munich",
        hourlyDayDetail = munichHourlyDetails,
        nextDaysDetail = munichNext9DaysDetails,
        weatherDetail = munichWeatherDetails,
        feels = -3,
        highTemperature = 3,
        lowTemperature = -4,
        type = WeatherType.SNOW),

    City(name = "Yarroweyah",
        hourlyDayDetail = yarroweyahHourlyDetails,
        nextDaysDetail = yarroweyahNext9DaysDetails,
        weatherDetail = yarroweyahWeatherDetails,
        feels = 14,
        highTemperature = 28,
        lowTemperature = 14,
        type = WeatherType.CLEAR),

    City(name = "Fairbanks",
        hourlyDayDetail = fairbanksHourlyDetails,
        nextDaysDetail = fairbanksNext9DaysDetails,
        weatherDetail = fairbanksWeatherDetails,
        feels = -18,
        highTemperature = -11,
        lowTemperature = -24,
        type = WeatherType.CLOUDY)
)

enum class WeatherType(val value:String, val theme: List<Color>){
    SUNNY("Sunny", listOf(sunny_primary, sunny_secondary, sunny_third)),
    DRIZZLE("Drizzle", listOf(drizzle_primary, drizzle_secondary, drizzle_third)),
    CLOUDY("Cloudy", listOf(cloudy_primary, cloudy_secondary, cloudy_third)),
    SNOW("Snow Shower", listOf(snow_primary, snow_secondary, snow_third)),
    CLEAR("Clear", listOf(clear_primary, clear_secondary, clear_third))
}
