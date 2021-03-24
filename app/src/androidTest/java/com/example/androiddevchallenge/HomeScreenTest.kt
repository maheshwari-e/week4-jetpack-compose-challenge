package com.example.androiddevchallenge

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.stubdata.TemperatureUnit
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.viewmodel.HomeScreenViewModel
import com.example.androiddevchallenge.weather.HomeScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @ExperimentalAnimationApi
    @ExperimentalMaterialApi
    @Before
    fun setUp() {
        composeTestRule.setContent {
            MyTheme {
                val navController = rememberNavController()
                val homeScreenViewModel = HomeScreenViewModel()
                HomeScreen(navController = navController, homeScreenViewModel = homeScreenViewModel)
            }
        }
    }

    @Test
    fun show_current_location_test(){
        //check default city as "Minneapolis"
        composeTestRule.onNodeWithText("Minneapolis").assertExists()
    }

    @Test
    fun show_temperature_unit_test(){
        //check default temperature unit as "Celsius
        val temperature = composeTestRule.onNodeWithTag("Temperature Unit")
        temperature.assertExists()

        temperature.assertTextContains(TemperatureUnit.CELSIUS.unit)
    }

    @Test
    fun show_current_temperature_test(){
        //check default current temperature as format of celsius
        composeTestRule.onNodeWithTag("Temperature Unit")
            .assertContentDescriptionEquals("10${TemperatureUnit.CELSIUS.unit}")
    }

    @Test
    fun background_animation_test(){
        //check default background with animation for "Sunny"
        composeTestRule.onNodeWithTag("Sunny Animation").assertExists()
    }

    @Test
    fun swipe_up_bottom_sheet_test(){
        val nextDays = composeTestRule.onNodeWithTag("Next 9 Days")
        nextDays.assertExists()
        nextDays.performClick()
        val next9Days = composeTestRule.onNodeWithTag("Days details")
        next9Days.assertIsDisplayed()
    }

    @Test
    fun show_next_nine_days_test(){
        composeTestRule.onNodeWithTag("Next 9 Days").performClick().assertIsDisplayed()
        val next9Days = composeTestRule.onNodeWithTag("Days details").onChildren()
        next9Days.filter(hasText("Friday"))
        next9Days.filter(hasText("Saturday"))
        next9Days.filter(hasText("Sunday"))
        next9Days.filter(hasText("Monday"))
        next9Days.filter(hasText("Tuesday"))
        next9Days.filter(hasText("Wednesday"))
        next9Days.filter(hasText("Thursday"))
        next9Days.filter(hasText("Friday"))
        next9Days.filter(hasText("Saturday"))

        next9Days[1].assertContentDescriptionEquals("Friday Sunny  High 11 Low 1")
    }

    @Test
    fun bottom_sheet_close_button_test(){
        //check close button before swipe up bottom sheet
        composeTestRule.onNodeWithContentDescription("Close").assertDoesNotExist()

        //check close button After swipe up bottom sheet
        composeTestRule.onNodeWithTag("Next 9 Days").performClick()
        composeTestRule.onNodeWithTag("Days details").assertIsDisplayed()

        composeTestRule.onNodeWithContentDescription("Close").assertExists()
        composeTestRule.onNodeWithTag("Next 9 Days").performClick()

    }
}
