package com.github.emmpann.resepan.ui.screen.detail

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.github.emmpann.resepan.model.Food
import com.github.emmpann.resepan.ui.theme.ResepanTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailScreenKtTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val fakeFood = Food(
        1,
        "https://d1vbn70lmn1nqe.cloudfront.net/prod/wp-content/uploads/2023/07/13073811/Praktis-dengan-Bahan-Sederhana-Ini-Resep-Nasi-Goreng-Special-1.jpg.webp",
        "Nasi Goreng",
        false,
        arrayListOf("just", "fake", "data"),
        arrayListOf("just", "fake", "data"),
    )

    private fun getIngredientsString(): String {
        var ingredients = ""
        fakeFood.ingredients.forEach {
            ingredients += "- $it\n"
        }
        return ingredients
    }

    private fun getWaysString(): String {
        var ways = ""
        fakeFood.ways.forEach {
            ways += "- $it"
        }
        return ways
    }

    @Before
    fun setUp() {

        composeTestRule.setContent {
            ResepanTheme {
                DetailContent(
                    onCheckedChange = { },
                    onBackClick = { },
                    imageUrl = fakeFood.imageUrl,
                    name = fakeFood.name,
                    isFavorite = fakeFood.isFavorite,
                    time = 20,
                    rating = 4.8f,
                    ingredients = getIngredientsString(),
                    ways = getWaysString()
                )
            }
        }
    }

    @Test
    fun detailContent_isDisplayed() {
        composeTestRule.onNodeWithText(fakeFood.name).assertIsDisplayed()
        composeTestRule.onNodeWithText(getIngredientsString()).assertIsDisplayed()
        composeTestRule.onNodeWithText(getWaysString()).assertIsDisplayed()
    }
}