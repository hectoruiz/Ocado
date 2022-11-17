package hector.ruiz.ocado.ui.composables

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import hector.ruiz.ocado.ui.theme.OcadoTheme
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ErrorDataTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private var onButtonClicked = false

    @Before
    fun setUp() {
        composeTestRule.setContent {
            OcadoTheme {
                ErrorData {
                    onButtonClicked = true
                }
            }
        }
    }

    @Test
    fun testOnClickTryAgainButton() {
        composeTestRule.onNodeWithText("Try again").performClick()
        assertTrue(onButtonClicked)
    }

    @Test
    fun testErrorDataDisplay() {
        composeTestRule
            .onNodeWithContentDescription("Error data").assertIsDisplayed()
        composeTestRule
            .onNodeWithText("Ups… something was wrong…").assertIsDisplayed()
    }
}