package com.mayank.presentation.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mayank.presentation.R
import com.mayank.presentation.base.BaseScreen
import com.mayank.presentation.constant.Constant.characterId
import com.mayank.presentation.features.detailscreen.CharacterDetailScreen
import com.mayank.presentation.features.homescreen.CharacterListScreen

@Composable
fun NavGraph(navController: NavHostController, context: Context) {
    NavHost(
        navController = navController,
        startDestination = NavigationScreens.CharacterListScreen.route
    ) {
        composable(NavigationScreens.CharacterListScreen.route) {
            BaseScreen(
                title = stringResource(id = R.string.characters),
                showBackButton = false,
                onBackClicked = {}
            ) {
                CharacterListScreen(context) {
                    navController.navigate(NavigationScreens.CharacterDetailScreen.route + "/${it}")
                }
            }
        }
        composable(
            "${NavigationScreens.CharacterDetailScreen.route}/{${characterId}}",
            arguments = listOf(navArgument(characterId) { type = NavType.IntType })
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.getInt(characterId)?.let {
                BaseScreen(
                    title = stringResource(R.string.character_detail),
                    showBackButton = true,
                    onBackClicked = {
                        navController.popBackStack()
                    }) {
                    CharacterDetailScreen(
                        context
                    )
                }
            }
        }
    }
}