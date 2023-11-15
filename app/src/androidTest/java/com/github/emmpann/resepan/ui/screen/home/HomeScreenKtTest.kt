package com.github.emmpann.resepan.ui.screen.home

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.github.emmpann.resepan.model.Food
import com.github.emmpann.resepan.ui.theme.ResepanTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeScreenKtTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private var fakeRecipes = mutableListOf(
        Food(
            1,
            "gambar.jpg",
            "Nasi Uduk",
            false,
            arrayListOf("just", "fake", "data"),
            arrayListOf("just", "fake", "data"),
        )
    )

    @Before
    fun setUp() {
        composeTestRule.setContent {
            ResepanTheme {
                HomeContent(foodList = fakeRecipes, navigateToDetail = {}, query = "", onQueryChange = {})
            }
        }
    }

    @Test
    fun homeContent_isDisplayed() {
        composeTestRule.onNodeWithText(fakeRecipes.first().name).assertIsDisplayed()
    }

    @Test
    fun searchBar_isDisplayed() {
        composeTestRule.onNodeWithTag("searchBar").assertIsDisplayed()
    }
}