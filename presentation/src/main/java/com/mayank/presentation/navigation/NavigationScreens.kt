package com.mayank.presentation.navigation

import com.mayank.presentation.constant.Constant.CHARACTER_DETAIL_ROUTE
import com.mayank.presentation.constant.Constant.CHARACTER_LIST_ROUTE

sealed class NavigationScreens(val route: String) {
    object CharacterListScreen : NavigationScreens(route = CHARACTER_LIST_ROUTE)
    object CharacterDetailScreen : NavigationScreens(route = CHARACTER_DETAIL_ROUTE)
}