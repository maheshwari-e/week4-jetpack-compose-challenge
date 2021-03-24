package com.example.androiddevchallenge.stubdata

import androidx.annotation.DrawableRes
import com.example.androiddevchallenge.R

data class DayDetail(
    val name:String,
    @DrawableRes
    val image: Int,
    val chance:String ="",
    val highTemp:Int,
    val lowTemp:Int,
    val contentDescription: String=""
)

val minneapolisNext9DaysDetails = listOf(
    DayDetail(name = "Friday", image = R.drawable.ic_sunny,highTemp = 11, lowTemp = 1,
        contentDescription = "Sunny"),
    DayDetail(name = "Saturday", image = R.drawable.ic_sunny,highTemp = 14, lowTemp = 7,
        contentDescription = "Sunny"),
    DayDetail(name = "Sunday", image = R.drawable.ic_rain_cloud,chance = "40%",highTemp = 15,
        lowTemp = 5, contentDescription = "Rainy"),
    DayDetail(name = "Monday", image = R.drawable.ic_rain_cloud,chance = "30%",highTemp = 11,
        lowTemp = 6,contentDescription = "Rainy"),
    DayDetail(name = "Tuesday", image = R.drawable.ic_rain_cloud,chance = "70%",highTemp = 7,
        lowTemp = 5,contentDescription = "Rainy"),
    DayDetail(name = "Wednesday", image = R.drawable.ic_rain_cloud,chance = "60%",highTemp = 6,
        lowTemp = 2,contentDescription = "Rainy"),
    DayDetail(name = "Thursday", image = R.drawable.ic_sun_cloud,highTemp = 12, lowTemp = 3,
        contentDescription = "Partly Cloudy"),
    DayDetail(name = "Friday", image = R.drawable.ic_rain_cloud,chance = "30%",highTemp = 11,
        lowTemp = 3,contentDescription = "Rainy"),
    DayDetail(name = "Saturday", image = R.drawable.ic_rain_cloud,chance = "40%",highTemp = 11,
        lowTemp = -1,contentDescription = "Rainy")
)

val fairbanksNext9DaysDetails = listOf(
    DayDetail(name = "Friday", image = R.drawable.ic_cloud,highTemp = 11, lowTemp = -24,
        contentDescription = "Mostly Cloudy"),
    DayDetail(name = "Saturday", image = R.drawable.ic_sunny,highTemp = -9, lowTemp = -24
        ,contentDescription = "Sunny"),
    DayDetail(name = "Sunday", image = R.drawable.ic_sunny,highTemp = -9, lowTemp = -23,
        contentDescription = "Sunny"),
    DayDetail(name = "Monday", image = R.drawable.ic_sunny,highTemp = -6, lowTemp = -20,
        contentDescription = "Sunny"),
    DayDetail(name = "Tuesday", image = R.drawable.ic_cloud,highTemp = -9, lowTemp = -18,
        contentDescription = "Mostly Cloudy"),
    DayDetail(name = "Wednesday", image = R.drawable.ic_cloud,highTemp = -6, lowTemp = -11,
        contentDescription = "Mostly Cloudy"),
    DayDetail(name = "Thursday", image = R.drawable.ic_snow_daylist,chance = "40%",highTemp = -3,
        lowTemp = -12,contentDescription = "Snow Shower"),
    DayDetail(name = "Friday", image = R.drawable.ic_snow_daylist,chance = "30%",highTemp = -3,
        lowTemp = -13,contentDescription = "Snow Shower"),
    DayDetail(name = "Saturday", image = R.drawable.ic_cloud,highTemp = -5, lowTemp = -15,
        contentDescription = "Mostly Cloudy")
)

