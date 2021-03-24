package com.example.androiddevchallenge

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.stubdata.TemperatureUnit
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.viewmodel.HomeScreenViewModel
import com.example.androiddevchallenge.weather.SettingsScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SettingScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @ExperimentalMaterialApi
    @Before
    fun setUp() {
        composeTestRule.setContent {
            MyTheme{
                val navController = rememberNavController()
                val homeScreenViewModel = HomeScreenViewModel()
                SettingsScreen(navController = navController,
                    homeScreenViewModel = homeScreenViewModel)
            }
        }
    }

    @Test
    fun temperature_unit_test(){
        val temperatureUnit =  composeTestRule.onNodeWithContentDescription(
            TemperatureUnit.CELSIUS.toString()
        )
        temperatureUnit.assertExists().performClick()
        temperatureUnit.assertContentDescriptionEquals(TemperatureUnit.FAHRENHEIT.toString())

        temperatureUnit.performClick()
        temperatureUnit.assertContentDescriptionEquals(TemperatureUnit.CELSIUS.toString())
    }
    
    @Test
    fun add_city_test(){
        val addCityButton = composeTestRule.onNodeWithText("Add City")
        addCityButton.assertExists().performClick()
        showCities(addCityButton)
        checkCitiesAdded()
    }

    private fun checkCitiesAdded() {
         composeTestRule.onNodeWithText("Seattle").assertExists()
         composeTestRule.onNodeWithText("Fairbanks").assertExists()
    }

    private fun showCities(addCityButton: SemanticsNodeInteraction) {
        val cities = composeTestRule.onNodeWithTag("Show Cities")
        cities.assertExists().assertIsDisplayed()
        val clickables = cities.onChildren().filter(hasClickAction())

        clickables.assertCountEquals(6)

        clickables[0].assertContentDescriptionEquals("Close")
        clickables[1].assertTextEquals("Minneapolis")
        clickables[2].assertTextEquals("Seattle")
        clickables[3].assertTextEquals("Munich")
        clickables[4].assertTextEquals("Yarroweyah")
        clickables[5].assertTextEquals("Fairbanks")

        //seattle selected
        clickables[2].performClick()

        //Yarroweyah selected
        addCityButton.performClick()
        clickables[5].performClick()
    }

    @Test
    fun close_button_test(){
        composeTestRule.onNodeWithText("Add City").performClick()
        val cities = composeTestRule.onNodeWithTag("Show Cities")
        val click = cities.onChildren().filter(hasClickAction())

        click[0].assertContentDescriptionEquals("Close")
        //Close Action
        click[0].performClick()
        cities.assertIsNotDisplayed()
    }
}
