package com.github.emmpann.resepan.ui.screen.about

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.github.emmpann.resepan.ui.theme.ResepanTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AboutScreenKtTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            ResepanTheme {
                AboutScreen()
            }
        }
    }

    @Test
    fun aboutContent_isDisplayed() {
        composeTestRule.onNodeWithTag("username").assertExists()
            .assertTextEquals("Efan Fitriyan")
        composeTestRule.onNodeWithTag("user_email").assertExists()
            .assertTextEquals("mhdepan@gmail.com")
    }
}