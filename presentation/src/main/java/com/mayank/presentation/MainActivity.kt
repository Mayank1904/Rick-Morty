package com.mayank.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.mayank.presentation.navigation.NavGraph
import com.mayank.presentation.ui.theme.RickMortyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            rememberNavController().run {
                RickMortyTheme {
                    NavGraph(navController = this, context = this.context)
                }
            }
        }
    }
}