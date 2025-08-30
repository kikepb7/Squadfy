package com.kikepb.squadfy.ui.common.navigation.bottomnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.kikepb.squadfy.ui.common.navigation.Routes

@Composable
fun BottomNavigationWrapper(
    navController: NavHostController,
    mainNavController: NavHostController
) {
    // TODO --> fix startDestination, change by the one who choice
    NavHost(navController = navController, startDestination = Routes.Home.route) {

    }
}