package com.mayank.presentation.ui.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.mayank.presentation.base.BaseScreen
import org.junit.Rule
import org.junit.Test

class BaseScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testBaseScreen() {
        composeTestRule.setContent {
            BaseScreen(
                title = "Test Title",
                showBackButton = true,
                onBackClicked = {},
                content = {}
            )
        }

        // Verify the title is displayed correctly
        composeTestRule.onNodeWithText("Test Title").assertIsDisplayed()

        // Verify the back button is displayed correctly
        composeTestRule.onNodeWithContentDescription("ArrowBack").assertIsDisplayed()

        // Perform a click on the back button
        composeTestRule.onNodeWithContentDescription("ArrowBack").performClick()
    }
}
