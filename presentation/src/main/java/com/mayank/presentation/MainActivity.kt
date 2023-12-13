package com.mayank.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import androidx.paging.PagingData
import com.mayank.presentation.features.homescreen.CharacterListViewModel
import com.mayank.presentation.models.CharacterItem
import com.mayank.presentation.navigation.NavGraph
import com.mayank.presentation.ui.theme.RickMortyTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private var isLoading by mutableStateOf(false)
    private var isError by mutableStateOf(false)
    private val characterListViewModel: CharacterListViewModel by viewModels()
    private lateinit var characters: Flow<PagingData<CharacterItem>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            RickMortyTheme {
                NavGraph(navController = navController)
            }

        }
    }
}