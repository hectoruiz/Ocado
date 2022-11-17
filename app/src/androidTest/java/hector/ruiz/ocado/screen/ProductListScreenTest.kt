package hector.ruiz.ocado.screen

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import hector.ruiz.ocado.TestActivity
import hector.ruiz.ocado.ui.theme.OcadoTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class ProductListScreenTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<TestActivity>()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            OcadoTheme {
                ProductListScreen(navigateTo = {})
            }
        }
    }

    @Test
    fun testTopAppBarDisplayed() {
        composeTestRule.onNodeWithText("Product List").assertIsDisplayed()
    }
}