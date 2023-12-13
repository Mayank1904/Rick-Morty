package com.mayank.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mayank.presentation.R
import com.mayank.presentation.base.BaseScreen
import com.mayank.presentation.components.CharacterDetailScreen
import com.mayank.presentation.constant.Constant.characterId
import com.mayank.presentation.features.homescreen.CharacterListScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavigationScreens.CharacterListScreen.route
    ) {
        composable(NavigationScreens.CharacterListScreen.route) {
            BaseScreen(
                title = stringResource(id = R.string.characters),
                showBackButton = false,
                onBackClicked = {}) {
                CharacterListScreen {
                        navController.navigate(NavigationScreens.CharacterDetailScreen.route + "/${it}")
                }
            }
        }
        composable(
            "${NavigationScreens.CharacterDetailScreen.route}/{${characterId}}",
            arguments = listOf(navArgument(characterId) { type = NavType.IntType })
        ) { navBackStackEntry ->
            val characterId = navBackStackEntry.arguments?.getInt(characterId)
            characterId?.let {
                BaseScreen(
                    title = "Character Detail",
                    showBackButton = true,
                    onBackClicked = {
                        navController.popBackStack()
                    }) {
                    CharacterDetailScreen(
                        id = characterId
                    )
                }
            }
        }
    }
}