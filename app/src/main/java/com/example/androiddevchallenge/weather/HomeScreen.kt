package com.example.androiddevchallenge.weather

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.animate.*
import com.example.androiddevchallenge.stubdata.*
import com.example.androiddevchallenge.viewmodel.HomeScreenViewModel

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun HomeScreen(navController: NavHostController, homeScreenViewModel: HomeScreenViewModel) {
    val color = MaterialTheme.colors.surface
    val temperatureUnit = homeScreenViewModel.temperatureUnit
    val cityDetails = homeScreenViewModel.listCities[homeScreenViewModel.selectedCity.value]
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    Scaffold(
        modifier = Modifier
            .testTag("Home Screen")
            .padding(bottom = 40.dp)
    ) {
        BottomSheetScaffold(
            sheetContent = {
                BottomSheetContent(
                    bottomSheetScaffoldState = bottomSheetScaffoldState,
                    temperatureUnit = temperatureUnit.value,
                    cityDetails = cityDetails
                ) },
            scaffoldState = bottomSheetScaffoldState,
            sheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
            sheetBackgroundColor = MaterialTheme.colors.background
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                if(cityDetails.type == WeatherType.CLEAR) ClearSkyBackground()

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                if (cityDetails.type != WeatherType.CLEAR) {
                                    cityDetails.type.theme
                                } else {
                                    listOf(Color.Transparent, Color.Transparent)
                                }
                            )
                        )
                        .padding(top = 48.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    HeaderTabForHome(
                        navController = navController,
                        homeScreenViewModel = homeScreenViewModel,
                        name = cityDetails.name
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(125.dp)
                            .padding(top = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        GetAnimationForSelectedItem(cityDetails.type)
                        Column(
                            modifier = Modifier
                                .semantics(mergeDescendants = true) {}
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                text = stringResource(R.string.today),
                                style = MaterialTheme.typography.h1.copy(color)
                            )
                            Text(
                                text = stringResource(R.string.day),
                                style = MaterialTheme.typography.body2.copy(color)
                            )
                        }
                    }
                    Row(
                        modifier = Modifier
                            .testTag("Temperature Unit")
                            .semantics(mergeDescendants = true) {}
                    ) {
                        val temp = getTemperature(
                            cityDetails.hourlyDayDetail[0].temp,
                            cityDetails.hourlyDayDetail[0].type, temperatureUnit.value
                        ).removeSuffix("°")
                        Text(
                            text = temp,
                            style = MaterialTheme.typography.subtitle1.copy(color)
                        )
                        val temperature = stringResource(
                            id = R.string.temperature,temp,temperatureUnit.value.unit
                        )
                        Text(
                            text = temperatureUnit.value.unit,
                            style = MaterialTheme.typography.body1.copy(color),
                            modifier = Modifier
                                .semantics { contentDescription = temperature }
                                .padding(top = 16.dp)
                        )
                    }
                    Text(
                        text = cityDetails.type.value,
                        style = MaterialTheme.typography.h3.copy(color)
                    )
                    val highTemp = getTemperatureFormat(
                        cityDetails.highTemperature,
                        temperatureUnit.value)
                    val lowTemp =  getTemperatureFormat(
                        cityDetails.lowTemperature,
                        temperatureUnit.value)
                    val feel = getTemperatureFormat(cityDetails.feels,temperatureUnit.value)
                    val weatherDetails = stringResource(
                        id = R.string.short_description_weather,
                        feel, highTemp, lowTemp)
                    Text(
                        text = stringResource(id = R.string.short_weather_details,
                            feel, highTemp, lowTemp),
                        style = MaterialTheme.typography.body2.copy(color),
                        modifier = Modifier.semantics { contentDescription = weatherDetails }
                    )
                    Spacing(16.dp)
                    Divider(color = color)
                    val hourlyForecast = stringResource(R.string.description_hourly_forecasts)
                    LazyRow(
                        modifier = Modifier
                            .semantics { contentDescription = hourlyForecast }
                            .fillMaxWidth()
                            .padding(
                                vertical = 8.dp,
                                horizontal = 12.dp
                            )
                    ) {
                        items(cityDetails.hourlyDayDetail) {
                            HourlyForecast(it, temperatureUnit.value)
                        }
                    }
                    Spacing(8.dp)
                    Divider(color = color)
                    Spacing(16.dp)
                    Text(
                        text = stringResource(R.string.details),
                        style = MaterialTheme.typography.h3.copy(color = color)
                    )
                    Spacing(8.dp)
                    LazyRow(
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .fillMaxWidth()
                    ) {
                        items(cityDetails.weatherDetail) {
                            WeatherConditions(it,cityDetails.type.theme[1])
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GetAnimationForSelectedItem(type: WeatherType) {
    when (type) {
        WeatherType.CLOUDY -> CloudyAnimation()
        WeatherType.SUNNY -> SunnyDayAnimation()
        WeatherType.SNOW -> ParticleDropAnimation(
            cloud = R.drawable.ic_cloud_overlap,
            particle = R.drawable.ic_snow
        )
        WeatherType.DRIZZLE -> ParticleDropAnimation(
            cloud = R.drawable.ic_cloud_overlap,
            particle = R.drawable.ic_droplet
        )
        else -> ClearSkyAnimation()
    }
}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun BottomSheetContent(
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    temperatureUnit: TemperatureUnit,
    cityDetails: City
) {
    val color = MaterialTheme.colors.onSurface
    LazyColumn(
        modifier = Modifier
            .testTag("Days details")
            .padding(vertical = 16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        item {
            val coroutineScope = rememberCoroutineScope()
            Box(modifier = Modifier
                .fillMaxWidth()
                .testTag("Next 9 Days")
                .clickable {
                    handleBottomSheet(bottomSheetScaffoldState, coroutineScope)
                }
            ) {
                Text(
                    text = stringResource(R.string.next_few_days),
                    style = MaterialTheme.typography.h3.copy(color),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(bottom = 16.dp)
                )
                   AnimatedVisibility(bottomSheetScaffoldState.bottomSheetState.isExpanded,
                   modifier = Modifier.align(Alignment.CenterEnd)) {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = stringResource(R.string.description_close),
                            modifier = Modifier
                                .padding(
                                    top = 4.dp,
                                    bottom = 16.dp,
                                    end = 16.dp
                                ),
                            tint = color
                        )
                    }
                }
        }
        item { Divider(color = color) }
        item { Spacing(height = 4.dp) }
        items(cityDetails.nextDaysDetail) {
            Next9DaysContent(
                color = color,
                dayDetail = it,
                temperatureUnit = temperatureUnit
            )
        }
    }
}

@Composable
fun Next9DaysContent(color: Color, dayDetail: DayDetail, temperatureUnit: TemperatureUnit) {
    val highTemp = getTemperatureFormat(dayDetail.highTemp, temperatureUnit).toString()
    val lowTemp = getTemperatureFormat(dayDetail.lowTemp, temperatureUnit).toString()
    val chance =
        if (dayDetail.chance.isNotEmpty())
            stringResource(id = R.string.description_chance,dayDetail.chance) else ""
    val descriptionDay = stringResource(id = R.string.description_day_details,
        dayDetail.name, dayDetail.contentDescription,chance,highTemp,lowTemp)
    Row(
        modifier = Modifier
            .semantics(mergeDescendants = true) {
                contentDescription = descriptionDay
            }
            .padding(
                horizontal = 16.dp,
                vertical = 4.dp
            )
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = dayDetail.name,
            style = MaterialTheme.typography.body1.copy(color),
            modifier = Modifier.weight(1f)
        )
        Row(
            modifier = Modifier.weight(1f)
        ) {
            Image(
                painter = painterResource(id = dayDetail.image),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = dayDetail.chance,
                style = MaterialTheme.typography.body2.copy(color)
            )
        }
        Text(
            text = highTemp,
            style = MaterialTheme.typography.body1.copy(color),
            modifier = Modifier.weight(0.25f),
            textAlign = TextAlign.End
        )
        Text(
            text = lowTemp,
            style = MaterialTheme.typography.body1.copy(color),
            modifier = Modifier.weight(0.25f),
            textAlign = TextAlign.End
        )
    }
}

@Composable
fun Spacing(height: Dp) {
    Spacer(modifier = Modifier.height(height))
}

@Composable
fun WeatherConditions(weatherDetail: WeatherDetail, background: Color) {
    val color =MaterialTheme.colors.surface
    Card(
        modifier = Modifier
            .semantics(mergeDescendants = true) {}
            .padding(horizontal = 4.dp)
            .size(100.dp)
            .border(
                border = BorderStroke(1.dp, color),
                shape = MaterialTheme.shapes.small
            )
    ) {
        Column(
            modifier = Modifier
                .background(color = background)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Text(
                text = weatherDetail.name,
                style = MaterialTheme.typography.body2.copy(color)
            )
            Image(
                painter = painterResource(id = weatherDetail.image),
                contentDescription = null,
                modifier = Modifier
                    .padding(4.dp)
                    .size(24.dp)
            )
            Text(
                text = weatherDetail.value,
                style = MaterialTheme.typography.body2.copy(color)
            )
        }
    }
}

@Composable
fun HourlyForecast(forecastData: ForecastData, temperatureUnit: TemperatureUnit) {
    val color =MaterialTheme.colors.surface
    val temp = getTemperature(forecastData.temp, forecastData.type, temperatureUnit)
    val forecast = stringResource(
        id = R.string.forecast_details,
        forecastData.time,
        forecastData.contentDescription,
        temp
    )
    Column(
        modifier = Modifier
            .semantics(mergeDescendants = true) {
                contentDescription = forecast
            }
            .padding(horizontal = 4.dp)
    ) {
        Text(
            text = forecastData.time,
            style = MaterialTheme.typography.body2.copy(color)
        )
        val modifier = Modifier
            .padding(top = 4.dp)
            .height(75.dp)
            .border(
                border = BorderStroke(width = 1.dp, color = color),
                shape = RoundedCornerShape(50)
            )
        Box(
            modifier = modifier
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 16.dp)
            ) {
                Image(
                    painter = painterResource(forecastData.image),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = temp,
                    style = MaterialTheme.typography.body2.copy(color)
                )
            }
        }
    }
}

fun getTemperature(
    temp: Int,
    type: ForecastType,
    temperatureUnit: TemperatureUnit = TemperatureUnit.CELSIUS
): String {
    return when(type) {
        ForecastType.TEMPERATURE -> {
            val temperature = getTemperatureFormat(temp, temperatureUnit)
            "$temperature°"
        }
        ForecastType.SUNRISE -> "Sunrise"
        ForecastType.SUNSET -> "Sunset"
    }
}

@Composable
fun HeaderTabForHome(
    navController: NavHostController,
    homeScreenViewModel: HomeScreenViewModel,
    name: String
) {
    val iconColor = MaterialTheme.colors.surface
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            DropDownCities(iconColor, homeScreenViewModel,name)
        }
        Icon(
            imageVector = Icons.Filled.Settings,
            contentDescription = stringResource(R.string.description_settings),
            tint = iconColor,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .size(48.dp)
                .clickable { navController.navigate("Settings") }
                .padding(12.dp)
        )
    }
}

@Composable
fun DropDownCities(iconColor: Color, homeScreenViewModel: HomeScreenViewModel, name: String) {
    var expand by remember { mutableStateOf(false)}
    Box {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .testTag("Added Cities")
                .semantics(mergeDescendants = true) {}
                .clickable { expand = true },
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.h3
            )
            Icon(
                imageVector = Icons.Outlined.LocationOn,
                contentDescription = stringResource(id = R.string.description_location, name),
                tint = iconColor
            )
        }
        DropdownMenu(
            expanded = expand,
            onDismissRequest = { expand = false },
            modifier = Modifier
                .testTag("Cities")
                .background(MaterialTheme.colors.primary)
                .align(Alignment.Center),
            offset = DpOffset(12.dp,0.dp)
        ) {
            homeScreenViewModel.listCities.forEachIndexed { index, city ->
                DropdownMenuItem(
                    onClick = {
                        homeScreenViewModel.selectedCity.value = index
                        expand = false
                    }
                ) {
                    Text(
                        text = city.name,
                        style = MaterialTheme.typography.h2.copy(MaterialTheme.colors.onPrimary)
                    )
                }
            }
        }
    }
}
