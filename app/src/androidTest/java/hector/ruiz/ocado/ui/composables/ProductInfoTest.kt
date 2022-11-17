package hector.ruiz.ocado.ui.composables

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import hector.ruiz.domain.entities.ProductModel
import hector.ruiz.ocado.ui.theme.OcadoTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ProductInfoTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val product = ProductModel(id = 14,
        price = "10",
        title = "title",
        imageUrl = "imageUrl",
        description = "description",
        allergyInformation = "allergyInformation")

    @Before
    fun setUp() {
        composeTestRule.setContent {
            OcadoTheme {
                ProductInfo(product, Modifier)
            }
        }
    }

    @Test
    fun testProductInfoDisplay() {
        composeTestRule.onNodeWithContentDescription("Picture").assertIsDisplayed()
        composeTestRule.onNodeWithText(product.title).assertIsDisplayed()
        composeTestRule.onNodeWithText("${product.price} €").assertIsDisplayed()
    }
}