package com.mayank.presentation.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val lightColorScheme = lightColors(
    primary = Purple40,
    secondary = PurpleGrey40,
    surface = Purple40,
    background = white,
)

@Composable
fun RickMortyTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = lightColorScheme
    MaterialTheme(
        colors = colorScheme,
        typography = Typography,
        content = content,
    )
}