val munichNext9DaysDetails = listOf(
    DayDetail(name = "Friday", image = R.drawable.ic_snow_daylist,chance = "50%",highTemp = 0,
        lowTemp = -6,contentDescription = "Snow Shower"),
    DayDetail(name = "Saturday", image = R.drawable.ic_snow_daylist,chance = "60%",highTemp = 3,
        lowTemp = -1,contentDescription = "Snow Shower"),
    DayDetail(name = "Sunday", image = R.drawable.ic_snow_daylist,chance = "60%",highTemp = 3,
        lowTemp = -2,contentDescription = "Snow Shower"),
    DayDetail(name = "Monday", image = R.drawable.ic_sun_cloud,highTemp = 6, lowTemp = -3,
        contentDescription = "Partly Cloudy"),
    DayDetail(name = "Tuesday", image = R.drawable.ic_sunny,highTemp = 10, lowTemp = -2,
        contentDescription = "Sunny"),
    DayDetail(name = "Wednesday", image = R.drawable.ic_sun_cloud,highTemp = 13, lowTemp = 1,
        contentDescription = "Partly Cloudy"),
    DayDetail(name = "Thursday", image = R.drawable.ic_sun_cloud,highTemp = 14, lowTemp = 2,
        contentDescription = "Partly Cloudy"),
    DayDetail(name = "Friday", image = R.drawable.ic_rain_cloud,chance = "50%",highTemp = 14,
        lowTemp = 3, contentDescription = "Rainy"),
    DayDetail(name = "Saturday", image = R.drawable.ic_sun_cloud,highTemp = 16, lowTemp = 3,
        contentDescription = "Partly Cloudy")
)

val seattleNext9DaysDetails = listOf(
    DayDetail(name = "Friday", image = R.drawable.ic_rain_cloud,chance = "50%",highTemp = 11,
        lowTemp = 3, contentDescription = "Rainy"),
    DayDetail(name = "Saturday", image = R.drawable.ic_rain_cloud,chance = "60%",highTemp = 7,
        lowTemp = 3, contentDescription = "Rainy"),
    DayDetail(name = "Sunday", image = R.drawable.ic_rain_cloud,chance = "30%",highTemp = 10,
        lowTemp = 4, contentDescription = "Rainy"),
    DayDetail(name = "Monday", image = R.drawable.ic_cloud,highTemp = 11, lowTemp = 6,
        contentDescription = "Mostly Cloudy"),
    DayDetail(name = "Tuesday", image = R.drawable.ic_rain_cloud,chance = "60%",highTemp = 10,
        lowTemp = 4, contentDescription = "Rainy"),
    DayDetail(name = "Wednesday", image = R.drawable.ic_cloud,highTemp = 11, lowTemp = 4
        , contentDescription = "Mostly Cloudy"),
    DayDetail(name = "Thursday", image = R.drawable.ic_sun_cloud,highTemp = 13, lowTemp = 4,
        contentDescription = "Partly Cloudy"),
    DayDetail(name = "Friday", image = R.drawable.ic_sun_cloud,highTemp = 15, lowTemp = 3,
        contentDescription = "Partly Cloudy"),
    DayDetail(name = "Saturday", image = R.drawable.ic_sun_cloud,highTemp = 15, lowTemp = 3,
        contentDescription = "Partly Cloudy")
)

val yarroweyahNext9DaysDetails = listOf(
    DayDetail(name = "Friday", image = R.drawable.ic_rain_cloud,chance = "90%",highTemp = 22,
        lowTemp = 16, contentDescription = "Rainy"),
    DayDetail(name = "Saturday", image = R.drawable.ic_rain_cloud,chance = "90%",highTemp = 20,
        lowTemp = 16, contentDescription = "Rainy"),
    DayDetail(name = "Sunday", image = R.drawable.ic_rain_cloud,chance = "60%",highTemp = 23,
        lowTemp = 14, contentDescription = "Rainy"),
    DayDetail(name = "Monday", image = R.drawable.ic_sun_cloud,highTemp = 24, lowTemp = 11,
        contentDescription = "Partly Cloudy"),
    DayDetail(name = "Tuesday", image = R.drawable.ic_sun_cloud,highTemp = 20, lowTemp = 9,
        contentDescription = "Partly Cloudy"),
    DayDetail(name = "Wednesday", image = R.drawable.ic_sun_cloud,highTemp = 21, lowTemp = 10,
        contentDescription = "Partly Cloudy"),
    DayDetail(name = "Thursday", image = R.drawable.ic_sun_cloud,highTemp = 21, lowTemp = 0,
        contentDescription = "Partly Cloudy"),
    DayDetail(name = "Friday", image = R.drawable.ic_sun_cloud,highTemp = 22, lowTemp = 10,
        contentDescription = "Partly Cloudy"),
    DayDetail(name = "Saturday", image = R.drawable.ic_sun,highTemp = 23, lowTemp = 11,
        contentDescription = "Sunny")
)
