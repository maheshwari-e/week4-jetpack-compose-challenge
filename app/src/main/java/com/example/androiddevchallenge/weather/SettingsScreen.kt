package com.example.androiddevchallenge.weather

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.stubdata.City
import com.example.androiddevchallenge.stubdata.TemperatureUnit
import com.example.androiddevchallenge.stubdata.cities
import com.example.androiddevchallenge.stubdata.toFahrenheit
import com.example.androiddevchallenge.ui.theme.white
import com.example.androiddevchallenge.viewmodel.HomeScreenViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun SettingsScreen(navController: NavHostController, homeScreenViewModel: HomeScreenViewModel) {
    val color = MaterialTheme.colors.surface
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val background = cities[homeScreenViewModel.selectedCity.value].type
    val coroutineScope = rememberCoroutineScope()
    Scaffold(Modifier.testTag("Settings Screen")) {
        BottomSheetScaffold(
            sheetContent = {
                BottomSheetContentForShowCities(
                    homeScreenViewModel = homeScreenViewModel,
                    bottomSheetScaffoldState = bottomSheetScaffoldState,
                    coroutineScope = coroutineScope
                ) },
            scaffoldState = bottomSheetScaffoldState,
            sheetPeekHeight = 0.dp,
            sheetBackgroundColor = MaterialTheme.colors.background,
            sheetShape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(background.theme)
                    )
                    .padding(top = 48.dp)) {
                HeaderTabForSetting(navController, color)
                Spacing(height = 16.dp)
                TemperatureUnitOption(homeScreenViewModel, color)
                Spacing(height = 16.dp)
                MyLocationContent(homeScreenViewModel, color)
                Spacing(height = 16.dp)
                AddCityContent(color, bottomSheetScaffoldState, coroutineScope)
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun BottomSheetContentForShowCities(
    homeScreenViewModel: HomeScreenViewModel,
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    coroutineScope: CoroutineScope
) {
    LazyColumn(modifier = Modifier.testTag("Show Cities")) {
        item {
            Row(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.select_city),
                    style = MaterialTheme.typography.body1.copy(MaterialTheme.colors.onSurface),
                    modifier = Modifier.weight(2f)
                )
                Icon(
                    imageVector = Icons.Outlined.Close,
                    contentDescription = stringResource(id = R.string.description_close),
                    modifier = Modifier.clickable {
                        handleBottomSheet(bottomSheetScaffoldState, coroutineScope)
                    },
                    tint = MaterialTheme.colors.onSurface
                )
            }
            Divider(color = MaterialTheme.colors.onSurface)
        }
        items(cities) { city ->
            Text(
                text = city.name,
                style = MaterialTheme.typography.body1.copy(MaterialTheme.colors.onSurface),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        handleBottomSheet(bottomSheetScaffoldState, coroutineScope)
                        val isContent = homeScreenViewModel.listCities.contains(city)
                        if (!isContent) homeScreenViewModel.listCities.add(city)
                    }
                    .padding(16.dp)
            )
            Divider(color = MaterialTheme.colors.onSurface)
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun AddCityContent(
    color: Color,
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    coroutineScope: CoroutineScope
) {
    Button(
        onClick = { handleBottomSheet(bottomSheetScaffoldState, coroutineScope) },
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .border(
                border = BorderStroke(1.dp, color),
                shape = MaterialTheme.shapes.medium
            ),
        colors = ButtonDefaults.buttonColors(Color.Transparent)
    ) {
        Text(
            text = stringResource(id = R.string.add_city),
            style = MaterialTheme.typography.button.copy(color),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(4.dp)
        )
    }
}

@ExperimentalMaterialApi
fun handleBottomSheet(
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    coroutineScope: CoroutineScope
) {
    coroutineScope.launch {
        if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
            bottomSheetScaffoldState.bottomSheetState.expand()
        } else {
            bottomSheetScaffoldState.bottomSheetState.collapse()
        }
    }
}

@Composable
fun TemperatureUnitOption(homeScreenViewModel: HomeScreenViewModel = viewModel(), color: Color) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.temperature_unit),
            style = MaterialTheme.typography.h1.copy(color),
            textAlign = TextAlign.Start,
            modifier = Modifier.weight(1f)
        )
        val tempUnit = homeScreenViewModel.temperatureUnit.value
        Text(
            text = tempUnit.unit,
            style = MaterialTheme.typography.h1.copy(color),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .semantics { contentDescription = tempUnit.toString() }
                .size(48.dp)
                .clickable {
                    homeScreenViewModel.temperatureUnit.value = when (tempUnit) {
                        TemperatureUnit.CELSIUS -> TemperatureUnit.FAHRENHEIT
                        TemperatureUnit.FAHRENHEIT -> TemperatureUnit.CELSIUS
                    }
                }
                .padding(8.dp))
    }
}

