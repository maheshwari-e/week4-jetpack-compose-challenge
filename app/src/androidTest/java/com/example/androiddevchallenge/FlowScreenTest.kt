package com.example.androiddevchallenge

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.androiddevchallenge.stubdata.TemperatureUnit
import com.example.androiddevchallenge.ui.theme.MyTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FlowScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @ExperimentalAnimationApi
    @ExperimentalMaterialApi
    @Before
    fun setUp() {
        composeTestRule.setContent {
            MyTheme{
                MyApp()
            }
        }
    }

    @Test
    fun flow_settings_test(){
        val settingsButton = composeTestRule.onNodeWithContentDescription("Settings")
        settingsButton.assertExists().performClick()

        val settingScreen = composeTestRule.onNodeWithTag("Settings Screen")
        settingScreen.assertIsDisplayed()
    }

    @Test
    fun flow_back_button_test(){
        composeTestRule.onNodeWithContentDescription("Settings").performClick()

        val backButton = composeTestRule.onNodeWithContentDescription("Back")
        backButton.assertExists().performClick()

        val homeScreen = composeTestRule.onNodeWithTag("Home Screen")
        homeScreen.assertIsDisplayed()
    }

    @Test
    fun flow_test() {
        composeTestRule.onNodeWithContentDescription("Settings").performClick()

        composeTestRule.onNodeWithText("Add City").performClick()
        val cities = composeTestRule.onNodeWithTag("Show Cities").onChildren()
            .filter(hasClickAction())
        cities[2].performClick()

        composeTestRule.onNodeWithText("Add City").performClick()
        cities[3].performClick()

        composeTestRule.onNodeWithText("Add City").performClick()
        cities[4].performClick()

        composeTestRule.onNodeWithText("Add City").performClick()
        cities[5].performClick()

        composeTestRule.onNodeWithContentDescription("Back").performClick()

        val listCities = composeTestRule.onNodeWithTag("Added Cities")
        listCities.performClick().assertIsDisplayed()

        //verify the cities show in drop down
        val dropDownCities = composeTestRule.onNodeWithTag("Cities").onChildren()
        dropDownCities[0].assertTextEquals("Minneapolis")
        dropDownCities[1].assertTextEquals("Seattle")
        dropDownCities[2].assertTextEquals("Munich")
        dropDownCities[3].assertTextEquals("Yarroweyah")
        dropDownCities[4].assertTextEquals("Fairbanks")

        //select the city as "Munich
        dropDownCities[2].performClick()
        composeTestRule.onNodeWithText("Munich").assertExists()

        //Background & Animation valid
        composeTestRule.onNodeWithTag("Drizzle/Snow Animation").assertExists()

        //Select the city as "Seattle"
        listCities.performClick().assertIsDisplayed()
        dropDownCities[1].performClick()
        composeTestRule.onNodeWithText("Seattle").assertExists()
        composeTestRule.onNodeWithTag("Drizzle/Snow Animation").assertExists()

        //Select the city as "Yarroweyah"
        listCities.performClick().assertIsDisplayed()
        dropDownCities[3].performClick()
        composeTestRule.onNodeWithText("Yarroweyah").assertExists()
        composeTestRule.onNodeWithTag("Clear Sky Background").assertExists()
        composeTestRule.onNodeWithTag("Clear Sky Animation").assertExists()

        //Select the city as "Minneapolis"
        listCities.performClick().assertIsDisplayed()
        dropDownCities[0].performClick()
        composeTestRule.onNodeWithText("Minneapolis").assertExists()
        composeTestRule.onNodeWithTag("Sunny Animation").assertExists()

        //select the city as "Fairbanks"
        listCities.performClick().assertIsDisplayed()
        dropDownCities[4].performClick()
        composeTestRule.onNodeWithText("Fairbanks").assertExists()
        composeTestRule.onNodeWithTag("Cloudy Animation").assertExists()
    }

    @Test
    fun flow_update_temperature_unit_test(){
        composeTestRule.onNodeWithContentDescription("Settings").performClick()

        //convert Celsius to Fahrenheit
        composeTestRule.onNodeWithContentDescription(TemperatureUnit.CELSIUS.toString())
            .performClick()

        composeTestRule.onNodeWithContentDescription("Back").performClick()

        composeTestRule.onNodeWithTag("Temperature Unit")
            .assertTextContains(TemperatureUnit.FAHRENHEIT.unit)
    }

    @Test
    fun flow_update_temperature_format_test(){
        composeTestRule.onNodeWithContentDescription("Settings").performClick()

        composeTestRule.onNodeWithContentDescription(TemperatureUnit.CELSIUS.toString())
            .performClick()

        composeTestRule.onNodeWithContentDescription("Back").performClick()

        //verify the temperature format in "Fahrenheit"
        composeTestRule.onNodeWithTag("Temperature Unit")
            .assertContentDescriptionEquals("50${TemperatureUnit.FAHRENHEIT.unit}")

        composeTestRule.onNodeWithContentDescription("Settings").performClick()

        composeTestRule.onNodeWithContentDescription(TemperatureUnit.FAHRENHEIT.toString())
            .performClick()

        composeTestRule.onNodeWithContentDescription("Back").performClick()

        //verify the temperature format in "Celsius"
        composeTestRule.onNodeWithTag("Temperature Unit")
            .assertContentDescriptionEquals("10${TemperatureUnit.CELSIUS.unit}")
    }
}
