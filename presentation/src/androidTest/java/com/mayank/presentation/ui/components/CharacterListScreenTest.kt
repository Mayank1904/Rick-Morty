package com.mayank.presentation.ui.components

import androidx.compose.ui.test.junit4.createComposeRule
import com.mayank.presentation.features.homescreen.CharacterListScreen
import org.junit.Rule
import org.junit.Test

class CharacterListScreenTest {
    @get:Rule
    val testRule = createComposeRule()

    @Test
    fun test_character_list_screen() {
        testRule.setContent {
            CharacterListScreen {

            }
        }
        testRule.run {

        }
    }
}