@ExperimentalMaterialApi
@Composable
fun MyLocationContent(
    homeScreenViewModel: HomeScreenViewModel = viewModel(),
    color: Color
) {
    Text(
        text = stringResource(id = R.string.my_location),
        style = MaterialTheme.typography.h1.copy(color),
        modifier = Modifier.padding(horizontal = 16.dp))
    Spacing(height = 8.dp)
    ShowMyLocationLists(homeScreenViewModel, color)
}

@ExperimentalMaterialApi
@Composable
fun ShowMyLocationLists(homeScreenViewModel: HomeScreenViewModel, color: Color) {
    val cities = homeScreenViewModel.listCities
    val unit = homeScreenViewModel.temperatureUnit.value
    LazyColumn(
        modifier = Modifier.padding(horizontal = 16.dp)) {
        item { LocationContent(cities[0], unit, color) }
        items(cities.subList(1, cities.size)) {
            val background = it.type.theme[1]
            val dismissState = rememberDismissState { value ->
                if (value == DismissValue.DismissedToStart) {
                    homeScreenViewModel.listCities.remove(it)
                }
                true
            }
            SwipeToDismiss(
                state = dismissState,
                modifier = Modifier.padding(vertical = 4.dp),
                directions = setOf(DismissDirection.EndToStart),
                dismissThresholds = { FractionalThreshold(1f) },
                background = {
                    val scale by animateFloatAsState(
                        if (dismissState.targetValue == DismissValue.Default) 1f else 1.5f
                    )
                    if (it.name != cities[0].name) {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Delete,
                                contentDescription = stringResource(R.string.description_delete),
                                modifier = Modifier
                                    .padding(8.dp)
                                    .scale(scale),
                                tint = white
                            )
                        }
                    }
                },
                dismissContent = {
                    Box(modifier = Modifier
                        .background(color = background, shape = MaterialTheme.shapes.medium)
                        .padding(8.dp)) {
                        LocationContent(it, unit, color)
                    }
                }
            )
        }
    }
}

@Composable
fun LocationContent(city: City, unit: TemperatureUnit, color: Color) {
    Row(
        modifier = Modifier
            .semantics(mergeDescendants = true) {}
            .height(40.dp)
            .padding(bottom = 4.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = city.name,
            style = MaterialTheme.typography.h2.copy(color),
            modifier = Modifier.weight(1f)
        )
        val temp = city.hourlyDayDetail[0].temp
        Text(
            text = stringResource(id=R.string.temperature,getTemperatureFormat(temp,unit),unit.unit),
            style = MaterialTheme.typography.h1.copy(color)
        )
    }
}

@Composable
fun HeaderTabForSetting(navController: NavHostController, color: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.ArrowBack,
            contentDescription = stringResource(R.string.description_back),
            tint = color,
            modifier = Modifier
                .size(48.dp)
                .clickable { navController.popBackStack() }
                .padding(horizontal = 12.dp)
        )
        Text(
            text = stringResource(id = R.string.description_settings),
            style = MaterialTheme.typography.h3.copy(color)
        )
    }
}

fun getTemperatureFormat(temp: Int, unit: TemperatureUnit) = when(unit) {
    TemperatureUnit.FAHRENHEIT -> temp.toFahrenheit()
    else -> temp
}
