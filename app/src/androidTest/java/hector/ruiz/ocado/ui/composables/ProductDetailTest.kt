package hector.ruiz.ocado.ui.composables

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import hector.ruiz.domain.entities.ProductModel
import hector.ruiz.ocado.ui.theme.OcadoTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ProductDetailTest {

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
                ProductDetail(product = product)
            }
        }
    }

    @Test
    fun testProductDetailDisplay() {
        composeTestRule.onNodeWithText(product.description).assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Allergies").assertIsDisplayed()
        composeTestRule.onNodeWithText(product.allergyInformation).assertIsDisplayed()
    }
}