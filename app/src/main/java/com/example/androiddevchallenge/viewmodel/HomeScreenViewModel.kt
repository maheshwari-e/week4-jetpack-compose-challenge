package com.example.androiddevchallenge.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.stubdata.TemperatureUnit
import com.example.androiddevchallenge.stubdata.cities

class HomeScreenViewModel : ViewModel() {
     val temperatureUnit = mutableStateOf(TemperatureUnit.CELSIUS)
     val listCities = mutableStateListOf(cities[0])
     val selectedCity = mutableStateOf(0)
}
