package com.github.emmpann.resepan.ui.screen.favorite

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

class FavoriteScreenKtTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private var fakeFavRecipes = mutableListOf(
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
                FavoriteContent(favRecipe = fakeFavRecipes, navigateToDetail = { })
            }
        }
    }

    @Test
    fun favoriteContent_isDisplayed() {
        composeTestRule.onNodeWithText(fakeFavRecipes.first().name).assertIsDisplayed()
    }

    @Test
    fun favoriteContent_dataIsNotFoundInformationNotShow() {
        composeTestRule.onNodeWithTag("dataNotFound").assertDoesNotExist()
    }
}